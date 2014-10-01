package com.wh.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ReportHelper {

    public static HSSFWorkbook createReport(String sheetName, List<String[]> reportParams, String[] columnNames,
	    List<String[]> data) {
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet(sheetName);
	int rowNum = 0;
	for (String[] param : reportParams) {
	    HSSFRow row = sheet.createRow(rowNum);
	    HSSFCell cell = row.createCell(0);
	    HSSFFont font = workbook.createFont();
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setFont(font);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue(param[0]);
	    cell = row.createCell(1);
	    cell.setCellValue(param[1]);
	    rowNum++;
	}
	rowNum++;
	HSSFRow headerRow = sheet.createRow(rowNum);
	for (int q = 0; q < columnNames.length; q++) {
	    HSSFCell cell = headerRow.createCell(q);
	    HSSFFont font = workbook.createFont();
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setFont(font);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue(columnNames[q]);
	}
	for (String[] record : data) {
	    rowNum++;
	    HSSFRow row = sheet.createRow(rowNum);
	    for (int q = 0; q < record.length; q++) {
		HSSFCell cell = row.createCell(q);
		try {
		    double d = Double.parseDouble(record[q]);
		    if (d < 0) {
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(cellStyle);
		    }
		} catch (Exception e) {
		    // no op
		}
		cell.setCellValue(record[q]);
	    }
	}
	for (int q = 0; q < columnNames.length; q++) {
	    sheet.autoSizeColumn(q);
	}
	return workbook;
    }

}
