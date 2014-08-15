package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Packing;

@Repository
public interface PackingRepository extends CrudRepository<Packing, Long> {

}
