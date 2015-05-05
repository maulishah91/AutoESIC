package com.esic.selenium.prelogin;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	public UserHomePage process() throws Exception{
		//from excel sheet
		String username=Launch.record.getEsicUserName();
		String password=Launch.record.getEsicPassword();
		if(username.equals("") || password.equals("")){
			logger.info("Skipping record since the username/password is not present");
			return null;
		}
		boolean sucess = login(username,password);
		if (sucess) {
			return checkLoginSuccess();
		} else {
			return null;
		}
	}
	
	public boolean login(String usernameValue,String passwordValue) throws Exception{
		for(String block : Launch.username_to_block){
		if(block.equalsIgnoreCase(usernameValue)){
			logger.error("username belongs to the list of invalid user credentials. Skipping the row");
			Launch.record.setAutoEsicComments("Skipping the row due to invalid login cerdentials");
			return false;
		}
		}
		userName.clear();
		userName.sendKeys(usernameValue);
		this.password.clear();
		this.password.sendKeys(passwordValue);
		loginButton.click();
		
		return true;
	}
	
	private UserHomePage checkLoginSuccess(){
		try{
			if(Launch.driver.getCurrentUrl().contains("www.esic.in/InsuranceGlobalWebV4/ESICInsurancePortal/PortalHome.aspx")){
			logger.info("Login success.");	
			//go to next PageObject
			return new UserHomePage();
			}
			//login has failed.. record the username to avoid further use
			Launch.username_to_block.add(Launch.record.getEsicUserName());
			return null;
		}
		catch(Exception e){
			logger.error("Login Failed");
			//login has failed.. record the username to avoid further use
			Launch.username_to_block.add(Launch.record.getEsicUserName());
			if(authFailMessage!=null && authFailMessage.getText().contains("Authentication failure.")){
				logger.error("Invalid username/password");
			}
			Launch.record.setAutoEsicComments("Skipping the row due to invalid login cerdentials");
			return null;
		}
	}
}
