package com.wh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tINCOMING")
public class Incoming extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "INCOMMING_ID")
    @GeneratedValue
    private Long incomingId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRAGENT_ID")
    private Contragent contragent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRODUCT_COUNT", nullable = false)
    private Double productCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @Column(name = "COMMENT", nullable = true, length = 4000)
    private String comment;

    public Incoming() {
    }

    public Long getIncomingId() {
	return incomingId;
    }

    public void setIncomingId(Long incomingId) {
	this.incomingId = incomingId;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public Contragent getContragent() {
	return contragent;
    }

    public void setContragent(Contragent contragent) {
	this.contragent = contragent;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public Double getProductCount() {
	return productCount;
    }

    public void setProductCount(Double productCount) {
	this.productCount = productCount;
    }

    public Store getStore() {
	return store;
    }

    public void setStore(Store store) {
	this.store = store;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

}
