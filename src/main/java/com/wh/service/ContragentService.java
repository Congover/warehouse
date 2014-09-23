package com.wh.service;

import java.util.List;

import com.wh.entity.Contragent;

public interface ContragentService {

    List<Contragent> findAll();

    void save(String name, String address1, String address2, String address3, String address4, String address5);

    void save(Long id, String name, Long address1, Long address2, Long address3, Long address4, Long address5,
	    String address1Name, String address2Name, String address3Name, String address4Name, String address5Name);

    Contragent find(Long id);

    /**
     * Delete contragent
     * 
     * @param contragentId
     *            - ID;
     * @return true - if deleted;
     */
    Boolean delete(Long contragentId);

}
