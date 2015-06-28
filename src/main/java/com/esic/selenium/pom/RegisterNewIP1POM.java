package com.esic.selenium.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterNewIP1POM {

	
	@FindBy(className="pageHeaderText")
	public WebElement registerNewIPHeader;
	
	
	@FindBy(id="ctl00_HomePageContent_rbtnlistIsregistered_1")
	public WebElement selectNoRadioButton;
	
	@FindBy(id="ctl00_HomePageContent_btnContinue")
	public WebElement continueButton;
	
	
}
