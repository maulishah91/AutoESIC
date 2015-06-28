package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyPerticularsFormPOM {
	
	
		//name : mandatory
		@FindBy(id="ctl00_HomePageContent_txtName")
		public 		WebElement name;
		
		//dob : mandatory 
		@FindBy(id="ctl00_HomePageContent_CtrlDOB")
		public WebElement dateOfBirth;
		
		//relationship with employee : 
		@FindBy(id="ctl00_HomePageContent_CtrlRelation")
		public WebElement relationshipWithEmployee;
		
		//residing with him/her
		@FindBy(id="ctl00_HomePageContent_ctrlRDIpDisable_0")
		public WebElement yesRadioBtn;
		
		@FindBy(id="ctl00_HomePageContent_ctrlRDIpDisable_1")
		public WebElement noRadioBtn;
		
		//if no is selection for option :whether residing with him/her
		@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentState")
		public WebElement state;
		
		
		@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentDistric")
		public WebElement district;
		
		
		@FindBy(id="ctl00_HomePageContent_txtAadhaar")
		public WebElement aadharCard;
		
		@FindBy(id="ctl00_HomePageContent_ctrlButtonSave")
		public WebElement submit;
		
		@FindBy(id="ctl00_HomePageContent_dec_chkbox")
		public WebElement checkboxForParentsRelation;
		
		@FindBy(id="ctl00_HomePageContent_ctrlButtonAdd")
		public WebElement add;
		
		@FindBy(name="close_btn")
		public WebElement close;
		

}
