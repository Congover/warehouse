package com.wh.service;

import java.util.List;

import com.wh.entity.Shipment;

public interface ShipmentService {

    List<Shipment> findAll();

    void save(String date, Long contragentId, Long productId, Integer productCount, Long storeId, Long transportId,
	    Long addressId, Boolean paymentType, String comment);

    Shipment find(Long id);

    void update(Long id, String date, Long contragentId, Long storeId, Long transportId, Long addressId,
	    Boolean paymentType, String comment);
}
