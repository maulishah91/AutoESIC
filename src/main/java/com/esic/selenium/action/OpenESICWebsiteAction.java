package com.esic.selenium.action;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICSystemException;
import com.esic.selenium.pom.PreLoginPOM;
import com.esic.selenium.prelogin.Launch;

public class OpenESICWebsiteAction  implements Action{
	
	
	String loginLink = "https://www.esic.in/ESICInsurance1/ESICInsurancePortal/Portal_Login.aspx";
	String baseUrl = "http://www.esic.in";
	
	final static Logger logger = Logger
			.getLogger(OpenESICWebsiteAction.class);
	
	
	public void perform(WebDriver driver, ESICRecord record)
 {

		driver.get(baseUrl);
		validateHomePageLink(driver);

		PreLoginPOM preLoginPage = PageFactory.initElements(driver,
				PreLoginPOM.class);

		preLoginPage.homePageElement.click();
		// check if certificates have been installed. check later if this step
		// is required
		// launch the main login page
		preLoginPage.skipCertificateInstall.click();
		// check if connection untrusted page appears:
		// link:
		// https://www.esic.in/ESICInsurance1/ESICInsurancePortal/Portal_Login.aspx

		checkForUntrustedConnectionError(driver, preLoginPage);
		validateLoginPageLink(driver);

	}
	
	
	
	private  void validateHomePageLink(WebDriver driver) {
		String homepage = driver.getCurrentUrl();
		if (homepage
				.equals("http://www.esic.in/ESICInsurance1/ESICInsurancePortal/PortalLogin.aspx")) {
			logger.info("Success Scenario: ESIC site is loaded");
			return ;
		} else {
			int confirm = JOptionPane.showConfirmDialog(
					ObjectStore.getUI().frame,
					" Website Slow / not loading Do you want to retry ?");
			// short circuit
			if (confirm == JOptionPane.NO_OPTION) {
				throw new ESICSystemException("ESIC Site not loading", null);
			}
			Launch.driver.get(Launch.driver.getCurrentUrl());
			validateHomePageLink(driver);

		}
	}
	
	
	/**
	 * this step is crucial because the URL displayed on the browser changes
		but instead of loading the login page it shows this error page
	 * @param preLoginPage 
	 * @param driver 
	 */
	private void checkForUntrustedConnectionError(WebDriver driver,
			PreLoginPOM preLoginPage) {
		try {
			if (preLoginPage.errorTitleText != null
					&& preLoginPage.errorTitleText
							.equals("This Connection is Untrusted")) {
				logger.error("Connection is untrusted. Accept the risks");
				preLoginPage.addException.click();
			}
			// for now user had to click on the popup.
			// to do: automate this step
		} catch (Exception e) {
			logger.info("Success scenario: Connection is trusted");
		}
	}
	
	
	
	private void validateLoginPageLink(WebDriver driver) {

		if (driver.getCurrentUrl().contains(loginLink)) {
			logger.info("Success Scenario: Login page link is verified");
		} else {
			throw new ESICSystemException("Login page Link Not Found at"
					+ driver.getCurrentUrl(), null);
		}
	}
	
	

}
