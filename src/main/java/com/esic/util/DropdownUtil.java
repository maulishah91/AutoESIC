package com.esic.util;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtil {
	
	final static Logger logger = Logger.getLogger(DropdownUtil.class);

	public static boolean selectDropdown(String value,
            WebElement element) {
		if(value.trim().equals("")){
			return false;
		}
        Select dropDown = new Select(element);

        String currentValue;
        String previousValue = null;
        while (true) {

            currentValue = dropDown.getFirstSelectedOption().getText().trim();

            if (currentValue.trim().equalsIgnoreCase(value.trim())) {
            	System.out.println("Value "+value+" found in the dropdown");
                return true;
            } else {
                previousValue = currentValue;
                element.sendKeys(Keys.ARROW_DOWN);
                currentValue = dropDown.getFirstSelectedOption().getText()
                        .trim();

                // if on pressing down we have the same value, we can conclude that is the end of the list
                if (currentValue.equals(previousValue) && !currentValue.contains("Select")) {
                	//sometimes "please select" repeats twice, 
                	//so the contains condition above is stated to stop the dropdown from ending prematurely
                	logger.error("Value "+value+" not found in the dropdown");
                    JOptionPane.showMessageDialog (null, "Value "+value+" not found in dropdown. Selecting last value by default. Please select the correct value before submitting.", "Value not found", JOptionPane.ERROR_MESSAGE);
                	return false;
                }

            }
        }
        }
}
