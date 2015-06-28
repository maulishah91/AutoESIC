package com.esic.selenium.action.insertnewip.nominee;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.pom.insertnewip.EmployeeRegistrationForm1POM;
import com.esic.selenium.pom.insertnewip.NomineeFormPOM;
import com.esic.util.DropdownUtil;

public class FillNomineeDetailsAction implements Action {
	final static Logger logger = Logger.getLogger(FillNomineeDetailsAction.class);

	public void perform(WebDriver driver, ESICRecord record) {

		EmployeeRegistrationForm1POM form = PageFactory.initElements(driver, EmployeeRegistrationForm1POM.class);

		logger.info("link is : " + form.detailsOfNomineeLink.getText());

		form.detailsOfNomineeLink.click();

		((ESICFireFoxWebDriver) driver).switchToWindowWithTitle("NomineeDetails");

		NomineeFormPOM nomineeForm = PageFactory.initElements(driver, NomineeFormPOM.class);

		nomineeForm.name.clear();
		nomineeForm.name.sendKeys(record.getNomineeName());

		selectRelationshipWithIP(record.getNomineeRelationship(), nomineeForm);

		boolean isFamilyMem = isNomineeAFamilyMember(record.getIsnomineeAFamilyMember(), nomineeForm);
		if (!isFamilyMem) {
			// aadhar card details:
			enterAadharID(record.getNomineeAadharID(), nomineeForm);

		}
		
		
		FillNomineeAddressAction nomineeAddressAction = new FillNomineeAddressAction();
		nomineeAddressAction.perform(driver, record);
		
		//going back to previous form.	
		((ESICFireFoxWebDriver) driver).switchToWindowWithTitle("EmployeeRegistration");

		
		
	}

	private void enterAadharID(String value, NomineeFormPOM nomineeFormPOM) {
		if (!value.contains("^[0-9]+$")) {
			logger.error("Aadhar card value: " + value + " in excel file is incorrect. Please enter the valid value before proceeding");
			JOptionPane.showMessageDialog(null, "Aadhar card value: " + value + " in excel file is incorrect. Please enter the valid value before proceeding", "Value incorrect",
					JOptionPane.ERROR_MESSAGE);
		} else {
			nomineeFormPOM.aadharID.clear();
			nomineeFormPOM.aadharID.sendKeys(value);
		}
	}

	private boolean isNomineeAFamilyMember(String value, NomineeFormPOM nomineeFormPOM) {
		if (value.equalsIgnoreCase("yes")) {
			nomineeFormPOM.yesRadioBtn.click();
			return true;
		} else {
			nomineeFormPOM.noRadioBtn.click();
			return false;
		}
	}

	private void selectRelationshipWithIP(String relationValue, NomineeFormPOM nomineeFormPOM) {
		nomineeFormPOM.relationshipWithIp.click();
		DropdownUtil.selectDropdown(relationValue, nomineeFormPOM.relationshipWithIp);
	}
}
