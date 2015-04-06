package com.esic.selenium.secondaryForm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.selenium.prelogin.Launch;

/**
 * 
 * @author Mauli
 *
 *User story 26 and 27
 */
public class FamilyParticularsForm {

	@FindBy(xpath="//td[contains(.,'Particulars of Insured Person:')]/following-sibling::td/a")
	WebElement familyParticularsLink;
	
	public DetailsOfBankAccount process(){
		familyParticularsLink.click();
		Launch.switchToNewWindow();
		return new DetailsOfBankAccount();
	}
}
