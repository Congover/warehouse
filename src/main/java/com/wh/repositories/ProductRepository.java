package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
