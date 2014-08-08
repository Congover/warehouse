package com.wh.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Incoming;
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.IncomingRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.service.IncomingService;
import com.wh.utils.Utils;

@Service
public class IncomingServiceImpl implements IncomingService {
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	IncomingRepository incomingRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	StoreRepository storeRepository;
	
	@Resource
	ContragentRepository contragentRepository;

	@Override
	public List<Incoming> findAll() {
		return (List<Incoming>) incomingRepository.findAll();
	}

	@Override
	public void save(String date, Long contragentId, Long productId,
			Double productCount, Long storeId, String comment) {
		Incoming entity = new Incoming();
		entity.setCreateDate(Utils.getInstance().parse(date));
		entity.setContragent(contragentRepository.findOne(contragentId));
		entity.setProduct(productRepository.findOne(productId));
		entity.setProductCount(productCount);
		entity.setStore(storeRepository.findOne(storeId));
		entity.setComment(comment);
		incomingRepository.save(entity);
	}

}
