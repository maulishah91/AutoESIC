package com.esic.selenium.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalHomePOM {

	@FindBy(id = "lnkRegisterNewIP")
	public WebElement registerNewIP;

	@FindBy(id = "lnkUpdateIP")
	public WebElement updateIP;

	@FindBy(id = "lnkInsertIPDetails")
	public WebElement insertIPDetails;
	
	
}
