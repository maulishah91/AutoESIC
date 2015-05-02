package com.esic.selenium.datePicker;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.esic.domain.ESICDate;

/**
 * 
 * @author Mauli
 *
 */
public abstract class DatePickerMain {

	final static Logger logger = Logger.getLogger(DatePickerMain.class);
	
	WebElement calendarTitle,calendarNextButton,calendarPreviousButton;
	/*
	 * say current year is 2015.. 
	 * the range (on UI of date picker will be 2010-2019) will show 2019 as max due to which the while loop will have a false condition.
	 * adding 10 will prevent this from happening
	 */
	int currentYear=Calendar.getInstance().get(Calendar.YEAR)+10; 
	
	/**
	 * Additional Wripper Method.
	 */
	public void selectDateOnDatePicker(ESICDate date)
	{
	 
		this.selectDateOnDatePicker(""+date.getYear(), date.getMonth(), ""+date.getDate());
	}
	
	public void selectDateOnDatePicker(String year,String month,String day) throws ExceptionInDateSelectionFromDatePicker{
		initialiseCalendarTitleValues();
		getYearViewOnDatePicker();
		int startYear=1800; //assuming age of a person will not exceed 200 years (except if he is in a harry potter novel and thus will only need a wand to fill this form)
		int endYear=currentYear;	
		while(startYear>=1800 && endYear<=currentYear){
		startYear=Integer.parseInt(calendarTitle.getText().trim().substring(0,4));
		endYear=Integer.parseInt(calendarTitle.getText().trim().substring(5));
		logger.info("range is "+startYear+"-"+endYear);
		boolean yearFound=selectYearFromDatePicker(startYear, endYear, year);
		if(yearFound) break;
		}
		selectMonthFromDatePicker(month);
		selectDayFromDatePicker(day,month);
	}

	private void selectDayFromDatePicker(String day,String month) {
		List<WebElement> calendarDaySelectionIterator1 = initialiseCalendarDaySelectionIterator();
		
		for(WebElement w: calendarDaySelectionIterator1){
			String dateSelected=w.getAttribute("innerHTML");
			//make sure the value selected belongs to same month
			logger.debug(dateSelected);
			if(dateSelected.trim().equals(day) && w.getAttribute("title").contains(month)){
				logger.info("selecting the date: "+day);
				w.click();
				break;
			}
		}
	}

	

	private void selectMonthFromDatePicker(String month) {
		List<WebElement> calendarMonthSelectionIterator1 = initialiseCalendarMonthSelectionIterator();
		
		for(WebElement w: calendarMonthSelectionIterator1){
			String monthSelected=w.getAttribute("innerHTML");
			logger.debug(monthSelected);
			if(monthSelected.contains(month)){
				logger.info("month to be selected: "+month);
				w.click();
				break;
			}
		}
	}

	private boolean selectYearFromDatePicker(int startYear, int endYear, String year1) {
		System.out.println("actual year is : "+year1);
		int year=Integer.parseInt(year1.trim());
		System.out.println("post parsing year in int is : "+year);
		if(year>startYear){
			//check if it falls in the range and search for it or click next
			if(year<endYear){
				//check the body for the year
				logger.info("date is present in body");
				//value is loaded dynamically hence has to be fetched now
				List<WebElement> calenderYearSelectionIterator1 = initialiseCalendarYearSelectionIterator();
				
				for(WebElement w: calenderYearSelectionIterator1){
					String yearSelected=w.getAttribute("innerHTML");
					logger.debug(yearSelected);
					if(yearSelected.contains(year1)){
					logger.info("year selected is: "+year1);
					w.click();
						break;
					}
				}
				return true;
			}
			else{
				//click next button till you find it
				logger.info("next");
				calendarNextButton.click();
			}
		}
		else{
			//lets go back a bit by previous button
			logger.info("previous");
			calendarPreviousButton.click();
		}
		return false;
	}


	private void getYearViewOnDatePicker() {
		calendarTitle.click(); //with show months
		calendarTitle.click(); //will show year
		calendarTitle.click(); //extra one to be sure
		logger.info("calender year currently displayed is : "+calendarTitle.getText());
		int tryAgain=0;
		while(calendarTitle.getText().length()<8 && tryAgain<5){
			calendarTitle.click();	
			tryAgain++;
			logger.info("calender year currently displayed is : "+calendarTitle.getText());
		}
	}
	
	abstract List<WebElement> initialiseCalendarDaySelectionIterator();
	
	abstract List<WebElement> initialiseCalendarMonthSelectionIterator();
	
	abstract List<WebElement> initialiseCalendarYearSelectionIterator();
	
	abstract void initialiseCalendarTitleValues();
	
	class ExceptionInDateSelectionFromDatePicker extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
}
