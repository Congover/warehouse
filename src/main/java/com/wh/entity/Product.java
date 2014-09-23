package com.wh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tPRODUCT")
public class Product extends BaseEntity {

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

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Shipment> shipments;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Incoming> incomings;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Packing> packings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private Product parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Product> children;

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

    public List<Packing> getPackings() {
	if (packings == null) {
	    packings = new ArrayList<Packing>();
	}
	return packings;
    }

    public boolean cannotDelete() {
	return !getIncomings().isEmpty() || !getShipments().isEmpty() || !getPackings().isEmpty()
		|| !getChildren().isEmpty();
    }

    public Product getParent() {
	return parent;
    }

    public void setParent(Product parent) {
	this.parent = parent;
    }

    public List<Product> getChildren() {
	if (children == null) {
	    children = new ArrayList<Product>();
	}
	return children;
    }

    public void setChildren(List<Product> children) {
	this.children = children;
    }

}
