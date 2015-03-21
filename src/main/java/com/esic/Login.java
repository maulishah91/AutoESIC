package com.esic;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	//validate the page
	public void validateLoginPage(){
		if(loginTitle.equals("User Login")){
			logger.info("Login page is launched successfully");
		}
		else{
			//error and exit
		}
	}
	
	//login success and error scenarios
	public void login(String username,String password){
		validateLoginPage();
		userName.clear();
		userName.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);
		loginButton.click();
		checkLoginSuccess();
	}
	
	public void checkLoginSuccess(){
		//lblMessage  && Authentication Failed.
		try{
			//continue from here: 
			//login success
			//table7/tbody/tr/td: Either last logged-in session was not closed/logged-out OR another user is already                     logged in. Please check last logged in time.
			//value="X" name="div1_close
		}
		catch(Exception e){
			
		}
	}
}
