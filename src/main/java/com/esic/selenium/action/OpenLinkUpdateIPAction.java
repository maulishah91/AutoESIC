package com.esic.selenium.action;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.pom.PortalHomePOM;

public class OpenLinkUpdateIPAction  implements Action {

	
	final static Logger logger = Logger
			.getLogger(OpenLinkUpdateIPAction.class);
	
	public void perform(WebDriver driver, ESICRecord record) {
		PortalHomePOM portalHome = PageFactory.initElements(driver,PortalHomePOM.class);
		portalHome.updateIP.click();
		
	}

}
