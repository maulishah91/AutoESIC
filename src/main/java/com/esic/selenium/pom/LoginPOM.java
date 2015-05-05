package com.esic.selenium.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPOM {

	
	
	
	@FindBy(id="lblLoginEng")
	public WebElement loginTitle;
	
	@FindBy(id="txtUserName")
	public	WebElement userName;
	
	@FindBy(id="txtPassword")
	public WebElement password;
	
	@FindBy(id="btnLogin")
	public WebElement loginButton;
	
	//Authentication failed error message
	@FindBy(id="lblMessage")
	public WebElement authFailMessage;
	
	
	@FindBy(id="div1_close")
	public WebElement postLoginPopUp1;
	
		
	@FindBy(id="btnClose")
	public	WebElement postLoginPopUp2;
	
	
	
		
}
