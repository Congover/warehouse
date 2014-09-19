package com.wh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.entity.Address;
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
    public void save(String name, Long address1, Long address2, Long address3, Long address4, Long address5) {
	Contragent entity = new Contragent();
	entity.setIsActive(true);
	entity.setCreateDate(new Date());
	entity.setName(name);
	entity.setAddressList(new ArrayList<Address>());
	addAddress(entity, address1);
	addAddress(entity, address2);
	addAddress(entity, address3);
	addAddress(entity, address4);
	addAddress(entity, address5);
	contragentRepository.save(entity);
    }

    private void addAddress(Contragent entity, Long address) {
	if (address != null) {
	    entity.getAddressList().add(addressRepository.findOne(address));
	}
    }

    @Override
    public Contragent find(Long id) {
	return contragentRepository.findOne(id);
    }

    @Override
    public void save(Long id, String name, Long address1, Long address2, Long address3, Long address4, Long address5) {
	Contragent entity = find(id);
	entity.setName(name);
	entity.getAddressList().clear();
	contragentRepository.save(entity);
	addAddress(entity, address1);
	addAddress(entity, address2);
	addAddress(entity, address3);
	addAddress(entity, address4);
	addAddress(entity, address5);
	contragentRepository.save(entity);
    }

    @Transactional
    @Override
    public Boolean delete(Long contragentId) {
	Contragent entity = find(contragentId);
	if (entity.getShipmentList().isEmpty() && entity.getIncomingList().isEmpty()) {
	    contragentRepository.delete(entity);
	    return true;
	}
	return false;
    }

}
