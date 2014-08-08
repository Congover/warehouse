package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Transport;

@Repository
public interface TransportRepository extends CrudRepository<Transport, Long>{

}
