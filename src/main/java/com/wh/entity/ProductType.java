package com.wh.entity;

public enum ProductType {

    BAG("мешок"),
    PLACER("россыпью"),
    GROUP("тип продукта");

    private String value;
    private int id;

    ProductType(String value) {
	this.value = value;
    }

    private String getValue() {
	return value;
    }

    public static ProductType getProductType(int id) {
	for (ProductType type : values()) {
	    if (id == type.ordinal()) {
		return type;
	    }
	}
	return null;
    }

}
