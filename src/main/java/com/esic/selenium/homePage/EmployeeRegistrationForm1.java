package com.esic.selenium.homePage;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICDate;
import com.esic.selenium.datePicker.DateOfAppointment;
import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 *This class will fetch values from input file.
 *Just the date of appointment 
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
		ESICDate doa = Launch.record.getDateOfAppointmentESICDate();
		enterDateOfAppointment(doa);
		
		//NOTE: THIS PART IS LEFT UNIMPLEMENTED FOR NOW
		//SelectdispensaryOrImp("imp");
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
	public void enterDateOfAppointment(ESICDate doa){
		//perform date validation and fetch dd, mm and yyyy
		dateOfAppointment.click(); ////this will lead to creation of datepicker element in dom
		DateOfAppointment pickDate=PageFactory.initElements(Launch.driver, DateOfAppointment.class);
		pickDate.selectDateOnDatePicker(""+doa.getYear(),doa.getMonth(),""+doa.getDate());
		
	}
}
