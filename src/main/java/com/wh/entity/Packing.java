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
@Table(name = "tPACKING")
public class Packing extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PAKING_ID")
    @GeneratedValue
    private Long packingId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRODUCT_COUNT", nullable = false)
    private Double productCount;

    @Column(name = "BAG_COUNT", nullable = false)
    private Integer bagCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PACKED_PRODUCT_ID")
    private Product packedProduct;

    public Packing() {
    }

    public Long getPackingId() {
	return packingId;
    }

    public void setPackingId(Long packingId) {
	this.packingId = packingId;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
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

    public Integer getBagCount() {
	return bagCount;
    }

    public void setBagCount(Integer bagCount) {
	this.bagCount = bagCount;
    }

    public Product getPackedProduct() {
	return packedProduct;
    }

    public void setPackedProduct(Product packedProduct) {
	this.packedProduct = packedProduct;
    }

}
