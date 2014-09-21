package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Incoming;

public class IncomingDataTableModel extends BaseDataTableModel<Incoming> {

    public IncomingDataTableModel(List<Incoming> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Incoming entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("date", entity.getCreateDate());
	row.put("contractor", entity.getContragent().getName());
	row.put("product", entity.getProduct().getName());
	row.put("count", entity.getProductCount());
	row.put("store", entity.getStore().getName());
	row.put("comment", entity.getComment());
	row.put("DT_RowId", entity.getIncomingId());
	return row;
    }

}
