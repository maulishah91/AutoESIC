package com.esic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * 
 * @author Mauli
 *
 */
public class PreLogin {
	
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
	
	
	public Login launchLoginPage() {
		try{
	    String baseUrl = "http://www.esic.in";
	    Launch.driver.get(baseUrl);
	    validateHomePageLink();
	    homePageElement.click();
	    //check if certificates have been installed. check later if this step is required
	    //launch the main login page
	    skipCertificateInstall.click();
	    //check if connection untrusted page appears:
	    //link: https://www.esic.in/ESICInsurance1/ESICInsurancePortal/Portal_Login.aspx
	    checkForUntrustedConnectionError();
	    logger.info("Launching Login page.");
	    validateLoginPageLink();
	    return PageFactory.initElements(Launch.driver,Login.class);
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
	
	private void validateHomePageLink(){
		String homepage=Launch.driver.getCurrentUrl();
		if(homepage.equals("http://www.esic.in/ESICInsurance1/ESICInsurancePortal/PortalLogin.aspx")){
			logger.info("Success Scenario: ESIC site is loaded");
		}
	}
	
	private void validateLoginPageLink(){	
		String loginLink="https://www.esic.in/ESICInsurance1/ESICInsurancePortal/Portal_Login.aspx";
		if(Launch.driver.getCurrentUrl().contains(loginLink)){
			logger.info("Success Scenario: Login page link is verified");
		}
		
	}
}
