package com.wh.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Contragent;

@Repository
public interface ContragentRepository extends CrudRepository<Contragent, Long> {

    List<Contragent> findAll(Sort sort);

}
