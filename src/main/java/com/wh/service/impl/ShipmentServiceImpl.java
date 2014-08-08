package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Incoming;
import com.wh.entity.Shipment;
import com.wh.repositories.AddressRepository;
import com.wh.repositories.ContragentRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.ShipmentRepository;
import com.wh.repositories.StoreRepository;
import com.wh.repositories.TransportRepository;
import com.wh.service.ShipmentService;
import com.wh.utils.Utils;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Resource
	ShipmentRepository shipmentRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	StoreRepository storeRepository;
	
	@Resource
	ContragentRepository contragentRepository;
	
	@Resource
	AddressRepository addressRepository;
	
	@Resource
	TransportRepository transportRepository;

	@Override
	public List<Shipment> findAll() {
		return (List<Shipment>) shipmentRepository.findAll();
	}

	@Override
	public void save(String date, Long contragentId, Long productId,
			Double productCount, Long storeId, Long transportId, Long addressId, Boolean paymentType, String comment) {
		Shipment entity = new Shipment();
		entity.setCreateDate(Utils.getInstance().parse(date));
		entity.setContragent(contragentRepository.findOne(contragentId));
		entity.setProduct(productRepository.findOne(productId));
		entity.setProductCount(productCount);
		entity.setStore(storeRepository.findOne(storeId));
		entity.setAddress(addressRepository.findOne(addressId));
		entity.setTransport(transportRepository.findOne(transportId));
		entity.setPaymentType(paymentType);
		entity.setComment(comment);
		shipmentRepository.save(entity);
	}

}
