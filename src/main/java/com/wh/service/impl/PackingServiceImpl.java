package com.wh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wh.entity.Packing;
import com.wh.repositories.PackingRepository;
import com.wh.service.PackingService;

@Service
public class PackingServiceImpl implements PackingService {
	
	@Resource
	PackingRepository packingRepository;

	@Override
	public List<Packing> findAll() {
		return (List<Packing>) packingRepository.findAll();
	}

}
