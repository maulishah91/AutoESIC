package com.esic.selenium.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.pom.InsertIPDetailsPOM;

public class DownloadEmployerIPDetailsAction implements Action {

	public void perform(WebDriver driver, ESICRecord record) {

		
		
		InsertIPDetailsPOM insertIPDetailsPOM = PageFactory.initElements(driver,
				InsertIPDetailsPOM.class);

		insertIPDetailsPOM.downloadButton.click();

		
		
	}

}
