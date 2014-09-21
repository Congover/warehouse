package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Store;

public class StoreDataTableModel extends BaseDataTableModel<Store> {

    public StoreDataTableModel(List<Store> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Store entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("name", entity.getName());
	row.put("DT_RowId", entity.getStoreId());
	return row;
    }

}
