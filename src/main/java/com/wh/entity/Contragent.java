package com.wh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tCONTRAGENT")
public class Contragent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRAGENT_ID")
	@GeneratedValue
	private Long contragentId;

	@Column(name = "NAME")
	private String name;

    @Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tCONTRAGENT_ADDRESS", 
	joinColumns = {@JoinColumn(name = "CONTRAGENT_ID")}, 
	inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID")})
	private List<Address> addressList;

	public Long getContragentId() {
		return contragentId;
	}

	public void setContragentId(Long contragentId) {
		this.contragentId = contragentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Address> getAddressList() {
		if(addressList == null) {
			addressList = new ArrayList<Address>();
		}
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
}
