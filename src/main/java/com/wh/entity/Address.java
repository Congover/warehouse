package com.wh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="tADDRESS")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue
	private Long addressId;

	@Column(name = "FULLADDRESS", length=1000)
	private String fullAddress;

	@ManyToMany(mappedBy = "addressList", fetch = FetchType.LAZY)
	private List<Contragent> contragentList;

    
    public Address() {
    }

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public List<Contragent> getContragentList() {
		return contragentList;
	}

	public void setContragentList(List<Contragent> contragentList) {
		this.contragentList = contragentList;
	}
}
