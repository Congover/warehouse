package com.wh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wh.entity.Address;
import com.wh.entity.Contragent;

public class ContragentDataTableModel extends BaseDataTableModel<Contragent> {

    public ContragentDataTableModel(List<Contragent> list, Integer draw, Integer length, Integer start) {
	super(list, draw, length, start);
    }

    @Override
    protected Map<String, Object> createRow(Contragent entity) {
	Map<String, Object> row = new HashMap<String, Object>();
	row.put("name", entity.getName());
	row.put("DT_RowId", entity.getContragentId());
	row.put("address", getFullAddresses(entity.getAddressList()));
	return row;
    }

    private String getFullAddresses(List<Address> addressList) {
	if (addressList == null || addressList.isEmpty()) {
	    return StringUtils.EMPTY;
	}
	StringBuilder sb = new StringBuilder();
	for (Address address : addressList) {
	    sb.append("[").append(address.getFullAddress()).append("]").append(",");
	}
	return sb.substring(0, sb.length() - 1);
    }

}
