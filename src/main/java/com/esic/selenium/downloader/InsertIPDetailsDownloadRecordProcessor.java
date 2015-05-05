package com.esic.selenium.downloader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.processor.ESICRecordProcessorBase;
import com.esic.selenium.action.DownloadEmployerIPDetailsAction;
import com.esic.selenium.action.LoginAction;
import com.esic.selenium.action.OpenESICWebsiteAction;
import com.esic.selenium.action.OpenLinkInserIPDetailsAction;
import com.esic.selenium.driver.ESICFireFoxWebDriver;

public class InsertIPDetailsDownloadRecordProcessor extends ESICRecordProcessorBase {

	

	final static Logger logger = Logger
			.getLogger(InsertIPDetailsDownloadRecordProcessor.class);
	
	@Override
	public void processRecord(ESICRecord record) {
		
		try{
			
		
		
		ESICFireFoxWebDriver driver = loadDriver();
		
		
		OpenESICWebsiteAction action = new OpenESICWebsiteAction();
		action.perform(driver, record);
		
		
		
		LoginAction loginAction = new LoginAction();
		loginAction.perform(driver, record);
	
		
		
		String parentHandler = driver.getWindowHandle();
		
		
		OpenLinkInserIPDetailsAction openLinkInserIPDetailsAction = new OpenLinkInserIPDetailsAction();
		openLinkInserIPDetailsAction.perform(driver, record);

		
		
		driver.switchToWindowWithTitle("Employer IPDetails");
		
		
		DownloadEmployerIPDetailsAction downloadEmployerIPDetailsAction = new DownloadEmployerIPDetailsAction();
		downloadEmployerIPDetailsAction.perform(driver, record);
		
		JOptionPane.showMessageDialog(ObjectStore.getUI().frame, "Please click OK after you are sure that file is downloaded.");
		driver.close();
		driver.switchTo().window(parentHandler);

		
		
		record.setAutoEsicStatus("DOWNLOADED");
		
		
		}
		catch(Exception exception)
		{
			record.setAutoEsicStatus("DOWNLOAD_ERROR");
			record.setAutoEsicComments(exception.getMessage());
			
			
		}
	}
	
	


}
