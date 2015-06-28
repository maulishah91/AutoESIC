package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PermanentContactDetailsPOM {

	// checkbox
	@FindBy(id = "ctl00_HomePageContent_chkboxCopyPresentAddress")
	public WebElement copyPresentAddressDetails;

	// multiple address lines.. split address into lengths of 50
	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentAddress1")
	public WebElement mandatoryAddressLine1;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentAddress2")
	public WebElement mandatoryAddressLine2;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentAddress3")
	public WebElement mandatoryAddressLine3;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentState")
	public WebElement state;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentPinCode")
	public WebElement pincode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentSTDCode")
	public WebElement phoneSTDCode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentphoneNo")
	public WebElement phoneNo;

	// mobile number without 91
	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentMobileNo")
	public WebElement mobileNo;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentEmail")
	public WebElement email;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPermanentDistrict")
	public WebElement district;

}
