package com.wh.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Shipment;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

    List<Shipment> findByContragentContragentId(Long contragentId);

}
