package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Contragent;

@Repository
public interface ContragentRepository extends CrudRepository<Contragent, Long> {

}
