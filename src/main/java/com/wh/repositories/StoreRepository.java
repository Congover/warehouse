package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

}
