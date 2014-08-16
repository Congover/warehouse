package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Packing;
import com.wh.repositories.PackingRepository;
import com.wh.repositories.ProductRepository;
import com.wh.service.PackingService;
import com.wh.utils.Utils;

@Service
public class PackingServiceImpl implements PackingService {
	
	@Resource
	PackingRepository packingRepository;
	
	@Resource
	ProductRepository productRepository;

	@Override
	public List<Packing> findAll() {
		return (List<Packing>) packingRepository.findAll();
	}

	@Override
	public void save(String date, Long productId, Double productCount) {
		Packing entity = new Packing();
		entity.setCreateDate(Utils.getInstance().parse(date));
		entity.setProductCount(productCount);
		entity.setProduct(productRepository.findOne(productId));
		Double bagCount = productCount * 10;
		entity.setBagCount(bagCount.intValue());
	}

}
