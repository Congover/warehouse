package com.wh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wh.entity.Address;
import com.wh.entity.Contragent;

//TODO отрефакторить, т.к. нам нужно проверять на пустые значения + количество
public class DataTableModel {
	
	private Integer draw;
	
	private Integer recordsTotal;
	
	private Integer recordsFiltered;
	
	private List<Map<String, Object>> data;
	
	public DataTableModel(List<Contragent> list, Integer draw, Integer lenght, Integer start) {
		this.setDraw(draw);
		this.setRecordsTotal(list.size());
		createData(list, lenght, start);		
	}

	private void createData(List<Contragent> list, Integer length, Integer start) {
		data = new ArrayList<Map<String,Object>>();
		if(length == -1) {
			for(Contragent entity : list) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("name", entity.getName());
				row.put("address", getFullAddresses(entity.getAddressList()));
				data.add(row);
			}			
		} else {
			int needCout = start+length > list.size() ? list.size() : start+length;
			for(int i = start; i < needCout; i++) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("name", list.get(i).getName());
				row.put("address", getFullAddresses(list.get(i).getAddressList()));
				data.add(row);
				
			}			
		}
		recordsFiltered = recordsTotal;		
	}
	
	private String getFullAddresses(List<Address> addressList) {
		if(addressList == null || addressList.isEmpty()) {
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (Address address : addressList) {
			sb.append("[").append(address.getFullAddress()).append("]").append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

}
