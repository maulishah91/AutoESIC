package com.esic.selenium.secondaryForm;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.selenium.contactDetails.NomineeContactDetails;
import com.esic.selenium.prelogin.Launch;
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
	
	public NomineeContactDetails process(){
		findNomineeDetails();
		selectRelationshipWithIP("Dependant unmarried daughter");
		boolean isFamilyMem=isNomineeAFamilyMember("no");
		if(!isFamilyMem){
			//aadhar card details:
			enterAadharID("1050234234");
			
		}
		return new NomineeContactDetails();
		
	}
	
	public void findNomineeDetails(){
		/*WebElement elem = Launch.driver.findElement(By.xpath("//td[contains(.,'Details of Nominee')]/following-sibling::td")); 
		if (elem == null)  System.out.println("The nominee text is not found on the page!");
		else{
			System.out.println("here we goo: "+elem.getText());
		}*/
		nomineeLink.click();
		logger.info("link is : "+nomineeLink.getText());
		Launch.switchToNewWindow(); //check what happens when there are multiple handles, think of implementing LIFO approach to resolve the issue
		
	}

	private void enterAadharID(String value) {
		aadharID.clear();
		aadharID.sendKeys(value);
	}
	
	
	private void selectRelationshipWithIP(String relationValue){
		relationshipWithIp.click();
		List<WebElement> options=relationshipWithIp.findElements(By.xpath("./option"));
		for(WebElement w:options){
			if(w.getText().trim().equalsIgnoreCase(relationValue.trim())){
				logger.info("Relationship selected is : "+relationValue);
				w.click();
				break;
			}
		}
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
