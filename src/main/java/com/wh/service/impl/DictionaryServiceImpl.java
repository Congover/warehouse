package com.wh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.entity.Address;
import com.wh.entity.Product;
import com.wh.entity.ProductQuantity;
import com.wh.entity.ProductType;
import com.wh.entity.Store;
import com.wh.entity.Transport;
import com.wh.repositories.AddressRepository;
import com.wh.repositories.ProductQuantityRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.repositories.TransportRepository;
import com.wh.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private AddressRepository addressRepository;

    @Resource
    private TransportRepository transportRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private StoreRepository storeRepository;

    @Resource
    private ProductQuantityRepository productQuantityRepository;

    @Override
    public List<Address> getAdresses() {
	return (List<Address>) addressRepository.findAll();
    }

    @Override
    public List<Transport> getTransports() {
	return (List<Transport>) transportRepository.findAll();
    }

    @Override
    public List<Product> getProducts() {
	return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Store> getStories() {
	return (List<Store>) storeRepository.findAll();
    }

    public void create(Class<?> cls, String value) {
	if (cls.isAssignableFrom(Address.class)) {
	    Address entity = new Address();
	    entity.setFullAddress(value);
	    addressRepository.save(entity);
	} else if (cls.isAssignableFrom(Transport.class)) {
	    Transport entity = new Transport();
	    entity.setName(value);
	    transportRepository.save(entity);
	} else if (cls.isAssignableFrom(Store.class)) {
	    Store entity = new Store();
	    entity.setName(value);
	    storeRepository.save(entity);
	} else if (cls.isAssignableFrom(Product.class)) {
	    Product entity = new Product();
	    entity.setName(value);
	    entity.setProductType(ProductType.PLACER);
	    entity = productRepository.save(entity);
	    ProductQuantity pq = new ProductQuantity();
	    pq.setProduct(entity);
	    pq.setBagCount(0);
	    pq.setProductCount(0D);
	    productQuantityRepository.save(pq);
	}
    }

    @Override
    public List<Product> getAvailibleProductForPacking() {
	return productRepository.findByProductType(ProductType.PLACER);
    }

    @Override
    public List<Product> getAvailibleProductForIncoming() {
	return productRepository.findByProductTypeNot(ProductType.PLACER);
    }

    @Override
    public List<Product> getAvailibleProductForShipment() {
	return productRepository.findByProductTypeNot(ProductType.BAG);
    }

    @Override
    public Object find(Long id, String dictType) {
	if (dictType.equals("address")) {
	    return addressRepository.findOne(id);
	}
	if (dictType.equals("transport")) {
	    return transportRepository.findOne(id);
	}
	if (dictType.equals("product")) {
	    return productRepository.findOne(id);
	}
	if (dictType.equals("store")) {
	    return storeRepository.findOne(id);
	}
	return null;
    }

    @Override
    public void update(Long id, String name, String dictType) {
	if (dictType.equals("address")) {
	    Address entity = addressRepository.findOne(id);
	    entity.setFullAddress(name);
	    addressRepository.save(entity);
	}
	if (dictType.equals("transport")) {
	    Transport entity = transportRepository.findOne(id);
	    entity.setName(name);
	    transportRepository.save(entity);
	}
	if (dictType.equals("product")) {
	    Product entity = productRepository.findOne(id);
	    entity.setName(name);
	    productRepository.save(entity);
	}
	if (dictType.equals("store")) {
	    Store entity = storeRepository.findOne(id);
	    entity.setName(name);
	    storeRepository.save(entity);
	}
    }

    @Transactional
    @Override
    public Boolean delete(Long id, String dictType) {
	if (dictType.equals("address")) {
	    Address entity = addressRepository.findOne(id);
	    if (!entity.getShipments().isEmpty()) {
		return false;
	    }
	    addressRepository.delete(id);
	} else if (dictType.equals("transport")) {
	    Transport entity = transportRepository.findOne(id);
	    if (!entity.getShipments().isEmpty()) {
		return false;
	    }
	    transportRepository.delete(id);
	} else if (dictType.equals("product")) {
	    Product entity = productRepository.findOne(id);
	    if (entity.cannotDelete()) {
		return false;
	    }
	    productRepository.delete(id);
	} else if (dictType.equals("store")) {
	    Store entity = storeRepository.findOne(id);
	    if (entity.cannotDelete()) {
		return false;
	    }
	    storeRepository.delete(id);
	}
	return true;
    }

    @Override
    public void createProduct(String value, Boolean isGroup, Long groupId) {
	Product product = new Product();
	product.setName(value);
	if (isGroup) {
	    product.setProductType(ProductType.GROUP);
	} else {
	    if (groupId != null) {
		product.setParent(productRepository.findOne(groupId));
	    }
	    product.setProductType(ProductType.PLACER);
	}
	product = productRepository.save(product);
	ProductQuantity pq = new ProductQuantity();
	pq.setProduct(product);
	pq.setBagCount(0);
	pq.setProductCount(0D);
	productQuantityRepository.save(pq);

    }

    @Override
    public List<Product> getProductGroups() {
	return productRepository.findByProductType(ProductType.GROUP);
    }

    @Override
    public void updateProduct(Long id, String value, Long groupId) {
	Product entity = productRepository.findOne(id);
	entity.setName(value);
	entity.setParent(groupId != null ? productRepository.findOne(groupId) : null);
	productRepository.save(entity);
    }

    @Override
    public List<Product> findProductWithChildren(Long productId) {
	List<Product> res = new ArrayList<Product>();
	Product product = productRepository.findOne(productId);
	res.add(product);
	addChildren(product, res);
	return res;
    }

    private void addChildren(Product entity, List<Product> result) {
	result.addAll(entity.getChildren());
	for (Product p : entity.getChildren()) {
	    addChildren(p, result);
	}
    }
}
