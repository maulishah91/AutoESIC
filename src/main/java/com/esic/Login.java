package com.esic;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.homePage.UserHomePage;
/**
 * 
 * @author Mauli
 *
 */
public class Login {
	
	final static Logger logger = Logger.getLogger(Login.class);

	@FindBy(id="lblLoginEng")
	WebElement loginTitle;
	
	@FindBy(id="txtUserName")
	WebElement userName;
	
	@FindBy(id="txtPassword")
	WebElement password;
	
	@FindBy(id="btnLogin")
	WebElement loginButton;
	
	//Authentication failed error message
	@FindBy(id="lblMessage")
	WebElement authFailMessage;
	
	public UserHomePage login(String username,String password){
		validateLoginPage();
		userName.clear();
		userName.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);
		loginButton.click();
		return checkLoginSuccess();
	}
	
	private UserHomePage checkLoginSuccess(){
		try{
			if(Launch.driver.getCurrentUrl().contains("http://www.esic.in/InsuranceGlobalWebV4/ESICInsurancePortal/PortalHome.aspx")){
			logger.info("Login success.");	
			//go to next PageObject
			return PageFactory.initElements(Launch.driver,UserHomePage.class);
			}
			throw new Exception("Login failure");
		}
		catch(Exception e){
			logger.error("Login Failed");
			if(authFailMessage!=null){
				logger.error("Invalid username/password");
			}
			return null;
		}
	}
	
	//validate the page
	private void validateLoginPage(){
		if(loginTitle.equals("User Login")){
			logger.info("Login page is launched successfully");
		}
		else{
			//error and exit
		}
	}
}
