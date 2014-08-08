package com.wh.service;

import java.util.List;

import com.wh.entity.Incoming;

public interface IncomingService {
	
	List<Incoming> findAll();

	void save(String date, Long contragentId, Long productId,
			Double productCount, Long storeId, String comment);

}
