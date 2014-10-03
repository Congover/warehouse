package com.wh.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Incoming;

@Repository
public interface IncomingRepository extends CrudRepository<Incoming, Long> {

    List<Incoming> findByContragentContragentId(Long contragentId);
}
