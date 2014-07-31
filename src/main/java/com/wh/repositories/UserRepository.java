package com.wh.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wh.entity.User;

@Repository	
public interface UserRepository extends CrudRepository<User, Long> {

}
