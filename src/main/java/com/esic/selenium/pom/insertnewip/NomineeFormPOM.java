package com.esic.selenium.pom.insertnewip;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NomineeFormPOM {
	
	
	final static Logger logger = Logger.getLogger(NomineeFormPOM.class);

	
	//name: mandatory
	@FindBy(id="ctl00_HomePageContent_ctrlTextUserName")
	public WebElement name;
	
	//relationship with ip : mandatory  (has <option>) 12 options
	@FindBy(id="ctl00_HomePageContent_RelationShipWithIp")
	public WebElement relationshipWithIp;
	
	//nominee family member radio buttons
	////yes:  : if yes is selected aadhar card details are not mandatory
	@FindBy(id="ctl00_HomePageContent_rbtnlistNomneeAkaFamily_0")
	public WebElement yesRadioBtn;
	
	//no :  : if no is selected aadhar card details are mandatory
	@FindBy(id="ctl00_HomePageContent_rbtnlistNomneeAkaFamily_1")
	public WebElement noRadioBtn;
	
	//aadhar ID
	@FindBy(id="ctl00_HomePageContent_txtAadhaarID")
	public WebElement aadharID;
	

}
