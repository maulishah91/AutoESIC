package com.esic.selenium.contactDetails;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.selenium.prelogin.Launch;
import com.esic.selenium.secondaryForm.FamilyParticularsForm;

/**
 * 
 * @author Mauli
 *
 *For nominee dialog box, providing the contact details
 */
public class NomineeContactDetails extends ContactDetails{

	final static Logger logger = Logger.getLogger(NomineeContactDetails.class);
	
	//multiple address lines.. here the split is 10,45,45.. new implementation of address method
	@FindBy(id="ctl00_HomePageContent_ctrlTextAddress1")
	WebElement mandatoryAddressLine1;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextAddress2")
	WebElement mandatoryAddressLine2;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextAddress3")
	WebElement mandatoryAddressLine3;

	@FindBy(id="ctl00_HomePageContent_States")
	WebElement state;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPin")
	WebElement pincode;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPhoneExt")
	WebElement phoneSTDCode;
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextPhoneNumber")
	WebElement phoneNo;
	
	//mobile number without 91 
	@FindBy(id="ctl00_HomePageContent_ctrlTextMobileNumber")
	WebElement mobileNo;
	
	@FindBy(id="ctl00_HomePageContent_Save")
	WebElement saveBtn;
	
	@FindBy(id="ctl00_HomePageContent_btnClose")
	WebElement closeBtn;
	
	public FamilyParticularsForm process(){
		enterMandatoryAddress("abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij");
		enterDetailForField("email", "abc@abc.in");
		enterDetailForField("district", "mum");
		enterDetailForField("state", "Kerala");
		enterDetailForField("district", "Kollam");
		enterDetailForField("phone", "12345");
		saveBtn.click();
		closeBtn.click();
		Launch.switchToNewWindow(); // this will get us back to the main form
		return new FamilyParticularsForm();
	}
	
			//overriding base class method since lengths of address lines are different
			@Override
			public void enterMandatoryAddress(String address){
				loadAddressValues();
				address=address.trim();
				int lineNo=0;
				String addressLine="";
				if(!address.equals("") && address.length()>100){
					addressLine=address.substring(0, 99);
					mandatoryAddress.get(lineNo).clear();
					mandatoryAddress.get(lineNo).sendKeys(addressLine);
					address=address.replaceFirst(addressLine, "");
					lineNo++;
					while(!address.equals("") && lineNo<3){
						if(address.length()>45){
						addressLine=address.substring(0, 44);}
						else addressLine=address;
						mandatoryAddress.get(lineNo).clear();
						mandatoryAddress.get(lineNo).sendKeys(addressLine);
						address=address.replaceFirst(addressLine, "");
						logger.info("address line "+lineNo+" : "+addressLine);
						lineNo++;
					}
				}
				else{
					addressLine=address;
					mandatoryAddress.get(lineNo).clear();
					mandatoryAddress.get(lineNo).sendKeys(addressLine);
					logger.info("address line "+lineNo+" : "+addressLine);
				}
				
			}

	protected void loadAddressValues(){
		mandatoryAddress=new ArrayList<WebElement>();
		mandatoryAddress.add(mandatoryAddressLine1);
		mandatoryAddress.add(mandatoryAddressLine2);
		mandatoryAddress.add(mandatoryAddressLine3);
	}
	
	protected void loadDistrict(){
		//dynamically loaded after state is selected and hence has to called after state is selected
		district=Launch.driver.findElement(By.id("ctl00_HomePageContent_Districts"));
	}
	
	public void enterDetailForField(String fieldName,String fieldValue){
		if(fieldName.equals("state")) selectState(fieldValue, state);
		else if(fieldName.equals("district")) selectDistrict(fieldValue);
		else if(fieldName.equals("mobile")) enterDetail(fieldValue, mobileNo);
		else if(fieldName.equals("pincode")) enterDetail(fieldValue, pincode);
		else if(fieldName.equals("stdcode")) enterDetail(fieldValue, phoneSTDCode);
		else if(fieldName.equals("phone")) enterDetail(fieldValue, phoneNo);
		else logger.error("Incorrect fieldName");
	}

}
