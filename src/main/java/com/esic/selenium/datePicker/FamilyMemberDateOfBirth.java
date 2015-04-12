package com.esic.selenium.datePicker;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyMemberDateOfBirth extends DatePickerMain{

final static Logger logger = Logger.getLogger(FamilyMemberDateOfBirth.class);
	
	/*
	 * click once for month, twice for year
	 */
	@FindBy(id="ctl00_HomePageContent_cEDOA_title")
	WebElement calendarTitleDOB;
	
	@FindBy(id="ctl00_HomePageContent_cEDOA_prevArrow")
	WebElement calendarPreviousButtonDOB;
	
	@FindBy(id="ctl00_HomePageContent_cEDOA_nextArrow")
	WebElement calendarNextButtonDOB;
	
	//dob body part(like a root element for DOB datepicker body) 
	//for successful initialisation of root, the datepicker field has to be clicked once on UI before loading this class
	@FindBy(id="ctl00_HomePageContent_cEDOA_body")
	WebElement root;
	
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
		calendarTitle=calendarTitleDOB;
		calendarNextButton=calendarNextButtonDOB;
		calendarPreviousButton=calendarPreviousButtonDOB;
	}


}
