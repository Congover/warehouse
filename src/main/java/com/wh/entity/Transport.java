package com.wh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tTRANSPORT")
public class Transport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TRANSPORT_ID")
	@GeneratedValue
	private Long transportId;

	@Column(name = "NAME")
	private String name;
	
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
	

}
