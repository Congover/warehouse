package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Transport;

public class TransportDataTableModel extends BaseDataTableModel<Transport> {

    public TransportDataTableModel(List<Transport> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Transport entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("name", entity.getName());
	row.put("DT_RowId", entity.getTransportId());
	return row;
    }

}
