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

    List<Product> getAvailibleProductForPacking();

    /**
     * Address findAddress(Long id);
     * 
     * Product findProduct(Long id);
     * 
     * Transport findTransport(Long id);
     * 
     * Store findStore(Long id);
     */

    Object find(Long id, String dictType);

    void update(Long id, String name, String dictType);

    Boolean delete(Long id, String dictType);

}
