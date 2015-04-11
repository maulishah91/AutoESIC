package com.esic.selenium.homePage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import com.esic.selenium.datePicker.DateOfAppointment;
import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 *This class will fetch values from input file
 */
public class EmployeeRegistrationForm1 {
	
	final static Logger logger = Logger.getLogger(EmployeeRegistrationForm1.class);
	
	@FindBy(id="ctl00_HomePageContent_ctrlRBDispensaryIMP_0")
	WebElement dispensaryRadioButton;
	
	@FindBy(id="ctl00_HomePageContent_ctrlRBDispensaryIMP_1")
	WebElement impRadioButton;
	
	//date of appointment/date of joining : ctl00_HomePageContent_ctrlDIDateOfAppointmentDy
	@FindBy(id="ctl00_HomePageContent_ctrlDIDateOfAppointmentDy")
	WebElement dateOfAppointment;
	
	public PersonalDetails process(){
		enterDateOfAppointment("24/12/1212");
		SelectdispensaryOrImp("imp");
		return new PersonalDetails();
	}
	
	//not mandatory
	public void SelectdispensaryOrImp(String value){
		if(value!=null && !value.trim().equals("")){
			if(value.trim().equalsIgnoreCase("Dispensary")) {dispensaryRadioButton.click();}
			else if(value.trim().equalsIgnoreCase("IMP")) {impRadioButton.click();}
			else logger.error("Incorrect value provided for selecting dispensary/IMP Radio Button");
		}
	}
	
	//date of apponitment : mandatory
	//acc to site it has to be dd/mm/yyyy 
	public void enterDateOfAppointment(String BirthDate){
		//perform date validation and fetch dd, mm and yyyy
		dateOfAppointment.click(); ////this will lead to creation of datepicker element in dom
		DateOfAppointment pickDate=PageFactory.initElements(Launch.driver, DateOfAppointment.class);
		//to do: perform validation that the values entered are valid
		pickDate.selectDateOnDatePicker("1991","Dec","24");
		
	}
}
