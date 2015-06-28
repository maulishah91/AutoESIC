package com.esic.selenium.processor.insertnewip;

import javax.swing.JOptionPane;

import org.openqa.selenium.firefox.FirefoxDriver;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.LoginAction;
import com.esic.selenium.action.OpenESICWebsiteAction;
import com.esic.selenium.action.insertnewip.FillFormRegisterNewIPAction;
import com.esic.selenium.action.insertnewip.OpenLinkRegisterNewIPDetailsAction;
import com.esic.selenium.action.insertnewip.OpenRegisterNewIPFormAction;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.processor.ESICRecordProcessorBase;

public class InsertESICRecordProcessor extends ESICRecordProcessorBase {

	@Override
	public void processRecord(ESICRecord record) {

		FirefoxDriver driver = loadDriver();
		
		OpenESICWebsiteAction action = new OpenESICWebsiteAction();
		action.perform(driver, record);
		
		
		
		LoginAction loginAction = new LoginAction();
		loginAction.perform(driver, record);
		
		
		
		OpenLinkRegisterNewIPDetailsAction openLinkInserIPDetailsAction = new OpenLinkRegisterNewIPDetailsAction();
		openLinkInserIPDetailsAction.perform(driver, record);
		
		((ESICFireFoxWebDriver) driver).switchToWindowWithTitle("EmployeeRegistration");
		
		
		OpenRegisterNewIPFormAction openRegisterNewIPFormAction = new OpenRegisterNewIPFormAction();
		openRegisterNewIPFormAction.perform(driver, record);
		
		
		FillFormRegisterNewIPAction fillFormRegisterNewIPAction = new FillFormRegisterNewIPAction();
		fillFormRegisterNewIPAction.perform(driver, record);
		
		
		JOptionPane.showMessageDialog(null, "Hello");
		
		
		
	}

}
