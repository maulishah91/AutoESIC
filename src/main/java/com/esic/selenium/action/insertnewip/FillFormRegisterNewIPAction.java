package com.esic.selenium.action.insertnewip;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICDate;
import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.action.insertnewip.nominee.FillNomineeDetailsAction;
import com.esic.selenium.datePicker.DateOfAppointment;
import com.esic.selenium.datePicker.DateOfBirth;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.pom.insertnewip.EmployeeRegistrationForm1POM;
import com.esic.util.DropdownUtil;

public class FillFormRegisterNewIPAction implements Action {

	final static Logger logger = Logger.getLogger(FillFormRegisterNewIPAction.class);

	public void perform(WebDriver driver, ESICRecord record) {

		
		logger.info("going to fill new IP form");
		ESICDate doa = record.getDateOfAppointmentESICDate();

		EmployeeRegistrationForm1POM form = PageFactory.initElements(driver, EmployeeRegistrationForm1POM.class);
		enterDateOfAppointment(doa, form);

		// TODO: THIS PART IS LEFT UNIMPLEMENTED FOR NOW
		// SelectdispensaryOrImp("imp", form);

		// PERSONALDETAILS.java

		enterEmpName(record.getEmployeeName(), form);
		selectMartialStatus(record.getMatitalStatus(), form);

		int isHusband = Integer.parseInt(record.getIsHusband());
		enterFatherOrHusbandName(isHusband, record.getHusbandOrFatherName(), form);

		enterDateOfBirth(record.getDateOfBirthESICDate(), form);
		enterAadharCard(record.getAadharID(), form);
		selectGender(record.getGender(), form);

		
	
		FillFormSectionPresentContactDetailsAction presentContactDetailsAction = new FillFormSectionPresentContactDetailsAction();
		presentContactDetailsAction.perform(driver, record);

		FillFormSectionPermanentContactDetailsAction permanentContactDetailsAction = new FillFormSectionPermanentContactDetailsAction();
		permanentContactDetailsAction.perform(driver, record);
		
		
		FillNomineeDetailsAction nomineeDetailsAction = new FillNomineeDetailsAction();
		nomineeDetailsAction.perform(driver, record);
		
		FillFamilyPerticularsFormAction familyPerticularsAction = new FillFamilyPerticularsFormAction();
		familyPerticularsAction.perform(driver, record);
		
		
		
	}

	// not mandatory
	void SelectdispensaryOrImp(String value, EmployeeRegistrationForm1POM form) {
		if (value != null && !value.trim().equals("")) {
			if (value.trim().equalsIgnoreCase("Dispensary")) {
				form.dispensaryRadioButton.click();
			} else if (value.trim().equalsIgnoreCase("IMP")) {
				form.impRadioButton.click();
			} else
				logger.error("Incorrect value provided for selecting dispensary/IMP Radio Button");
		}
	}

	// date of apponitment : mandatory
	// acc to site it has to be dd/mm/yyyy
	void enterDateOfAppointment(ESICDate doa, EmployeeRegistrationForm1POM form) {
		// perform date validation and fetch dd, mm and yyyy
		form.dateOfAppointment.click(); // //this will lead to creation of
										// datepicker element in dom
		DateOfAppointment pickDate = PageFactory.initElements(ESICFireFoxWebDriver.getInstance(), DateOfAppointment.class);
		pickDate.selectDateOnDatePicker("" + doa.getYear(), doa.getMonth(), "" + doa.getDate());

	}

	// mandatory
	void enterEmpName(String name, EmployeeRegistrationForm1POM form) {
		form.empName.clear();
		form.empName.sendKeys(name.trim());
	}

	// mandatory
	// esic record and excel sheet needs to be modified accordingly
	public void enterFatherOrHusbandName(int selector, String name, EmployeeRegistrationForm1POM form) {
		// 0: father
		// 1: husband
		if (selector == 0)
			form.fatherRadioButton.click();
		else if (selector == 1)
			form.husbandRadioButton.click();
		else {
			logger.error("Wrong input entered for father/husband radio button");
			// Launch.record.setAutoEsicComments("Wrong input entered for father/husband radio button");
			JOptionPane.showMessageDialog(null, "Please select correct value for father/husband before clicking okay", "Wrong value", JOptionPane.ERROR_MESSAGE);
			return;
		}
		form.husbandOrfatherName.clear();
		form.husbandOrfatherName.sendKeys(name.trim());
		logger.info("selected is " + selector);
	}

	// date of birth : mandatory & age cannot be less than 14
	// acc to site it has to be dd/mm/yyyy
	void enterDateOfBirth(ESICDate dob, EmployeeRegistrationForm1POM form) {
		// perform date validation and fetch dd, mm and yyyy
		form.dateOfBirth.click(); // this will lead to creation of datepicker
									// element in dom
		DateOfBirth pickDate = PageFactory.initElements(ESICFireFoxWebDriver.getInstance(), DateOfBirth.class);
		// to do: perform validation that the values entered are valid
		pickDate.selectDateOnDatePicker(dob);

	}

	// aadhar card: not mandatory
	void enterAadharCard(String aadharCardName, EmployeeRegistrationForm1POM form) {
		form.aadharCard.clear();
		if (aadharCardName != null && !aadharCardName.trim().equals(""))
			form.aadharCard.sendKeys(aadharCardName.trim());
	}

	// martial status: mandatory :ask if int value is poss or not
	void selectMartialStatus(String martialValue, EmployeeRegistrationForm1POM form) {
		form.martialStatus.click();
		DropdownUtil.selectDropdown(martialValue, form.martialStatus);
	}

	// gender(sex) : mandatory
	void selectGender(String gender, EmployeeRegistrationForm1POM form) {
		if (gender.equalsIgnoreCase("Male")) {
			form.maleRadioButton.click();
		} else if (gender.equalsIgnoreCase("Female")) {
			form.femaleRadioButton.click();
		} else {
			logger.error("Incorrect option provided for gender");
			// Launch.record.setAutoEsicComments("Incorrect option provided for gender");
			JOptionPane.showMessageDialog(null, "Please select correct option for gender before clicking okay", "Wrong value", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	
	
	
	

}
