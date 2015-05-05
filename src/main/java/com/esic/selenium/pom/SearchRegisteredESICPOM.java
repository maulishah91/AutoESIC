package com.esic.selenium.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchRegisteredESICPOM {

	
	
	@FindBy(id="ctl00_HomePageContent_ctrlTextTINOrPIN")
	public	WebElement esicNumber;
	
	
	@FindBy(id="ctl00_HomePageContent_Button1")
	public	WebElement searchButton;
	
	
	
	@FindBy(id="ctl00_HomePageContent_gdvRegistredEmploee")
	public	WebElement searchResultTable;

	@FindBy(id="ctl00_HomePageContent_ctrlBtnEdit")
	public WebElement editBUtton;
	
	
	
	
	
	
	
}
