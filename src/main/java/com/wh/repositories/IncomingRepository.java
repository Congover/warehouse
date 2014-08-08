package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.Incoming;

@Repository
public interface IncomingRepository extends CrudRepository<Incoming, Long> {
}
