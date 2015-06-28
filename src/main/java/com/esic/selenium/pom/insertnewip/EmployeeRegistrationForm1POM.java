package com.esic.selenium.pom.insertnewip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeRegistrationForm1POM {

	@FindBy(id = "ctl00_HomePageContent_ctrlRBDispensaryIMP_0")
	public WebElement dispensaryRadioButton;

	@FindBy(id = "ctl00_HomePageContent_ctrlRBDispensaryIMP_1")
	public WebElement impRadioButton;

	// date of appointment/date of joining :
	// ctl00_HomePageContent_ctrlDIDateOfAppointmentDy
	@FindBy(id = "ctl00_HomePageContent_ctrlDIDateOfAppointmentDy")
	public WebElement dateOfAppointment;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextEmpName")
	public WebElement empName;

	@FindBy(id = "ctl00_HomePageContent_ctrlFatherOrHus_0")
	public WebElement fatherRadioButton;

	@FindBy(id = "ctl00_HomePageContent_ctrlFatherOrHus_1")
	public WebElement husbandRadioButton;

	@FindBy(id = "ctl00_HomePageContent_ctrlTextFatherHusName")
	public WebElement husbandOrfatherName;

	@FindBy(id = "ctl00_HomePageContent_ctrlTxtIpDate")
	public WebElement dateOfBirth;

	@FindBy(id = "ctl00_HomePageContent_txtAadhaarID")
	public WebElement aadharCard;

	@FindBy(id = "ctl00_HomePageContent_ctrlRDMarried")
	public WebElement martialStatus;

	// male radio button
	@FindBy(id = "ctl00_HomePageContent_ctrlRDMale_0")
	public WebElement maleRadioButton;

	// female radio button
	@FindBy(id = "ctl00_HomePageContent_ctrlRDMale_1")
	public WebElement femaleRadioButton;
	
	
	@FindBy(xpath="//td[contains(.,'Details of Nominee')]/following-sibling::td/a")
	public WebElement detailsOfNomineeLink ;
	
	@FindBy(xpath="//td[contains(.,'Particulars of Insured Person:')]/following-sibling::td/a")
	public WebElement familyParticularsLink;
	
	
	
	

}
