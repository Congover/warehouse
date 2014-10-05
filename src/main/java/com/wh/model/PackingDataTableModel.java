package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Packing;

public class PackingDataTableModel extends BaseDataTableModel<Packing> {

    public PackingDataTableModel(List<Packing> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Packing entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("date", entity.getCreateDate());
	row.put("product", entity.getProduct().getName());
	row.put("packedProduct", entity.getPackedProduct().getName());
	row.put("prod_coutn", entity.getProductCount());
	row.put("bag_count", entity.getBagCount());
	row.put("DT_RowId", entity.getPackingId());
	return row;
    }

}
