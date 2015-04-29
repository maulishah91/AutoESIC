package com.esic.selenium.prelogin;


import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.homePage.UserHomePage;
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
	
	public UserHomePage process(){
		//from excel sheet
		ESICRecord record = Launch.record;
		String userName = record.getEsicUserName();
		String password = record.getEsicPassword();
		return login(userName,password);
        /*String uName=JOptionPane.showInputDialog("Enter username");
		String pWord=JOptionPane.showInputDialog("Enter password");
		return login(uName, pWord);*/
	}
	
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
			return new UserHomePage();
			}
			throw new Exception("Login failure");
		}
		catch(Exception e){
			logger.error("Login Failed");
			if(authFailMessage!=null && authFailMessage.getText().contains("Authentication failure.")){
				logger.error("Invalid username/password");
			}
			return null;
		}
	}
	
	//validate the page
	private void validateLoginPage(){
		if(loginTitle.equals("User Login")){
			logger.info("Success Scenario: Login page is launched successfully");
		}
		else{
			//error and exit
		}
	}
}
