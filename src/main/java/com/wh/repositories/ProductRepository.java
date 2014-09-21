package com.wh.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Product;
import com.wh.entity.ProductType;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByProductType(ProductType productType);

}
