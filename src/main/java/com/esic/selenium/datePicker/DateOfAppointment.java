package com.esic.selenium.datePicker;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DateOfAppointment extends DatePickerMain{

	final static Logger logger = Logger.getLogger(DateOfAppointment.class);
	
	//doa body (like a root element for DOA datepicker) 
	//for successful initialisation of root, the datepicker field has to be clicked once on UI before loading this class
	@FindBy(id="ctl00_HomePageContent_cEDOA_body")
	WebElement root;
	
	@FindBy(id="ctl00_HomePageContent_cEDOA_title")
	WebElement calendarTitleDOA;
	
	@FindBy(id="ctl00_HomePageContent_cEDOA_prevArrow")
	WebElement calendarPreviousButtonDOA;
	
	@FindBy(id="ctl00_HomePageContent_cEDOA_nextArrow")
	WebElement calendarNextButtonDOA;
	
	List<WebElement> initialiseCalendarDaySelectionIterator() {
		List<WebElement> calenderDaySelectionIterator1=root.findElements(By.xpath("//tbody[@id='ctl00_HomePageContent_cEDOA_daysBody']/tr/td/div"));
		return calenderDaySelectionIterator1;
	}
	
	List<WebElement> initialiseCalendarMonthSelectionIterator() {
		List<WebElement> calenderMonthSelectionIterator1=root.findElements(By.xpath("//tbody[@id='ctl00_HomePageContent_cEDOA_monthsBody']/tr/td/div"));
		return calenderMonthSelectionIterator1;
	}
	
	List<WebElement> initialiseCalendarYearSelectionIterator() {
		List<WebElement> calenderYearSelectionIterator1=root.findElements(By.xpath("//tbody[@id='ctl00_HomePageContent_cEDOA_yearsBody']/tr/td/div"));
		return calenderYearSelectionIterator1;
	}

	@Override
	void initialiseCalendarTitleValues() {
		calendarTitle=calendarTitleDOA;
		calendarNextButton=calendarNextButtonDOA;
		calendarPreviousButton=calendarPreviousButtonDOA;		
	}
}
