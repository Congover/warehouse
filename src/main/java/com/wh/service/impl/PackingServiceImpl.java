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
	PackingRepository packingRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	ProductQuantityRepository productQuantityRepository;

	@Override
	public List<Packing> findAll() {
		return (List<Packing>) packingRepository.findAll();
	}

	@Override
	public void save(String date, Long productId, Double productCount) {
		Product product = productRepository.findOne(productId);
		ProductQuantity pq = product.getProductQuantity();
		Double countInKg = productCount * 1000;
		Double countBugDouble = countInKg / 50;
		Integer bugCount = countBugDouble.intValue();
		pq.setProductCount(pq.getProductCount() - productCount);
		pq.setBagCount(pq.getBagCount() + bugCount);
		productQuantityRepository.save(pq);
		
		List<Product> bags = productRepository.findByProductType(ProductType.BAG);
		Product bag = bags.get(0);
		ProductQuantity pqBag = bag.getProductQuantity();
		pqBag.setBagCount(pqBag.getBagCount() - bugCount);
		productQuantityRepository.save(pqBag);
		
		Packing entity = new Packing();
		entity.setCreateDate(Utils.getInstance().parse(date));
		entity.setProductCount(productCount);
		entity.setProduct(product);
		entity.setBagCount(bugCount);
		entity.setBagResidue(pqBag.getBagCount());
		Double bagCount = productCount * 10;
		entity.setBagCount(bagCount.intValue());
	}

}
