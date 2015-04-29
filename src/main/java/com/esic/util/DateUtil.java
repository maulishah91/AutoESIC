package com.esic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.esic.domain.ESICDate;

public class DateUtil {

	
	
	public static final SimpleDateFormat mmmFormat = new SimpleDateFormat("MMM");
	public static final SimpleDateFormat ddMMyyFormat = new SimpleDateFormat("dd/MM/yy");

	
	
	/**
	 * {@value #ddMMyyFormat} Format {@link #ddMMyyFormat}
	 * @param date
	 * @return
	 */
	public static ESICDate getDate(String date) {
		try {
			ESICDate esicDate = new ESICDate(date);
			
			return esicDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
}
