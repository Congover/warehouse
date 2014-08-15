package com.wh.service;

import java.util.List;

import com.wh.entity.Address;
import com.wh.entity.Product;
import com.wh.entity.Store;
import com.wh.entity.Transport;

public interface DictionaryService {
	
	List<Address> getAdresses();

	List<Transport> getTransports();

	List<Product> getProducts();

	List<Store> getStories();
	
	void create(Class<?> cls, String value);
	
	void createProduct(String value, Integer productType);
	
	List<Product> getAvailibleProductForPacking();

}
