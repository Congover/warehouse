package com.wh.service;

import java.util.List;

import com.wh.entity.Packing;

public interface PackingService {

    List<Packing> findAll();

    void save(String date, Long productId, Double productCount);

    Boolean delete(Long id);

}
