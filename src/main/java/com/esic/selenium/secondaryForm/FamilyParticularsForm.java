package com.esic.selenium.secondaryForm;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.esic.selenium.contactDetails.ContactDetails;
import com.esic.selenium.datePicker.FamilyMemberDateOfBirth;
import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 *User story 26 and 27
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
		enterDetailForField("name", "abcedef");
		enterDateOfBirth("24/12/1221");
		String relationshipValue="Brother";
		selectRelationshipWithEmployee(relationshipValue);
		boolean isEmpResiding=isEmployeeResidingWithMember("no");
		if(!isEmpResiding){
			enterDetailForField("state", "Kerala");
			enterDetailForField("district", "Kollam");
			//aadhar card details: dont know if this is required
			enterDetailForField("aadharCard", "1050234234");
		}
		submitDetails(relationshipValue);
		moreDetailsToBeAdded("no");
		close.click();
		Launch.switchToNewWindow();
		return new DetailsOfBankAccount();
		
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
		Select dropdown = new Select(relationshipWithEmployee);
		dropdown.selectByVisibleText(relationValue);
		int value=dropdown.getOptions().indexOf(relationValue);
		try{dropdown.getAllSelectedOptions();
		logger.info("selectRelationshipWithEmployee : "+dropdown.getAllSelectedOptions().toString());
		}
		catch(Exception e){
			dropdown.selectByIndex(value);
		}
	}
	
	//date of birth : mandatory
	//acc to site it has to be dd/mm/yyyy 
	public void enterDateOfBirth(String BirthDate){
		//perform date validation and fetch dd, mm and yyyy
		dateOfBirth.click(); ////this will lead to creation of datepicker element in dom
		FamilyMemberDateOfBirth pickDate=PageFactory.initElements(Launch.driver, FamilyMemberDateOfBirth.class);
		//to do: perform validation that the values entered are valid
		pickDate.selectDateOnDatePicker("1991","Dec","24");
		
	}
	
	private boolean isEmployeeResidingWithMember(String value){
		if(value.equalsIgnoreCase("yes")){
			yesRadioBtn.click();
			return true;
		}
		else{
			noRadioBtn.click();
			return false;
		}
	}
	
	private void moreDetailsToBeAdded(String value){
		if(value.equalsIgnoreCase("yes")){
			add.click();
			process();
		}
	}
	
	private void submitDetails(String relationshipValue){
		if(relationshipValue.equalsIgnoreCase("Dependant father") || relationshipValue.equalsIgnoreCase("Dependant motherDependant mother") ){
			submit.click();
			if(checkboxForParentsRelation.isDisplayed())
			checkboxForParentsRelation.click();
			else{
				throw new CheckBoxNotDisplayedWhenFatherOrMotherIsSelected();
			}
			
		}
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
}
