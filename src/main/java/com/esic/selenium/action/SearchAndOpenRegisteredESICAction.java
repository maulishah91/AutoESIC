package com.esic.selenium.action;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.exception.ESICActionFailedException;
import com.esic.exception.ESICSystemException;
import com.esic.selenium.pom.SearchRegisteredESICPOM;

public class SearchAndOpenRegisteredESICAction   implements Action{

	
	final static Logger logger = Logger
			.getLogger(OpenESICWebsiteAction.class);
	
	
	public void perform(WebDriver driver, ESICRecord record)
	{
	
		SearchRegisteredESICPOM registeredESICSearch = PageFactory.initElements(driver,SearchRegisteredESICPOM.class);
		
		registeredESICSearch.esicNumber.clear();
		registeredESICSearch.esicNumber.sendKeys(record.getESICNo());
		registeredESICSearch.searchButton.click();

		
		WebElement radionBUtton = registeredESICSearch.searchResultTable.findElement(By.tagName("input"));
		
		
		if (radionBUtton == null) {
			throw new ESICActionFailedException(
					"not able to find record as per given ESIC Number.", null,
					this);
		}
		
		
		if(!radionBUtton.getAttribute("type").equals("radio"))
		{
			throw new ESICSystemException("can not locate radio button for record", null);
		}
		

		
		radionBUtton.click();
		
		 
		registeredESICSearch.editBUtton.click();;
		
		
	}
		
}
