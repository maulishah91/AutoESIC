package com.esic.domain;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.esic.util.DateUtil;

public class ESICDate {

	private int date;
	private String month;
	private int year;

	
	
	/**
	 * 1-31
	 */
	public int getDate() {
		return date;
	}

	/**
	 * mmm ( jan dec )
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * yyyy
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 
	 * {@link DateUtil#ddMMyyFormat}
	 * @param date
	 * @throws ParseException
	 */
	public ESICDate(String date) throws ParseException {

		setdate(date);
	}


	public void setdate(String dateString) throws ParseException {
		Date d = DateUtil.ddMMyyFormat.parse(dateString);
		setDate(d);
	}

	public void setDate(Date d) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		date = cal.get(Calendar.DATE);
		year = cal.get(GregorianCalendar.YEAR);
		month = DateUtil.mmmFormat.format(cal.getTime());
	}
	
	

}
