package com.esic.selenium.homePage;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.selenium.contactDetails.PresentContactDetails;
import com.esic.selenium.datePicker.DateOfBirth;
import com.esic.selenium.prelogin.Launch;

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
		enterFatherOrHusbandName(0, "dad");
		enterDateOfBirth("24/12/2323");
		enterAadharCard("21232434343");
		enterEmpName("abcd");
		selectGender("f");
		selectMartialStatus("1");
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
		else {logger.error("wrong input in exce"); return;}
		husbandOrfatherName.clear();
		husbandOrfatherName.sendKeys(name.trim());
		logger.info("selected is "+selector);
	}
	
	//date of birth : mandatory & age cannot be less than 14
	//acc to site it has to be dd/mm/yyyy 
	public void enterDateOfBirth(String BirthDate){
		//perform date validation and fetch dd, mm and yyyy
		dateOfBirth.click(); //this will lead to creation of datepicker element in dom
		DateOfBirth pickDate=PageFactory.initElements(Launch.driver, DateOfBirth.class);
		//to do: perform validation that the values entered are valid
		pickDate.selectDateOnDatePicker("1991","Dec","24");
		
	}
	
	//aadhar card: not mandatory
	public void enterAadharCard(String aadharCardName){
		aadharCard.clear();
		if(aadharCardName!=null && !aadharCardName.trim().equals(""))
		aadharCard.sendKeys(aadharCardName.trim());
	}
	
	//martial status: mandatory :ask if int value is poss or not
	public void selectMartialStatus(String martialStatusValue){
		int value=Integer.parseInt(martialStatusValue.trim());
		martialStatus.click();
		List<WebElement> options=martialStatus.findElements(By.xpath("./option"));
		//validate that the values belong to 0-3 range
		options.get(value).click(); 
	}
	
	//gender(sex) : mandatory
	public void selectGender(String gender){
		if(gender.equalsIgnoreCase("M")){
			maleRadioButton.click();
		}
		else if(gender.equalsIgnoreCase("F")){
			femaleRadioButton.click();
		}
		else{
			logger.error("Incorrect option provided for gender");
		}
	}
	
}
