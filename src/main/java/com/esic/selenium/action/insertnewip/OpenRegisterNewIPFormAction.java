package com.esic.selenium.action.insertnewip;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.pom.RegisterNewIP1POM;

//for page which opens after you click register new IP 
//http://www.esic.in/InsuranceGlobalWebV9/Employee/RegisteredEmployees.aspx

public class OpenRegisterNewIPFormAction implements Action {
	final static Logger logger = Logger
			.getLogger(OpenRegisterNewIPFormAction.class);

	public void perform(WebDriver driver, ESICRecord record) {

		RegisterNewIP1POM form = PageFactory.initElements(driver,
				RegisterNewIP1POM.class);

		if (form.registerNewIPHeader.getText().contains(
				"Track Registered Employees")) {
			logger.info("Success Scenario: Register New IP page has been opened");
		} else {
			logger.error("Failure Scenario: Register New IP page has NOT been opened");
		}

		form.selectNoRadioButton.click();
		driver.switchTo().alert().accept();
		
		//TODO: remove this hack of sleep.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		form = PageFactory.initElements(driver,
				RegisterNewIP1POM.class);
		
		
		form.continueButton.click();
		
		driver.switchTo().alert().accept();
		
	}

}
