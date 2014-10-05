package com.wh.service;

import java.util.Date;
import java.util.List;

import com.wh.entity.Packing;

public interface PackingService {

    List<Packing> findAll();

    void save(String date, Long productId, Long packedProductId, Double productCount, Long storeId);

    Boolean delete(Long id);

    Object find(Long id);

    void update(Long id, String date, Long productId, Double productCount, Long packedProductId, Long storeId);

    double findProductSum(Date date, Long productId, Long storeId);

    double findPackedProductSum(Date date, Long productId, Long storeId);

}
