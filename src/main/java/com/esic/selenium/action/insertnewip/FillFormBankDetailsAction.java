package com.esic.selenium.action.insertnewip;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.pom.insertnewip.BankAccountFormPOM;
import com.esic.selenium.pom.insertnewip.EmployeeRegistrationForm1POM;
import com.esic.util.DropdownUtil;

public class FillFormBankDetailsAction implements Action {

	final static Logger logger = Logger.getLogger(FillFormBankDetailsAction.class);

	public void perform(WebDriver driver, ESICRecord record) {

		EmployeeRegistrationForm1POM baseForm = PageFactory.initElements(driver, EmployeeRegistrationForm1POM.class);
		
		baseForm.bankAccountLink.click();
		
		((ESICFireFoxWebDriver) driver).switchToWindowWithTitle("Empe_BankAcc_Details");
				
		
		BankAccountFormPOM form =  PageFactory.initElements(driver, BankAccountFormPOM.class);
		
		
		form.accountNumber.clear();
		form.accountNumber.sendKeys(record.getBankAccountNo());
		
		
		DropdownUtil.selectDropdown(record.getBankAccountType(), form.typeOfAccount);
		
		
		form.nameOfBank.clear();
		form.nameOfBank.sendKeys(record.getBankAccountBankName());
		
		form.nameOfBranch.clear();
		form.nameOfBranch.sendKeys(record.getBankAccountBranchName());
		
		form.mICRCode.clear();
		form.mICRCode.sendKeys(record.getBankAccountMICR());
		
		form.iFSCCode.clear();
		form.iFSCCode.sendKeys(record.getBankAccountIFSC());
		
		form.saveBtn.click();
		
		
		//wait for ctl00_HomePageContent_ctrlLabelSaved
		form.closeBtn.click();
		
		
		
	}

}
