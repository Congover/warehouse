package com.wh.service;

import java.util.List;

import com.wh.entity.Contragent;

public interface ContragentService {
	
	List<Contragent> findAll();
	
	void save(String name, Long addressId);
	
	Contragent find(Long id);

}
