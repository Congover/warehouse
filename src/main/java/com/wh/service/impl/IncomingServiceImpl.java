package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Incoming;
import com.wh.entity.Product;
import com.wh.entity.ProductQuantity;
import com.wh.entity.ProductType;
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.IncomingRepository;
import com.wh.repositories.ProductQuantityRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.service.IncomingService;
import com.wh.utils.Utils;

@Service
public class IncomingServiceImpl implements IncomingService {
	
	@Resource
	IncomingRepository incomingRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	StoreRepository storeRepository;
	
	@Resource
	ContragentRepository contragentRepository;
	
	@Resource
	ProductQuantityRepository productQuantityRepository;

	@Override
	public List<Incoming> findAll() {
		return (List<Incoming>) incomingRepository.findAll();
	}

	@Override
	public void save(String date, Long contragentId, Long productId,
			Double productCount, Long storeId, String comment) {
		Product product = productRepository.findOne(productId);
		ProductQuantity pq = product.getProductQuantity();
		if(ProductType.BAG.equals(product.getProductType())) {
			pq.setBagCount(productCount.intValue());
		} else {
			pq.setProductCount(productCount);
		}
		productQuantityRepository.save(pq);
		
		Incoming entity = new Incoming();
		entity.setCreateDate(Utils.getInstance().parse(date));
		entity.setContragent(contragentRepository.findOne(contragentId));
		entity.setProduct(product);
		entity.setProductCount(productCount);
		entity.setStore(storeRepository.findOne(storeId));
		entity.setComment(comment);
		incomingRepository.save(entity);
	}

}
