package com.esic.selenium.action;

import org.openqa.selenium.WebDriver;

import com.esic.domain.ESICRecord;

public interface Action {

	
	public void perform(WebDriver driver, ESICRecord record);
	 
}
