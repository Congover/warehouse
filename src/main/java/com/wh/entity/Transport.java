package com.wh.entity;

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
@Table(name = "tTRANSPORT")
public class Transport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TRANSPORT_ID")
    @GeneratedValue
    private Long transportId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY)
    private List<Shipment> shipments;

    public Transport() {
    }

    public Long getTransportId() {
	return transportId;
    }

    public void setTransportId(Long transportId) {
	this.transportId = transportId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Shipment> getShipments() {
	if (shipments == null) {
	    shipments = new ArrayList<Shipment>();
	}
	return shipments;
    }

}
