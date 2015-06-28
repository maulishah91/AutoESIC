package com.esic.selenium.action.insertnewip;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.pom.PortalHomePOM;

public class OpenLinkRegisterNewIPDetailsAction implements Action {

	public void perform(WebDriver driver, ESICRecord record) {

		PortalHomePOM portalHome = PageFactory.initElements(driver,
				PortalHomePOM.class);
		portalHome.registerNewIP.click();

	}

}
