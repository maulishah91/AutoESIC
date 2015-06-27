package com.esic.selenium.secondaryForm;


import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.domain.ESICRecord;
import com.esic.selenium.contactDetails.NomineeContactDetails;
import com.esic.selenium.prelogin.Launch;
import com.esic.util.DropdownUtil;
/**
 * 
 * @author Mauli
 * till user story 25: a lot of code can be reused
 */
public class NomineeDetails {
	
	final static Logger logger = Logger.getLogger(NomineeDetails.class);

	@FindBy(xpath="//td[contains(.,'Details of Nominee')]/following-sibling::td/a")
	WebElement nomineeLink;
	
	//name: mandatory
	@FindBy(id="ctl00_HomePageContent_ctrlTextUserName")
	WebElement name;
	
	//relationship with ip : mandatory  (has <option>) 12 options
	@FindBy(id="ctl00_HomePageContent_RelationShipWithIp")
	WebElement relationshipWithIp;
	
	//nominee family member radio buttons
	////yes:  : if yes is selected aadhar card details are not mandatory
	@FindBy(id="ctl00_HomePageContent_rbtnlistNomneeAkaFamily_0")
	WebElement yesRadioBtn;
	
	//no :  : if no is selected aadhar card details are mandatory
	@FindBy(id="ctl00_HomePageContent_rbtnlistNomneeAkaFamily_1")
	WebElement noRadioBtn;
	
	//aadhar ID
	@FindBy(id="ctl00_HomePageContent_txtAadhaarID")
	WebElement aadharID;
	
	public NomineeContactDetails process(ESICRecord record){
		findNomineeDetails();
		enterName(record.getNomineeName());
		selectRelationshipWithIP(record.getNomineeRelationship());
		boolean isFamilyMem=isNomineeAFamilyMember(record.getIsnomineeAFamilyMember());
		if(!isFamilyMem){
			//aadhar card details:
			enterAadharID(record.getNomineeAadharID());
			
		}
		return new NomineeContactDetails();
		
	}
	
	public void enterName(String nameValue){
		name.clear();
		name.sendKeys(nameValue);
	}
	
	public void findNomineeDetails(){
		nomineeLink.click();
		logger.info("link is : "+nomineeLink.getText());
		Launch.switchToNewWindow(); //check what happens when there are multiple handles, think of implementing LIFO approach to resolve the issue
		
	}

	private void enterAadharID(String value) {
		if(!value.contains("^[0-9]+$")){
			logger.error("Aadhar card value: "+value+" in excel file is incorrect. Please enter the valid value before proceeding");
			JOptionPane.showMessageDialog (null, "Aadhar card value: "+value+" in excel file is incorrect. Please enter the valid value before proceeding", "Value incorrect", JOptionPane.ERROR_MESSAGE);
		}
		else{
		aadharID.clear();
		aadharID.sendKeys(value);
		}
	}
	
	
	private void selectRelationshipWithIP(String relationValue){
		relationshipWithIp.click();
		DropdownUtil.selectDropdown(relationValue, relationshipWithIp);
	}
	
	private boolean isNomineeAFamilyMember(String value){
		if(value.equalsIgnoreCase("yes")){
			yesRadioBtn.click();
			return true;
		}
		else{
			noRadioBtn.click();
			return false;
		}
	}
}
