package com.esic.selenium.homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 *closing the two popups and logging out
 */
public class UserHomePage {
	
	final static Logger logger = Logger.getLogger(UserHomePage.class);
	
	//check for popup once logged in
	@FindBy(xpath="//table[@id='table7']/tbody/tr/td")
	WebElement popupText;
		
	//close popup
	@FindBy(name="div1_close")
	WebElement closePopup;
	
	//second popup leftTopFormLabelgrid
	@FindBy(className="leftTopFormLabelgrid")
	WebElement popupIPText;
	
	//close the second popup
	@FindBy(id="btnClose")
	WebElement closeIPPopup;
	
	//logout
	@FindBy(id="imgbtnLogout")
	WebElement logoutButton;
	
	//final popup on clicking logout 
	@FindBy(id="BtnOK")
	WebElement buttonOK;
	
	
	public IPRegistration process(){
		Launch.base= Launch.driver.getWindowHandle(); //home page of application is the base URL
		return closePopupForSession();
	}
	public IPRegistration closePopupForSession() throws ClosePopupException{
		//check for popup
		validatePopup1(popupText.getText());
		closePopup.click();
		validatePopup2(popupIPText.getText());
		closeIPPopup.click();
		return PageFactory.initElements(Launch.driver,IPRegistration.class);
	}
	
	public void logout()throws LogoutError{
		logger.info("Logging out of the application");
		logoutButton.click();
		buttonOK.click();
		String URLpostLogout="http://www.esic.in/ESICInsurance1/EsicInsurancePortal/PortalLogin.aspx";
		if(Launch.driver.getCurrentUrl().contains(URLpostLogout)){
			logger.info("Successfully logged out of the application. Closing browser.");
		}
		else logger.error("Error in logging out of the application. Closing browser");
	}
	
	private void validatePopup1(String text){
		logger.info(text);
		String popup1="Either last logged-in session was not closed/logged-out";
		if(text.contains(popup1)){
			logger.info("Success Scenario: First popup is valid");
		}
	}
	
	private void validatePopup2(String text){
		logger.info(text);
		String popup2="IP NUMBER ONCE GIVEN IS VALID FOR THE LIFETIME OF THE EMPLOYEE.";
		if(text.contains(popup2)){
			logger.info("Success Scenario: Second popup is valid");
		}
	}
	
	class LogoutError extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	class ClosePopupException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	
	
}
