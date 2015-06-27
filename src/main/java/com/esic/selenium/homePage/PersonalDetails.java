package com.esic.selenium.homePage;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICDate;
import com.esic.domain.ESICRecord;
import com.esic.selenium.contactDetails.PresentContactDetails;
import com.esic.selenium.datePicker.DateOfBirth;
import com.esic.selenium.prelogin.Launch;
import com.esic.util.DropdownUtil;

public class PersonalDetails {

	final static Logger logger = Logger.getLogger(PersonalDetails.class);
			
	@FindBy(id="ctl00_HomePageContent_ctrlTextEmpName")
	WebElement empName;
	
	@FindBy(id="ctl00_HomePageContent_ctrlFatherOrHus_0")
	WebElement fatherRadioButton;
	
	@FindBy(id="ctl00_HomePageContent_ctrlFatherOrHus_1")
	WebElement husbandRadioButton;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextFatherHusName")
	WebElement husbandOrfatherName;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTxtIpDate")
	WebElement dateOfBirth;
	
	@FindBy(id="ctl00_HomePageContent_txtAadhaarID")
	WebElement aadharCard;
	
	@FindBy(id="ctl00_HomePageContent_ctrlRDMarried")
	WebElement martialStatus;
	
	//male radio button
	@FindBy(id="ctl00_HomePageContent_ctrlRDMale_0")
	WebElement maleRadioButton;
	
	//female radio button
	@FindBy(id="ctl00_HomePageContent_ctrlRDMale_1")
	WebElement femaleRadioButton;
	
		
	public PresentContactDetails process(){
		
		ESICRecord record = Launch.record;
		enterEmpName(record.getEmployeeName());
		selectMartialStatus(record.getMatitalStatus());
		
		int isHusband = Integer.parseInt(record.getIsHusband());
		enterFatherOrHusbandName(isHusband, record.getHusbandOrFatherName());
		
		enterDateOfBirth(record.getDateOfBirthESICDate());
		enterAadharCard(record.getAadharID());
		selectGender(record.getGender());
		return new PresentContactDetails();
	}
	//mandatory
	public void enterEmpName(String name){
		empName.clear();
		empName.sendKeys(name.trim());
	}
	
	//mandatory
	//esic record and excel sheet needs to be modified accordingly
	public void enterFatherOrHusbandName(int selector, String name){
		//0: father
		//1: husband
		if(selector==0)fatherRadioButton.click();
		else if(selector==1) husbandRadioButton.click();
		else {
			logger.error("Wrong input entered for father/husband radio button"); 
			//Launch.record.setAutoEsicComments("Wrong input entered for father/husband radio button");
			JOptionPane.showMessageDialog (null, "Please select correct value for father/husband before clicking okay", "Wrong value", JOptionPane.ERROR_MESSAGE);
			return;
			}
		husbandOrfatherName.clear();
		husbandOrfatherName.sendKeys(name.trim());
		logger.info("selected is "+selector);
	}
	
	//date of birth : mandatory & age cannot be less than 14
	//acc to site it has to be dd/mm/yyyy 
	public void enterDateOfBirth(ESICDate dob){
		//perform date validation and fetch dd, mm and yyyy
		dateOfBirth.click(); //this will lead to creation of datepicker element in dom
		DateOfBirth pickDate=PageFactory.initElements(Launch.driver, DateOfBirth.class);
		//to do: perform validation that the values entered are valid
		pickDate.selectDateOnDatePicker(dob);
		
	}
	
	//aadhar card: not mandatory
	public void enterAadharCard(String aadharCardName){
		aadharCard.clear();
		if(aadharCardName!=null && !aadharCardName.trim().equals(""))
		aadharCard.sendKeys(aadharCardName.trim());
	}
	
	//martial status: mandatory :ask if int value is poss or not
	public void selectMartialStatus(String martialValue){
		martialStatus.click();
		DropdownUtil.selectDropdown(martialValue, martialStatus);
	}
	
	//gender(sex) : mandatory
	public void selectGender(String gender){
		if(gender.equalsIgnoreCase("Male")){
			maleRadioButton.click();
		}
		else if(gender.equalsIgnoreCase("Female")){
			femaleRadioButton.click();
		}
		else{
			logger.error("Incorrect option provided for gender");
			//Launch.record.setAutoEsicComments("Incorrect option provided for gender");
			JOptionPane.showMessageDialog (null, "Please select correct option for gender before clicking okay", "Wrong value", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
}
