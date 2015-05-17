package com.esic.selenium.secondaryForm;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.esic.domain.Dependent;
import com.esic.domain.ESICDate;
import com.esic.selenium.contactDetails.ContactDetails;
import com.esic.selenium.datePicker.FamilyMemberDateOfBirth;
import com.esic.selenium.prelogin.Launch;
import com.esic.util.DropdownUtil;

/**
 * 
 * @author Mauli
 *
 *User story 26 and 27
 *Considers dependent's information
 *
 */
public class FamilyParticularsForm extends ContactDetails{
	
	final static Logger logger = Logger.getLogger(FamilyParticularsForm.class);

	@FindBy(xpath="//td[contains(.,'Particulars of Insured Person:')]/following-sibling::td/a")
	WebElement familyParticularsLink;
	
	//name : mandatory
	@FindBy(id="ctl00_HomePageContent_txtName")
	WebElement name;
	
	//dob : mandatory 
	@FindBy(id="ctl00_HomePageContent_CtrlDOB")
	WebElement dateOfBirth;
	
	//relationship with employee : 
	@FindBy(id="ctl00_HomePageContent_CtrlRelation")
	WebElement relationshipWithEmployee;
	
	//residing with him/her
	@FindBy(id="ctl00_HomePageContent_ctrlRDIpDisable_0")
	WebElement yesRadioBtn;
	
	@FindBy(id="ctl00_HomePageContent_ctrlRDIpDisable_1")
	WebElement noRadioBtn;
	
	//if no is selection for option :whether residing with him/her
	@FindBy(id="ctl00_HomePageContent_ctrlTextPermanentState")
	WebElement state;
	
	@FindBy(id="ctl00_HomePageContent_txtAadhaar")
	WebElement aadharCard;
	
	@FindBy(id="ctl00_HomePageContent_ctrlButtonSave")
	WebElement submit;
	
	@FindBy(id="ctl00_HomePageContent_dec_chkbox")
	WebElement checkboxForParentsRelation;
	
	@FindBy(id="ctl00_HomePageContent_ctrlButtonAdd")
	WebElement add;
	
	@FindBy(name="close_btn")
	WebElement close;
	
	public DetailsOfBankAccount process(){
		switchToFamilyParticularsWindow();
		//get list of dependents:
		List<Dependent> dependentIterator=Launch.record.getDependents();
		boolean firstRecord=true;
		for(Dependent dependent : dependentIterator){
			if(!firstRecord){
				add.click();
			}
			if(firstRecord){
				firstRecord=false;
			}
			enterDetailForField("name", dependent.getName());
			enterDateOfBirth(dependent.getDob());
			selectRelationshipWithEmployee(dependent.getRelationship());
			boolean isEmpResiding=isEmployeeResidingWithMember(dependent.isResidingWithHim());
			if(!isEmpResiding){
				enterDetailForField("state", dependent.getState());
				enterDetailForField("district",dependent.getTown());
				enterDetailForField("aadharCard", dependent.getAadharID());
			}
			submitDetails(dependent.getRelationship());
		}
		checkTheNumberOfDependentsAdded();
		close.click();
		Launch.switchToNewWindow();
		return new DetailsOfBankAccount();
		
	}
	
	void checkTheNumberOfDependentsAdded(){
		//number of dependents
		int number=0;
		List<WebElement> numberOfDependents=Launch.driver.findElements(By.xpath("//table[@id='ctl00_HomePageContent_GridviewIssue']//tr")); 
		if(numberOfDependents.size()>1){
			number=numberOfDependents.size()-1; ////since first tr is a header
			logger.info("number of dependents added: "+number); 
			if(number!=Launch.record.getDependents().size()){
				Launch.record.setAutoEsicComments("Dependents were not added correctly. Please verify the validity of values");
				logger.error("Dependents were not added correctly. Please verify the validity of values");
				throw new ErrorInAddingDependents();
			}
		}
		else{
			logger.error("No dependents were added");
			throw new ErrorInAddingDependents();
		}
	}

	void switchToFamilyParticularsWindow() {
		familyParticularsLink.click();
		Launch.switchToNewWindow();
	}

	@Override
	public void enterDetailForField(String fieldName, String fieldValue) {
		if(fieldName.equals("state")) selectState(fieldValue, state);
		else if(fieldName.equals("name")) enterDetail(fieldValue,name);
		else if(fieldName.equals("aadharCard")) enterDetail(fieldValue,aadharCard);
		else if(fieldName.equals("district")) selectDistrict(fieldValue);
	}
	
	private void selectRelationshipWithEmployee(String relationValue){
		relationshipWithEmployee.click();
		DropdownUtil.selectDropdown(relationValue, relationshipWithEmployee);
	}
	
	//date of birth : mandatory
	//acc to site it has to be dd/mm/yyyy 
	public void enterDateOfBirth(ESICDate BirthDate){
		//perform date validation and fetch dd, mm and yyyy
		dateOfBirth.click(); ////this will lead to creation of datepicker element in dom
		FamilyMemberDateOfBirth pickDate=PageFactory.initElements(Launch.driver, FamilyMemberDateOfBirth.class);
		pickDate.selectDateOnDatePicker(BirthDate);
		
	}
	
	private boolean isEmployeeResidingWithMember(boolean value){
		if(value){
			yesRadioBtn.click();
			return true;
		}
		else{
			noRadioBtn.click();
			return false;
		}
	}
	
	private void submitDetails(String relationshipValue){
		if(relationshipValue.equalsIgnoreCase("Dependant father") || relationshipValue.equalsIgnoreCase("Dependant mother") ){
			submit.click();
			if(checkboxForParentsRelation.isDisplayed() && !checkboxForParentsRelation.isSelected())
			checkboxForParentsRelation.click();
			else{
				Launch.record.setAutoEsicComments("Checkbox on selecting dependent mother/father is not displayed");
				logger.error("Checkbox on selecting dependent mother/father is not displayed");
				throw new CheckBoxNotDisplayedWhenFatherOrMotherIsSelected();
			}
			
		}
		//check if any errors are displayed
		loadErrorWebElement();
	}
	
	public void loadErrorWebElement(){
		
	}

	@Override
	protected void loadAddressValues() {
		//not needed here		
	}

	@Override
	protected void loadDistrict() {
		//dynamically loaded after state is selected and hence has to called after state is selected
		district=Launch.driver.findElement(By.id("ctl00_HomePageContent_ctrlTextPermanentDistrict"));
	}
	
	class CheckBoxNotDisplayedWhenFatherOrMotherIsSelected extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	class ErrorInAddingDependents extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
