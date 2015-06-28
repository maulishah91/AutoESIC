package com.esic.selenium.action.insertnewip;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.Dependent;
import com.esic.domain.ESICDate;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICException;
import com.esic.selenium.action.Action;
import com.esic.selenium.action.contact.ContactDetailsAbstractAction;
import com.esic.selenium.datePicker.FamilyMemberDateOfBirth;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.pom.insertnewip.EmployeeRegistrationForm1POM;
import com.esic.selenium.pom.insertnewip.FamilyPerticularsFormPOM;
import com.esic.selenium.prelogin.Launch;
import com.esic.util.DropdownUtil;

public class FillFamilyPerticularsFormAction extends ContactDetailsAbstractAction implements Action {

	final static Logger logger = Logger.getLogger(FillFamilyPerticularsFormAction.class);

	public void perform(WebDriver driver, ESICRecord record) {

		EmployeeRegistrationForm1POM baseForm = PageFactory.initElements(driver, EmployeeRegistrationForm1POM.class);
		
		baseForm.familyParticularsLink.click();
		
		((ESICFireFoxWebDriver) driver).switchToWindowWithTitle("FamilyDetails");
		
		FamilyPerticularsFormPOM form = PageFactory.initElements(driver, FamilyPerticularsFormPOM.class);
		
		
		//get list of dependents:
		List<Dependent> dependentIterator=record.getDependents();
		boolean firstRecord=true;
		int currentNumberOfRecords = getNumberOfDependentsPresentOnUI(driver);
		if(currentNumberOfRecords != 0) 
		{
			logger.warn("Some dependents already Present.", null);
		}
		
		
		for(Dependent dependent : dependentIterator){
			
			currentNumberOfRecords = getNumberOfDependentsPresentOnUI(driver);
			
			if(!firstRecord){
				form.add.click();
			}
			if(firstRecord){
				firstRecord=false;
			}
			enterDetailForField("name", dependent.getName(),form);
			enterDateOfBirth(dependent.getDob(),form);
			selectRelationshipWithEmployee(dependent.getRelationship(),form);
			boolean isEmpResiding=isEmployeeResidingWithMember(dependent.isResidingWithHim(),form);
			if(!isEmpResiding){
				enterDetailForField("state", dependent.getState());
				enterDetailForField("district",dependent.getTown());
				enterDetailForField("aadharCard", dependent.getAadharID());
			}
			submitDetails(dependent.getRelationship(), record,form);
			
			waitForEnteredFamilyDetailToReflectOnFamilyDetailsTable(currentNumberOfRecords+1,driver);
		}
		checkTheNumberOfDependentsAdded(record, driver);
		form.close.click();
		Launch.switchToNewWindow();
	
		
		

	}
	
	private void waitForEnteredFamilyDetailToReflectOnFamilyDetailsTable(int expectedNumberOfFamilyDetails, WebDriver driver) {
		
		
		while(!(getNumberOfDependentsPresentOnUI(driver)>= expectedNumberOfFamilyDetails))
		{
			logger.info("Snoozing for one second.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		
	}

	private void selectRelationshipWithEmployee(String relationValue, FamilyPerticularsFormPOM form){
		form.relationshipWithEmployee.click();
		DropdownUtil.selectDropdown(relationValue, form.relationshipWithEmployee);
	}
	
	
	 void enterDetailForField(String fieldName, String fieldValue, FamilyPerticularsFormPOM form) {
		if(fieldName.equals("state")) selectState(fieldValue, form.state);
		else if(fieldName.equals("name")) enterDetail(fieldValue,form.name);
		else if(fieldName.equals("aadharCard")) enterDetail(fieldValue,form.aadharCard);
		
		else if(fieldName.equals("district")) selectDistrict(fieldValue, form.district);
	}
	
		//date of birth : mandatory
		//acc to site it has to be dd/mm/yyyy 
		public void enterDateOfBirth(ESICDate BirthDate, FamilyPerticularsFormPOM form){
			try{
			//perform date validation and fetch dd, mm and yyyy
			form.dateOfBirth.click(); ////this will lead to creation of datepicker element in dom
			FamilyMemberDateOfBirth pickDate=PageFactory.initElements(ESICFireFoxWebDriver.getInstance(), FamilyMemberDateOfBirth.class);
			pickDate.selectDateOnDatePicker(BirthDate);
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please select Date Of birth "
			+BirthDate.getDate()
			+"-"+BirthDate.getMonth()
			+"-"+BirthDate.getYear()+
			" then click on OK" );
			}
		}
		
		
	
	
	void checkTheNumberOfDependentsAdded(ESICRecord record, WebDriver driver){
		//number of dependents
		int number=0;
		List<WebElement> numberOfDependents=driver.findElements(By.xpath("//table[@id='ctl00_HomePageContent_GridviewIssue']//tr")); 
		if(numberOfDependents.size()>1){
			number=numberOfDependents.size()-1; ////since first tr is a header
			logger.info("number of dependents added: "+number); 
			if(number!=record.getDependents().size()){
				record.setAutoEsicComments(record.getAutoEsicComments()+"Dependents were not added correctly. Please verify the validity of values");
				logger.error("Dependents were not added correctly. Please verify the validity of values");
			//FIXME: throw new ESICException("Dependents were not added correctly. Please verify the validity of values", null);
			}
		}
		else{
			logger.error("No dependents were added");
			//FIXME throw new ESICException("No dependents were added",null);
		}
	}

	
	int getNumberOfDependentsPresentOnUI(WebDriver driver)

	{
		List<WebElement> numberOfDependents = driver.findElements(By.xpath("//table[@id='ctl00_HomePageContent_GridviewIssue']//tr"));
		logger.info("Current Number of records " + numberOfDependents.size());
		return numberOfDependents.size();
		
	}

	private boolean isEmployeeResidingWithMember(boolean value, FamilyPerticularsFormPOM form){
		if(value){
			form.yesRadioBtn.click();
			return true;
		}
		else{
			form.noRadioBtn.click();
			return false;
		}
	}
	
	
	private void submitDetails(String relationshipValue, ESICRecord record, FamilyPerticularsFormPOM form){
		if(relationshipValue.equalsIgnoreCase("Dependant father") || relationshipValue.equalsIgnoreCase("Dependant mother") ){
			form.submit.click();
			if(form.checkboxForParentsRelation.isDisplayed() && !form.checkboxForParentsRelation.isSelected())
				form.checkboxForParentsRelation.click();
			else{
				record.setAutoEsicComments("Checkbox on selecting dependent mother/father is not displayed");
				logger.error("Checkbox on selecting dependent mother/father is not displayed");
				throw new ESICException("CheckBoxNotDisplayedWhenFatherOrMotherIsSelected", null);
			}
			
			form.submit.click();
		}
		//check if any errors are displayed
		loadErrorWebElement();
	}
	
	public void loadErrorWebElement(){
		
	}

	@Override
	protected void loadAddressValues() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadDistrict() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDetailForField(String fieldName, String fieldValue) {
		// TODO Auto-generated method stub
		
	}

	

}
