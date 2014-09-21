package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Address;

public class AddressDataTableModel extends BaseDataTableModel<Address> {

    public AddressDataTableModel(List<Address> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Address entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("name", entity.getFullAddress());
	row.put("DT_RowId", entity.getAddressId());
	return row;
    }
}
