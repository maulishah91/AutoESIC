package com.esic.selenium.contactDetails;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.domain.ESICRecord;
import com.esic.selenium.prelogin.Launch;
/**
 * 
 * @author Mauli
 *
 */
public class PresentContactDetails extends ContactDetails{

	final static Logger logger = Logger.getLogger(PresentContactDetails.class);
	
		//multiple address lines.. split address into lengths of 50
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentAddress1")
		WebElement mandatoryAddressLine1;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentAddress2")
		WebElement mandatoryAddressLine2;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentAddress3")
		WebElement mandatoryAddressLine3;

		@FindBy(id="ctl00_HomePageContent_ctrlTxtPresentState")
		WebElement state;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTxtPresentPinCode")
		WebElement pincode;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentSTDCode")
		WebElement phoneSTDCode;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentphoneNo")
		WebElement phoneNo;
		
		//mobile number without 91 
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentMobileNo")
		WebElement mobileNo;
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPresentEmail")
		WebElement email;
		

		public PermanentContactDetails process(ESICRecord record){
			enterMandatoryAddress(record.getPresentAddress_Address());
			enterDetailForField("email", record.getPresentAddress_emailID());
			enterDetailForField("district", record.getPresentAddress_District());
			enterDetailForField("state", record.getPresentAddress_State());
			enterDetailForField("district", record.getPresentAddress_District());
			enterDetailForField("phone", record.getPresentAddress_PhoneNo());
			enterDetailForField("mobile", record.getPresentAddress_MobileNo());
			enterDetailForField("pincode", record.getPresentAddress_PinCode());
			return new PermanentContactDetails();
		}
		
		protected void loadAddressValues(){
			mandatoryAddress=new ArrayList<WebElement>();
			mandatoryAddress.add(mandatoryAddressLine1);
			mandatoryAddress.add(mandatoryAddressLine2);
			mandatoryAddress.add(mandatoryAddressLine3);
		}
		
		protected void loadDistrict(){
			//dynamically loaded after state is selected and hence has to called after state is selected
			district=Launch.driver.findElement(By.id("ctl00_HomePageContent_ctrlTextPresentDistrict"));
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
