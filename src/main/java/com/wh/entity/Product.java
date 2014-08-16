package com.wh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tPRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue
	private Long productId;

	@Column(name = "NAME")
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "PRODUCT_TYPE")
	private ProductType productType;    
	
	@OneToOne(mappedBy = "product", fetch = FetchType.EAGER)
    private ProductQuantity productQuantity;

	public Product() {		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	public ProductQuantity getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(ProductQuantity productQuantity) {
		this.productQuantity = productQuantity;
	}

}
