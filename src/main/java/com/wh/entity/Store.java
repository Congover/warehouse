package com.wh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tSTORE")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STORE_ID")
    @GeneratedValue
    private Long storeId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Shipment> shipments;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Incoming> incomings;

    public Store() {
    }

    public Long getStoreId() {
	return storeId;
    }

    public void setStoreId(Long storeId) {
	this.storeId = storeId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Incoming> getIncomings() {
	if (incomings == null) {
	    incomings = new ArrayList<Incoming>();
	}
	return incomings;
    }

    public List<Shipment> getShipments() {
	if (shipments == null) {
	    shipments = new ArrayList<Shipment>();
	}
	return shipments;
    }

    public boolean cannotDelete() {
	return !getIncomings().isEmpty() || !getShipments().isEmpty();
    }

}
