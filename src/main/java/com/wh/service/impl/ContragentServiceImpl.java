package com.wh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
    private ContragentRepository contragentRepository;

    @Resource
    private AddressRepository addressRepository;

    @Override
    public List<Contragent> findAll() {
	Order order = new Order(Direction.ASC, "name");
	return (List<Contragent>) contragentRepository.findAll(new Sort(order));
    }

    @Override
    public void save(String name, String address1, String address2, String address3, String address4, String address5) {
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

    private void addAddress(Contragent entity, String addressName) {
	if (StringUtils.isBlank(addressName)) {
	    return;
	}
	Address address = new Address();
	address.setFullAddress(addressName);
	entity.getAddressList().add(addressRepository.save(address));
    }

    private void addAddress(Contragent entity, Long addressId, String address1Name, List<Address> oldList) {
	Address address;
	if (addressId != null) {
	    address = addressRepository.findOne(addressId);
	} else {
	    address = new Address();
	}
	if (StringUtils.isNotBlank(address1Name)) {
	    oldList.remove(address);
	    address.setFullAddress(address1Name);
	    entity.getAddressList().add(addressRepository.save(address));
	}
    }

    @Override
    public Contragent find(Long id) {
	return contragentRepository.findOne(id);
    }

    @Transactional
    @Override
    public void save(Long id, String name, Long address1, Long address2, Long address3, Long address4, Long address5,
	    String address1Name, String address2Name, String address3Name, String address4Name, String address5Name) {
	Contragent entity = find(id);
	entity.setName(name);
	List<Address> oldList = new ArrayList<Address>(entity.getAddressList());
	entity.getAddressList().clear();
	addAddress(entity, address1, address1Name, oldList);
	addAddress(entity, address2, address2Name, oldList);
	addAddress(entity, address3, address3Name, oldList);
	addAddress(entity, address4, address4Name, oldList);
	addAddress(entity, address5, address5Name, oldList);
	contragentRepository.save(entity);
	for (Address addr : oldList) {
	    if (addr.getShipments().isEmpty()) {
		addressRepository.delete(addr);
	    }
	}
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
