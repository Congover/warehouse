package com.wh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wh.entity.BaseEntity;

public abstract class BaseDataTableModel<T extends BaseEntity> {

    private Integer draw;

    private Integer recordsTotal;

    private Integer recordsFiltered;

    private List<Map<String, Object>> data;

    public BaseDataTableModel(List<T> list, Integer draw, Integer length, Integer start) {
	this.setDraw(draw);
	this.setRecordsTotal(list.size());
	// TODO - because we don't use search in the table.
	this.setRecordsFiltered(this.getRecordsTotal());
	createData(list, length, start);
    }

    private void createData(List<T> list, Integer length, Integer start) {
	data = new ArrayList<Map<String, Object>>();
	if (length == -1) {
	    for (T obj : list) {
		data.add(createRow(obj));
	    }
	} else {
	    int needCout = start + length > list.size() ? list.size() : start + length;
	    for (int i = start; i < needCout; i++) {
		data.add(createRow(list.get(i)));
	    }
	}
    }

    protected abstract Map<String, Object> createRow(T entity);

    public Integer getDraw() {
	return draw;
    }

    public void setDraw(Integer value) {
	this.draw = value;
    }

    public Integer getRecordsTotal() {
	return recordsTotal;
    }

    public void setRecordsTotal(Integer value) {
	this.recordsTotal = value;
    }

    public Integer getRecordsFiltered() {
	return recordsFiltered;
    }

    public void setRecordsFiltered(Integer value) {
	this.recordsFiltered = value;
    }

    public List<Map<String, Object>> getData() {
	return data;
    }

    public void setData(List<Map<String, Object>> value) {
	this.data = value;
    }

}
