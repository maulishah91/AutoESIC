package com.esic.selenium.contactDetails;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.esic.util.DropdownUtil;

/**
 * 
 * @author Mauli
 *Made this class since these details are repeated twice
 */
public abstract class ContactDetails {

	final static Logger logger = Logger.getLogger(ContactDetails.class);
	List<WebElement> mandatoryAddress;
	protected WebElement district;
	protected boolean isStateEntered=false;
	
	//permanent address : mandatory
		public void enterMandatoryAddress(String address){
			loadAddressValues();
			address=address.trim();
			int lineNo=0;
			String addressLine="";
			while(!address.equals("") && lineNo<3){
				if(address.length()>50){
				addressLine=address.substring(0, 49);}
				else addressLine=address;
				mandatoryAddress.get(lineNo).clear();
				mandatoryAddress.get(lineNo).sendKeys(addressLine);
				address=address.replaceFirst(addressLine, "");
				logger.info("address line "+lineNo+" : "+addressLine);
				lineNo++;
			}
		}
		
		//state: mandatory
		protected void selectState(String stateValue,WebElement state){
			state.click();
			isStateEntered=DropdownUtil.selectDropdown(stateValue, state);
		}
		
		//district: mandatory
		protected void selectDistrict(String districtName){
			if(isStateEntered){
				loadDistrict();
				district.click();
				DropdownUtil.selectDropdown(districtName, district);			}	
			else{
				logger.error("Select State before selecting district");
			}
		}
		
		//for email,phone no,std code for phone no, mobile no and pincode : not mandatory
		protected void enterDetail(String value,WebElement element){
			element.clear();
			element.sendKeys(value.trim());
			logger.info("Value entered is "+value);
		}
		
		protected abstract void loadAddressValues();
		
		protected abstract void loadDistrict();
		
		public abstract void enterDetailForField(String fieldName,String fieldValue);
}
