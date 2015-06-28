package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//http://www.esic.in/InsuranceGlobalWebV9/Employee/Empe_BankAcc_Details.aspx

public class BankAccountFormPOM {

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_AccountNumber")
	public WebElement accountNumber;

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_TypeOfAccount")
	public WebElement typeOfAccount;

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_BankName")
	public WebElement nameOfBank;

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_BranchName")
	public WebElement nameOfBranch;

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_MICRCode")
	public WebElement mICRCode;

	@FindBy(id = "ctl00_HomePageContent_gdvBankDetails_ctl03_IFSCCode")
	public WebElement iFSCCode;

	@FindBy(id = "ctl00_HomePageContent_close_btn")
	public WebElement closeBtn;

	@FindBy(id = "ctl00_HomePageContent_ctrlButtonSave")
	public WebElement saveBtn;

}
