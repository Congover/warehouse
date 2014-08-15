package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Address;
import com.wh.entity.Product;
import com.wh.entity.ProductType;
import com.wh.entity.Store;
import com.wh.entity.Transport;
import com.wh.repositories.AddressRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.repositories.TransportRepository;
import com.wh.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Resource
	AddressRepository addressRepository;
	
	@Resource
	TransportRepository transportRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	StoreRepository storeRepository;
	
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
		if(cls.isAssignableFrom(Address.class)) {
			Address entity = new Address();
			entity.setFullAddress(value);
			addressRepository.save(entity);
		} else if(cls.isAssignableFrom(Transport.class)) {
			Transport entity = new Transport();
			entity.setName(value);
			transportRepository.save(entity);
		} else if(cls.isAssignableFrom(Store.class)) {
			Store entity = new Store();
			entity.setName(value);
			storeRepository.save(entity);
		}
	}

	@Override
	public void createProduct(String value, Integer productType) {
		if(productType == null) {
			return;
		}
		ProductType type = ProductType.getProductType(productType);
		if(type == null) {
			return;
		}
		Product entity = new Product();
		entity.setName(value);
		entity.setProductType(type);
		productRepository.save(entity);
	}

	@Override
	public List<Product> getAvailibleProductForPacking() {
		return productRepository.findByProductType(ProductType.PLACER);
	}

}
