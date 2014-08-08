package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Shipment;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long>{

}
