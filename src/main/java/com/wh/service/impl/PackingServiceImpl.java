package com.wh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.wh.entity.Packing;
import com.wh.entity.Product;
import com.wh.entity.Store;
import com.wh.repositories.PackingRepository;
import com.wh.repositories.ProductRepository;
import com.wh.repositories.StoreRepository;
import com.wh.service.PackingService;
import com.wh.utils.Utils;

@Service
public class PackingServiceImpl implements PackingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PackingRepository packingRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private StoreRepository storeRepository;

    @Override
    public List<Packing> findAll() {
	return (List<Packing>) packingRepository.findAll();
    }

    @Override
    public void save(String date, Long productId, Long packedProductId, Double productCount, Long storeId) {
	Product product = productRepository.findOne(productId);
	Product packedProduct = productRepository.findOne(packedProductId);
	Store store = storeRepository.findOne(storeId);
	Double countInKg = productCount * 1000;
	Double countBagDouble = countInKg / 50;
	Integer bagCount = countBagDouble.intValue();
	Packing entity = new Packing();
	entity.setCreateDate(Utils.parse(date));
	entity.setProductCount(productCount);
	entity.setProduct(product);
	entity.setBagCount(bagCount);
	entity.setPackedProduct(packedProduct);
	entity.setStore(store);
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
    public void update(Long id, String date, Long productId, Double productCount, Long packedProductId, Long storeId) {
	Packing entity = packingRepository.findOne(id);
	Double countInKg = productCount * 1000;
	Double countBagDouble = countInKg / 50;
	Integer bagCount = countBagDouble.intValue();
	entity.setCreateDate(Utils.parse(date));
	entity.setProductCount(productCount);
	entity.setProduct(productRepository.findOne(productId));
	entity.setBagCount(bagCount);
	entity.setPackedProduct(productRepository.findOne(packedProductId));
	entity.setStore(storeRepository.findOne(storeId));
	packingRepository.save(entity);
    }

    @Override
    public double findProductSum(Date date, Long productId, Long storeId) {
	TypedQuery<Double> query = entityManager.createNamedQuery(Packing.FIND_PRODUCT_SUM_QUERY, Double.class);
	query.setParameter("date", date, TemporalType.DATE);
	query.setParameter("product", productRepository.findOne(productId));
	query.setParameter("store", storeRepository.findOne(storeId));
	Double res = query.getSingleResult();
	return res != null ? res.doubleValue() : 0;
    }

    @Override
    public double findPackedProductSum(Date date, Long productId, Long storeId) {
	TypedQuery<Long> query = entityManager.createNamedQuery(Packing.FIND_BAG_SUM_QUERY, Long.class);
	query.setParameter("date", date, TemporalType.DATE);
	query.setParameter("product", productRepository.findOne(productId));
	query.setParameter("store", storeRepository.findOne(storeId));
	Long res = query.getSingleResult();
	return res != null ? res.doubleValue() : 0;
    }
}
