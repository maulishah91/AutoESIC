package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NomineeContactDetailsPOM {

	// multiple address lines.. here the split is 10,45,45.. new implementation
	// of address method
	@FindBy(id = "ctl00_HomePageContent_ctrlTextAddress1")
	public WebElement mandatoryAddressLine1;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextAddress2")
	public WebElement mandatoryAddressLine2;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextAddress3")
	public WebElement mandatoryAddressLine3;

	@FindBy(id = "ctl00_HomePageContent_States")
	public WebElement state;

	@FindBy(id = "ctl00_HomePageContent_Districts")
	public WebElement district;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPin")
	public WebElement pincode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPhoneExt")
	public WebElement phoneSTDCode;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextPhoneNumber")
	public WebElement phoneNo;

	// mobile number without 91
	@FindBy(id = "ctl00_HomePageContent_ctrlTextMobileNumber")
	public WebElement mobileNo;

	@FindBy(id = "ctl00_HomePageContent_Save")
	public WebElement saveBtn;

	@FindBy(id = "ctl00_HomePageContent_btnClose")
	public WebElement closeBtn;

	// error fields:
	@FindBy(id = "ctl00_HomePageContent_RequiredFieldValidator1")
	public WebElement nameError;

	@FindBy(id = "ctl00_HomePageContent_RequiredFieldValidator3")
	public WebElement addressError;

	@FindBy(id = "ctl00_HomePageContent_RequiredFieldValidator5")
	public WebElement relationshipError;

	@FindBy(id = "ctl00_HomePageContent_RequiredFieldValidatorState")
	public WebElement stateError;

	@FindBy(id = "ctl00_HomePageContent_DistrictsRequiredFieldVal")
	public WebElement districtError;

	@FindBy(id = "ctl00_HomePageContent_Address1RegularExpressionVal")
	public WebElement addressError2;

	@FindBy(id = "ctl00_HomePageContent_Address2RegularExpressionVal")
	public WebElement addressLine2Error;

	@FindBy(id = "ctl00_HomePageContent_Address3RegularExpressionVal")
	public WebElement addressLine3Error;

	@FindBy(id = "ctl00_HomePageContent_RegularExpressionValidator3")
	public WebElement pincodeError;

	@FindBy(id = "ctl00_HomePageContent_RegularExpressionValidator6")
	public WebElement mobileError;

	@FindBy(id = "ctl00_HomePageContent_revAadhaar")
	public WebElement aadharCardError;

}
