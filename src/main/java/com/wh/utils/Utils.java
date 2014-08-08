package com.wh.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Utils instance = new Utils();
	
	private Utils() {
	}
	
	public static Utils getInstance() {
		return instance;		
	}
	
	public Date parse(String date) {
		try {
			return df.parse(date);
		} catch (ParseException e) {
			//TODO ???
			return new Date();
		}		
	}
}
