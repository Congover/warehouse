package com.wh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wh.entity.Address;
import com.wh.entity.Contragent;
import com.wh.entity.Incoming;
import com.wh.entity.Packing;
import com.wh.entity.Product;
import com.wh.entity.Shipment;
import com.wh.entity.Store;
import com.wh.entity.Transport;

//TODO
public class DataTableModel {
	
	private Integer draw;
	
	private Integer recordsTotal;
	
	private Integer recordsFiltered;
	
	private List<Map<String, Object>> data;
	
	public DataTableModel(List<?> list, Integer draw, Integer lenght, Integer start, Class<?> cls) {
		this.setDraw(draw);
		this.setRecordsTotal(list.size());
		//TODO - because we don't use search in the table.
		this.setRecordsFiltered(this.getRecordsTotal());
		createData(list, lenght, start, cls);	
	}
	
	private void createData(List<?> list, Integer length, Integer start, Class<?> cls) {
		data = new ArrayList<Map<String,Object>>();
		if(length == -1) {
			for(Object obj : list) {
				data.add(createRow(obj, cls));				
			}
		} else {
			int needCout = start+length > list.size() ? list.size() : start+length;
			for(int i = start; i < needCout; i++) {
				data.add(createRow(list.get(i), cls));				
			}			
		}
	}
	
	private Map<String, Object> createRow(Object object, Class<?> cls) {
		Map<String, Object> row = new HashMap<String, Object>();
		if(cls.isAssignableFrom(Contragent.class)) {
			row.put("name", ((Contragent) object).getName());
			row.put("address", getFullAddresses(((Contragent) object).getAddressList()));
			return row;			
		}
		if(cls.isAssignableFrom(Incoming.class)) {
			Incoming entity = (Incoming) object;
			row.put("date", entity.getCreateDate());
			row.put("contractor", entity.getContragent().getName());
			row.put("product", entity.getProduct().getName());
			row.put("count", entity.getProductCount());
			row.put("store", entity.getStore().getName());
			row.put("comment", entity.getComment());
			return row;
		}
		if(cls.isAssignableFrom(Shipment.class)) {
			Shipment entity = (Shipment) object;
			row.put("date", entity.getCreateDate());
			row.put("contractor", entity.getContragent().getName());
			row.put("product", entity.getProduct().getName());
			row.put("count", entity.getProductCount());
			row.put("store", entity.getStore().getName());
			row.put("transport", entity.getTransport().getName());
			row.put("address", entity.getAddress().getFullAddress());
			row.put("comment", entity.getComment());
			return row;			
		}
		if(cls.isAssignableFrom(Address.class)) {
			row.put("name", ((Address) object).getFullAddress());
			return row;			
		}
		if(cls.isAssignableFrom(Transport.class)) {
			row.put("name", ((Transport) object).getName());
			return row;			
		}
		if(cls.isAssignableFrom(Product.class)) {
			row.put("name", ((Product) object).getName());
			return row;
		}
		if(cls.isAssignableFrom(Store.class)) {
			row.put("name", ((Store) object).getName());
			return row;
		}
		if(cls.isAssignableFrom(Packing.class)) {
			Packing entity = (Packing) object;
			row.put("date", entity.getCreateDate());
			row.put("product", entity.getProduct().getName());
			row.put("prod_coutn", entity.getProductCount());
			row.put("bag_count", entity.getBagCount());
			row.put("bag_residue", entity.getBagResidue());
			return row;
		}
		return new HashMap<String, Object>();
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
