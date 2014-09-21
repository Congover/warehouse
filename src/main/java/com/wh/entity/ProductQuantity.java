package com.wh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tPRODUCT_QUANTITY")
public class ProductQuantity extends BaseEntity {

    private static final long serialVersionUID = 1394840906065751009L;

    @Id
    @Column(name = "PRODUCT_QUANTITY_ID")
    @GeneratedValue
    private Long productQuantityId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRODUCT_COUNT", nullable = true)
    private Double productCount;

    @Column(name = "PRODUCT_BAG", nullable = true)
    private Integer bagCount;

    public ProductQuantity() {
    }

    public Long getProductQuantityId() {
	return productQuantityId;
    }

    public void setProductQuantityId(Long productQuantityId) {
	this.productQuantityId = productQuantityId;
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
}
