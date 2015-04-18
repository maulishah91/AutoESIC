package com.esic.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

	public ESICDate(String date) throws ParseException {

		setdate(date);
	}

	SimpleDateFormat mmmDateFormat = new SimpleDateFormat("MMM");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

	public void setdate(String dateString) throws ParseException {

		Date d = simpleDateFormat.parse(dateString);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);

		date = cal.get(Calendar.DATE);
		year = cal.get(GregorianCalendar.YEAR);
		month = mmmDateFormat.format(cal.getTime());

	}

}
