package com.wh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Contragent;
import com.wh.repositories.ContragentRepository;

@Service
public class ContragentServiceImpl implements ContragentService {
	
	@Resource
	ContragentRepository contragentRepository;

	@Override
	public List<Contragent> findAll() {
		return (List<Contragent>) contragentRepository.findAll();
	}

}
