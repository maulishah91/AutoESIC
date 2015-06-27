package com.esic.selenium.processor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICSystemException;
import com.esic.selenium.driver.ESICFireFoxWebDriver;

public abstract class ESICRecordProcessorBase {
	
	private ESICFireFoxWebDriver driver;


	public void processRecords(List<ESICRecord> records){
		
		for (ESICRecord esicRecord : records) {
			processRecord(esicRecord);
			ObjectStore.getExcelDAO().updateRecord(esicRecord);
		}
		
		
		closeDriver();
		

	}

	 private void closeDriver() {
		driver.quit();
		
	}

	abstract public void processRecord(ESICRecord esicRecord);

	 
		public ESICFireFoxWebDriver loadDriver() {

			if(driver!=null)
			{
				return driver;
			}
			try {
				driver = ESICFireFoxWebDriver.getInstance();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			} catch (Exception e) {
				String str = "Error in creating Firefox Driver";
		//		logger.error(str);
				throw new ESICSystemException(str, e);
			}

			return driver;
		}
		
}
