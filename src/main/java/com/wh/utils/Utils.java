package com.wh.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final String DF = "yyyy-MM-dd";
    private static final String DTF = "yyyyMMddHHmmss";

    public static Date parse(String date) {
	try {
	    DateFormat df = new SimpleDateFormat(DF);
	    return df.parse(date);
	} catch (ParseException e) {
	    // TODO ???
	    return new Date();
	}
    }

    public static String convertDateToStr(Date date) {
	DateFormat df = new SimpleDateFormat(DF);
	return df.format(date);
    }

    public static String convertDateTimeToStr(Date dateTime) {
	DateFormat df = new SimpleDateFormat(DTF);
	return df.format(dateTime);
    }

}
