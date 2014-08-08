package com.wh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	

}
