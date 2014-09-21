package com.wh.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wh.entity.Shipment;

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

    // TODO Ugly code, use enum in Shipment instead boolean, use i18n
    // values instead hardcoded
    public static String getShipmentPaymentType(Shipment shipment) {
	if (shipment.getPaymentType() != null && shipment.getPaymentType().booleanValue()) {
	    return "Наличный";
	} else {
	    return "Безналичный";
	}

    }

}
