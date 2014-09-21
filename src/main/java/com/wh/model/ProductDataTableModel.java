package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Product;

public class ProductDataTableModel extends BaseDataTableModel<Product> {

    public ProductDataTableModel(List<Product> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Product entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("name", entity.getName());
	row.put("DT_RowId", entity.getProductId());
	return row;

    }

}
