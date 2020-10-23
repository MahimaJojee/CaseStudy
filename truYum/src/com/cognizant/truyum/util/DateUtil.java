package com.cognizant.truyum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date convertToDate(String date) throws Exception
	{
		Date new_date=new SimpleDateFormat("dd/MM/yyyy").parse(date); 
		return new_date;
	}
}
