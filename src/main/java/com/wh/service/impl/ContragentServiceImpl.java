package com.wh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Contragent;
import com.wh.repositories.AddressRepository;
import com.wh.repositories.ContragentRepository;
import com.wh.service.ContragentService;

@Service
public class ContragentServiceImpl implements ContragentService {
	
	@Resource
	ContragentRepository contragentRepository;
	
	@Resource
	AddressRepository addressRepository;

	@Override
	public List<Contragent> findAll() {
		return (List<Contragent>) contragentRepository.findAll();
	}

	@Override
	public void save(String name, Long addressId) {
		Contragent entity = new Contragent();
		entity.setIsActive(true);
		entity.setCreateDate(new Date());
		entity.setName(name);
		entity.getAddressList().add(addressRepository.findOne(addressId));
		contragentRepository.save(entity);		
	}

	@Override
	public Contragent find(Long id) {
		return contragentRepository.findOne(id);
	}

}
