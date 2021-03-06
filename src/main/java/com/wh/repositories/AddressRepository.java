package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
