package com.esic.selenium.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.pom.PortalHomePOM;

public class OpenLinkInserIPDetailsAction implements Action {

	public void perform(WebDriver driver, ESICRecord record) {

		PortalHomePOM portalHome = PageFactory.initElements(driver,
				PortalHomePOM.class);
		portalHome.insertIPDetails.click();

	}

}
