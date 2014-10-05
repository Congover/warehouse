package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Packing;
import com.wh.entity.Product;
import com.wh.repositories.PackingRepository;
import com.wh.repositories.ProductRepository;
import com.wh.service.PackingService;
import com.wh.utils.Utils;

@Service
public class PackingServiceImpl implements PackingService {

    @Resource
    private PackingRepository packingRepository;

    @Resource
    private ProductRepository productRepository;

    @Override
    public List<Packing> findAll() {
	return (List<Packing>) packingRepository.findAll();
    }

    @Override
    public void save(String date, Long productId, Long packedProductId, Double productCount) {
	Product product = productRepository.findOne(productId);
	Product packedProduct = productRepository.findOne(packedProductId);
	Double countInKg = productCount * 1000;
	Double countBagDouble = countInKg / 50;
	Integer bagCount = countBagDouble.intValue();
	Packing entity = new Packing();
	entity.setCreateDate(Utils.parse(date));
	entity.setProductCount(productCount);
	entity.setProduct(product);
	entity.setBagCount(bagCount);
	entity.setPackedProduct(packedProduct);
	packingRepository.save(entity);
    }

    @Override
    public Boolean delete(Long id) {
	packingRepository.delete(id);
	return true;
    }

    @Override
    public Object find(Long id) {
	return packingRepository.findOne(id);
    }

    @Override
    public void update(Long id, String date, Long productId, Double productCount, Long packedProductId) {
	Packing entity = packingRepository.findOne(id);
	Double countInKg = productCount * 1000;
	Double countBagDouble = countInKg / 50;
	Integer bagCount = countBagDouble.intValue();
	entity.setCreateDate(Utils.parse(date));
	entity.setProductCount(productCount);
	entity.setProduct(productRepository.findOne(productId));
	entity.setBagCount(bagCount);
	entity.setPackedProduct(productRepository.findOne(packedProductId));
	packingRepository.save(entity);
    }
}
