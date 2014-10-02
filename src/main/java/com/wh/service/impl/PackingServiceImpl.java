package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Packing;
import com.wh.entity.Product;
import com.wh.entity.ProductQuantity;
import com.wh.entity.ProductType;
import com.wh.repositories.PackingRepository;
import com.wh.repositories.ProductQuantityRepository;
import com.wh.repositories.ProductRepository;
import com.wh.service.PackingService;
import com.wh.utils.Utils;

@Service
public class PackingServiceImpl implements PackingService {

    @Resource
    private PackingRepository packingRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ProductQuantityRepository productQuantityRepository;

    @Override
    public List<Packing> findAll() {
	return (List<Packing>) packingRepository.findAll();
    }

    @Override
    public void save(String date, Long productId, Double productCount) {
	Product product = productRepository.findOne(productId);
	ProductQuantity pq = product.getProductQuantity();
	Double countInKg = productCount * 1000;
	Double countBagDouble = countInKg / 50;
	Integer bagCount = countBagDouble.intValue();
	// TODO тут не должно быть пустое количество
	pq.setProductCount(pq.getProductCount() != null ? pq.getProductCount() - productCount : 0 - productCount);
	pq.setBagCount(pq.getBagCount() != null ? pq.getBagCount() + bagCount : bagCount);
	productQuantityRepository.save(pq);

	List<Product> bags = productRepository.findByProductType(ProductType.BAG);
	Product bag = bags.get(0);
	ProductQuantity pqBag = bag.getProductQuantity();
	pqBag.setBagCount(pqBag.getBagCount() - bagCount);
	productQuantityRepository.save(pqBag);

	Packing entity = new Packing();
	entity.setCreateDate(Utils.parse(date));
	entity.setProductCount(productCount);
	entity.setProduct(product);
	entity.setBagCount(bagCount);
	entity.setBagResidue(pqBag.getBagCount());
	packingRepository.save(entity);
    }

    @Override
    public Boolean delete(Long id) {
	Packing current = packingRepository.findOne(id);

	List<Product> bags = productRepository.findByProductType(ProductType.BAG);
	Product bag = bags.get(0);
	ProductQuantity pqBag = bag.getProductQuantity();
	pqBag.setBagCount(pqBag.getBagCount() + current.getBagCount());
	productQuantityRepository.save(pqBag);

	ProductQuantity pq = current.getProduct().getProductQuantity();
	pq.setBagCount((pq.getBagCount() == null ? 0 : pq.getBagCount()) - current.getBagCount());
	pq.setProductCount((pq.getProductCount() == null ? 0 : pq.getProductCount()) + current.getProductCount());
	productQuantityRepository.save(pq);

	packingRepository.delete(current);
	return true;
    }

}
