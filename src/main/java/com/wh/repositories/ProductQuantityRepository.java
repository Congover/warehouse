package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.ProductQuantity;

@Repository
public interface ProductQuantityRepository extends CrudRepository<ProductQuantity, Long> {

}
