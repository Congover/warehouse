package com.wh.service;

import java.util.List;

import com.wh.entity.Packing;

public interface PackingService {

    List<Packing> findAll();

    void save(String date, Long productId, Long packedProductId, Double productCount);

    Boolean delete(Long id);

    Object find(Long id);

    void update(Long id, String date, Long productId, Double productCount, Long packedProductId);

}
