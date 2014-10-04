package com.wh.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wh.entity.Incoming;

public interface IncomingService {

    List<Incoming> findAll();

    void save(String date, Long contragentId, Long productId, Double productCount, Long storeId, String comment);

    Incoming find(Long id);

    void update(Long id, String date, Long contragentId, Long storeId, String comment);

    HSSFWorkbook generateReport(String dateStart, String dateEnd, Long contragentId, Long productId, Long storeId);

    Boolean delete(Long id);

    List<Incoming> findByContragentId(Long contragentId);

}
