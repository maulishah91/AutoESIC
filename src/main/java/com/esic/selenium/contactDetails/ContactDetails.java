package com.esic.selenium.contactDetails;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli 
 *Made this class since these details are repeated twice
 */
public abstract class ContactDetails {

	final static Logger logger = Logger.getLogger(ContactDetails.class);
	List<WebElement> mandatoryAddress;
	WebElement district;
	boolean isStateEntered=false;
	
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
		void selectState(String stateValue,WebElement state){
			state.click();
			List<WebElement> options=state.findElements(By.xpath("./option"));
			for(WebElement w:options){
				if(w.getText().trim().equalsIgnoreCase(stateValue.trim())){
					logger.info("State selected is : "+stateValue);
					w.click();
					isStateEntered=true;
					break;
				}
			}
		}
		
		//district: mandatory
		void selectDistrict(String districtName){
			if(isStateEntered){
				loadDistrict();
				district.click();
				List<WebElement> options=district.findElements(By.xpath("./option"));
				for(WebElement w:options){
					if(w.getText().trim().equalsIgnoreCase(districtName.trim())){
						logger.info("District selected is : "+districtName);
						w.click();
						break;
					}
				}
			}	
			else{
				logger.error("Select State before selecting district");
			}
		}
		
		//for email,phone no,std code for phone no, mobile no and pincode : not mandatory
		void enterDetail(String value,WebElement element){
			element.clear();
			element.sendKeys(value.trim());
			logger.info("Value entered is "+value);
		}
		
		abstract void loadAddressValues();
		
		abstract void loadDistrict();
}
