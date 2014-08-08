package com.wh.service;

import java.util.List;

import com.wh.entity.Shipment;

public interface ShipmentService {
	
	List<Shipment> findAll();

	void save(String date, Long contragentId, Long productId,
			Double productCount, Long storeId, Long transportId, Long addressId, Boolean paymentType, String comment);
}
