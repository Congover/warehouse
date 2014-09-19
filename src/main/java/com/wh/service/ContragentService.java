package com.wh.service;

import java.util.List;

import com.wh.entity.Contragent;

public interface ContragentService {

    List<Contragent> findAll();

    void save(String name, Long address1, Long address2, Long address3, Long address4, Long address5);

    void save(Long id, String name, Long address1, Long address2, Long address3, Long address4, Long address5);

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
