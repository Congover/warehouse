package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wh.entity.Shipment;
import com.wh.utils.Utils;

public class ShipmentDataTableModel extends BaseDataTableModel<Shipment> {

    public ShipmentDataTableModel(List<Shipment> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Shipment entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("date", entity.getCreateDate());
	row.put("contractor", entity.getContragent().getName());
	row.put("product", entity.getProduct().getName());
	row.put("count", entity.getProductCount());
	row.put("store", entity.getStore().getName());
	row.put("transport", entity.getTransport().getName());
	row.put("address", entity.getAddress().getFullAddress());
	row.put("paymentType", Utils.getShipmentPaymentType(entity.getPaymentType()));
	row.put("comment", entity.getComment());
	row.put("DT_RowId", entity.getShipmentId());
	return row;
    }

}
