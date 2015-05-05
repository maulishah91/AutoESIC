package com.esic.selenium.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreLoginPOM {
	
	
	@FindBy(id="LinkLoginpage")
	public	WebElement homePageElement;
	
	@FindBy(id="Label1")
	public WebElement skipCertificateInstall;
	
	//aSkipSecurityPage: href for "click here to continue" link of popup
	@FindBy(id="aSkipSecurityPage")
	public WebElement skipCertificateInstallLink;
	
	//only if 'connection is untrusted' page pops up
	@FindBy(id="errorTitleText")
	public WebElement errorTitleText;
	
	//adding exception if 'connection is untrusted' page pops up  
	@FindBy(id="exceptionDialogButton")
	public WebElement addException;

}
