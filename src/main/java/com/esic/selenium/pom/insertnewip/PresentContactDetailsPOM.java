package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PresentContactDetailsPOM {

	// multiple address lines.. split address into lengths of 50
	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentAddress1")
	public WebElement mandatoryAddressLine1;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentAddress2")
	public WebElement mandatoryAddressLine2;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentAddress3")
	public WebElement mandatoryAddressLine3;

	@FindBy(id = "ctl00_HomePageContent_ctrlTxtPresentState")
	public WebElement state;

	@FindBy(id = "ctl00_HomePageContent_ctrlTxtPresentPinCode")
	public WebElement pincode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentSTDCode")
	public WebElement phoneSTDCode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentphoneNo")
	public WebElement phoneNo;

	// mobile number without 91
	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentMobileNo")
	public WebElement mobileNo;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentEmail")
	public WebElement email;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPresentDistrict")
	public WebElement district;

}
