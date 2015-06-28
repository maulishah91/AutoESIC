package com.esic.selenium.homePage;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.PageObject;
import com.esic.selenium.prelogin.LastPO;

/**
 * 
 * @author Mauli
 *
 *user story 29
 *And exporting all errors to Excel sheet from main form and secondary forms
 */
public class SubmitFormAndExportErrors  implements PageObject{

	//final declaration checkbox : ctl00_HomePageContent_dec_chkbox
	//submit : ctl00_HomePageContent_Submit
	
	//move pdf to a location .. probably a popup
	//check the name of pdf generated
	
	

	public PageObject process(ESICRecord record) {
		return new LastPO();
	}
}

/*
 * 
 * To do so far to be resolved:
 * 1. dropdown options not getting selected
 * 2. 
 * 
 */
