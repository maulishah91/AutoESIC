package com.esic.selenium.contactDetails;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 */
public class PermanentContactDetails extends ContactDetails{
	
	final static Logger logger = Logger.getLogger(PermanentContactDetails.class);
	
	//multiple address lines.. split address into lengths of 50
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentAddress1")
	WebElement mandatoryAddressLine1;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentAddress2")
	WebElement mandatoryAddressLine2;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentAddress3")
	WebElement mandatoryAddressLine3;

	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentState")
	WebElement state;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentPinCode")
	WebElement pincode;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentSTDCode")
	WebElement phoneSTDCode;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentphoneNo")
	WebElement phoneNo;
	
	//mobile number without 91 
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentMobileNo")
	WebElement mobileNo;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentEmail")
	WebElement email;
	

	protected void loadAddressValues(){
		mandatoryAddress=new ArrayList<WebElement>();
		mandatoryAddress.add(mandatoryAddressLine1);
		mandatoryAddress.add(mandatoryAddressLine2);
		mandatoryAddress.add(mandatoryAddressLine3);
	}
	
	protected void loadDistrict(){
		//dynamically loaded after state is selected and hence has to called after state is selected
		district=Launch.driver.findElement(By.id("ctl00_HomePageContent_ctrlTextPermanentDistrict"));
	}
	
	public void enterDetailForField(String fieldName,String fieldValue){
		if(fieldName.equals("state")) selectState(fieldValue, state);
		else if(fieldName.equals("district")) selectDistrict(fieldValue);
		else if(fieldName.equals("email")) enterDetail(fieldValue, email);
		else if(fieldName.equals("mobile")) enterDetail(fieldValue, mobileNo);
		else if(fieldName.equals("pincode")) enterDetail(fieldValue, pincode);
		else if(fieldName.equals("stdcode")) enterDetail(fieldValue, phoneSTDCode);
		else if(fieldName.equals("phone")) enterDetail(fieldValue, phoneNo);
		else logger.error("Incorrect fieldName");
	}
}
