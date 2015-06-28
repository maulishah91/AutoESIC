package com.esic.selenium.action.insertnewip.nominee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.action.contact.ContactDetailsAbstractAction;
import com.esic.selenium.pom.insertnewip.NomineeContactDetailsPOM;
import com.esic.selenium.prelogin.Launch;

public class FillNomineeAddressAction extends ContactDetailsAbstractAction implements Action {

	final static Logger logger = Logger.getLogger(FillNomineeAddressAction.class);

	NomineeContactDetailsPOM form;

	private WebDriver driver;
	//this is syncronized because we are using class variables.
	public synchronized void perform(WebDriver driver, ESICRecord record) {


		form = PageFactory.initElements(driver, NomineeContactDetailsPOM.class);
		this.driver = driver;
		

		enterMandatoryAddress(record.getNomineeAddress());
		enterDetailForField("state", record.getNomineeState());
		enterDetailForField("district",record.getNomineeDistrict());
		enterDetailForField("phone",record.getNomineePhoneNo());
		//on clicking save check if pop up comes
		form.saveBtn.click();
		checkForErrors();
		form.closeBtn.click();
		Launch.switchToNewWindow(); // this will get us back to the main form
	
		
		
	}

	//overriding base class method since lengths of address lines are different
	@Override
	public void enterMandatoryAddress(String address){
		loadAddressValues();
		address=address.trim();
		int lineNo=0;
		String addressLine="";
		if(!address.equals("") && address.length()>100){
			addressLine=address.substring(0, 99);
			mandatoryAddress.get(lineNo).clear();
			mandatoryAddress.get(lineNo).sendKeys(addressLine);
			address=address.replaceFirst(addressLine, "");
			lineNo++;
			while(!address.equals("") && lineNo<3){
				if(address.length()>45){
				addressLine=address.substring(0, 44);}
				else addressLine=address;
				mandatoryAddress.get(lineNo).clear();
				mandatoryAddress.get(lineNo).sendKeys(addressLine);
				address=address.replaceFirst(addressLine, "");
				logger.info("address line "+lineNo+" : "+addressLine);
				lineNo++;
			}
		}
		else{
			addressLine=address;
			mandatoryAddress.get(lineNo).clear();
			mandatoryAddress.get(lineNo).sendKeys(addressLine);
			logger.info("address line "+lineNo+" : "+addressLine);
		}
		
	}
	
	protected void loadAddressValues(){
		mandatoryAddress=new ArrayList<WebElement>();
		mandatoryAddress.add(form.mandatoryAddressLine1);
		mandatoryAddress.add(form.mandatoryAddressLine2);
		mandatoryAddress.add(form.mandatoryAddressLine3);
	}
	
	public boolean checkForErrors(){
		List<WebElement> errorList=new ArrayList<WebElement>(Arrays.asList(
				form.nameError,
				form.addressError,
				form.relationshipError,
				form.stateError,
				form.districtError,
				form.addressError2,
				form.addressLine2Error,
				form.addressLine3Error,
				form.pincodeError,
				form.mobileError,
				form.aadharCardError
				));
		
		for(WebElement el: errorList){
			if(el.isDisplayed()==true) {
				logger.error("Error displayed in the nominee window. Please correct it before proceeding");
				JOptionPane.showMessageDialog (null, "Error displayed in the nominee window. Please correct it before proceeding.", "Error Message", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Click on save in the nominee window. Then click on OK");
				return false;
			}
		}
		
		return true;
	}
	
	protected void loadDistrict(){
		//dynamically loaded after state is selected and hence has to called after state is selected
		form = PageFactory.initElements(driver, NomineeContactDetailsPOM.class);
	}
	
	public void enterDetailForField(String fieldName,String fieldValue){
		if(fieldName.equals("state")) selectState(fieldValue, form.state);
		else if(fieldName.equals("district")) selectDistrict(fieldValue, form.district);
		else if(fieldName.equals("mobile")) enterDetail(fieldValue, form.mobileNo);
		else if(fieldName.equals("pincode")) enterDetail(fieldValue, form.pincode);
		else if(fieldName.equals("stdcode")) enterDetail(fieldValue, form.phoneSTDCode);
		else if(fieldName.equals("phone")) enterDetail(fieldValue, form.phoneNo);
		else logger.error("Incorrect fieldName");
	}
	

}
