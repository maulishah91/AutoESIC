package com.esic.selenium.action;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICRecordException;
import com.esic.exception.ESICRecordSkipException;
import com.esic.selenium.pom.LoginPOM;

public class LoginAction implements Action{

	
	final static Logger logger = Logger
			.getLogger(LoginAction.class);
	
	
	
	
	public void perform(WebDriver driver, ESICRecord record)
	 {
		
		
		LoginPOM loginPOM = PageFactory.initElements(driver,
				LoginPOM.class);

		

		//from excel sheet
		String username=record.getEsicUserName();
		String password=record.getEsicPassword();
		
		
		
		
		
		
		if(username.equals("") || password.equals("")){
			logger.warn("Skipping record since the username/password is not present");
			throw new ESICRecordException(" user name password not found", null);
			
		}
		
		
		

		loginPOM.userName.clear();
		loginPOM.userName.sendKeys(username);
		loginPOM.password.clear();
		loginPOM.password.sendKeys(password);
		loginPOM.loginButton.click();
		
		loginPOM.postLoginPopUp1.click();
		loginPOM.postLoginPopUp2.click();
		
		checkLoginSuccess(driver, loginPOM, record);

	 
	 }
	
	
	
	private void checkLoginSuccess(WebDriver driver, LoginPOM loginPOM,
			ESICRecord record) {
		if (driver
				.getCurrentUrl()
				.contains("ESICInsurancePortal/PortalHome.aspx")) {
			logger.info("Login success.");
			// go to next PageObject
			return;
		}
		// login has failed.. record the username to avoid further use
		ObjectStore.blockedUsers.add(record.getEsicUserName());
		throw new ESICRecordSkipException("login failed", null);

	}
	
	
}
