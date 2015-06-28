package com.esic.selenium.prelogin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.OpenESICWebsiteAction;
import com.esic.selenium.action.PageObject;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
/**
 * 
 * @author Mauli
 *
 */
public class PreLogin implements PageObject {
	
	final static Logger logger = Logger
			.getLogger(PreLogin.class);
	
	@FindBy(id="LinkLoginpage")
	WebElement homePageElement;
	
	@FindBy(id="Label1")
	WebElement skipCertificateInstall;
	
	//aSkipSecurityPage: href for "click here to continue" link of popup
	@FindBy(id="aSkipSecurityPage")
	WebElement skipCertificateInstallLink;
	
	//only if 'connection is untrusted' page pops up
	@FindBy(id="errorTitleText")
	WebElement errorTitleText;
	
	//adding exception if 'connection is untrusted' page pops up  
	@FindBy(id="exceptionDialogButton")
	WebElement addException;
	
	public Login process(ESICRecord record){
		return launchLoginPage(record);
	}
	
	public Login launchLoginPage(ESICRecord record) {
		try{
			
			WebDriver driver = ESICFireFoxWebDriver.getInstance();
	
			
			OpenESICWebsiteAction action = new OpenESICWebsiteAction();
			action.perform(driver, record);
			
			return PageFactory.initElements(ESICFireFoxWebDriver.getInstance(),Login.class);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("Error in launching ESIC website.");
			return null;
		}
}
	/**
	 * this step is crucial because the URL displayed on the browser changes
		but instead of loading the login page it shows this error page
	 */
	private void checkForUntrustedConnectionError(){
		try{
		if(errorTitleText!=null && errorTitleText.equals("This Connection is Untrusted")){
			logger.error("Connection is untrusted. Accept the risks");
			addException.click();}
			//for now user had to click on the popup. 
			//to do: automate this step
		}
		catch(Exception e){
			logger.info("Success scenario: Connection is trusted");
		}
	}
	
	private void validateHomePageLink(WebDriver driver){
		String homepage=driver.getCurrentUrl();
		if(homepage.equals("http://www.esic.in/ESICInsurance1/ESICInsurancePortal/PortalLogin.aspx")){
			logger.info("Success Scenario: ESIC site is loaded");
		}
		else
		{
			ESICFireFoxWebDriver.getInstance().get(ESICFireFoxWebDriver.getInstance().getCurrentUrl());
			validateHomePageLink(driver);
			
		}
	}
	
	private void validateLoginPageLink(){	
		String loginLink="https://www.esic.in/ESICInsurance1/ESICInsurancePortal/Portal_Login.aspx";
		if(ESICFireFoxWebDriver.getInstance().getCurrentUrl().contains(loginLink)){
			logger.info("Success Scenario: Login page link is verified");
		}
		
	}
}
