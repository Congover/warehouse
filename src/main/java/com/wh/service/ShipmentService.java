package com.wh.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wh.entity.Shipment;

public interface ShipmentService {

    List<Shipment> findAll();

    void save(String date, Long contragentId, Long productId, Double productCount, Long storeId, Long transportId,
	    Long addressId, Boolean paymentType, String comment);

    Shipment find(Long id);

    void update(Long id, String date, Long contragentId, Long productId, Double productCount, Long storeId,
	    Long transportId, Long addressId, Boolean paymentType, String comment);

    HSSFWorkbook generateReport(String dateStart, String dateEnd, Long contragentId, Long productId, Long storeId,
	    Long transportId, Boolean paymentType);

    HSSFWorkbook generateBalanceReport(String dateStart, String dateEnd, Long productId, Long storeId);

    Boolean delete(Long id);

    List<Shipment> findByContragentId(Long contragentId);
}
