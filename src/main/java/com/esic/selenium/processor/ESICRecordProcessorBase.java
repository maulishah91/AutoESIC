package com.esic.selenium.processor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.esic.ObjectStore;
import com.esic.StringConstants;
import com.esic.domain.ESICRecord;
import com.esic.domain.ESICRecordStatus;
import com.esic.exception.ESICSystemException;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.util.ESICRecordUtil;

public abstract class ESICRecordProcessorBase {
	
	final static Logger logger = Logger.getLogger(ESICRecordProcessorBase.class);
	
	private ESICFireFoxWebDriver driver;


	public void processRecords(List<ESICRecord> records){
		
		for (ESICRecord esicRecord : records) {
			
			boolean skipProcessing = false;

			if(!ESICRecordUtil.isLoginDetailPresent(esicRecord))
			{
				logger.info(StringConstants.SKIPPING_RECORD_SINCE_THE_USERNAME_PASSWORD_IS_NOT_PRESENT);
				esicRecord.setAutoEsicComments(StringConstants.SKIPPING_RECORD_SINCE_THE_USERNAME_PASSWORD_IS_NOT_PRESENT);
				esicRecord.setAutoEsicStatus(ESICRecordStatus.FAILED.toString());
				
			skipProcessing = true;
			}
			
			String usernameValue=esicRecord.getEsicUserName();
			
			// since we have already performed the null check we can use string operations
			//ToLowerCase because while adding elements to blocklist we are storing it in lowercase
			 if(ObjectStore.blockedUsers.contains(usernameValue.toLowerCase())){
				logger.error("username "+usernameValue+" belongs to the list of invalid user credentials. Skipping the row");
				esicRecord.setAutoEsicComments(StringConstants.SKIPPING_THE_ROW_DUE_TO_INVALID_LOGIN_CREDENTIALS);

				esicRecord.setAutoEsicStatus(ESICRecordStatus.FAILED.toString());
				skipProcessing = true;
			 }
			 
			 
			 
			
			if(!skipProcessing)
			{
			processRecord(esicRecord);
			}
			ObjectStore.getExcelDAO().updateRecord(esicRecord);
		}
		
		
		//closeDriver();
		

	}

	 private void closeDriver() {
		driver.quit();
		
	}

	abstract public void processRecord(ESICRecord esicRecord);

	 
	/**
	 * get driver instance per processor.
	 * @return
	 */
		public ESICFireFoxWebDriver loadDriver() {


			try{
			if(driver!=null)
			{
				//this will throw exception if driver connection is lost.
				driver.getCurrentUrl();
				//if we reach here we can return current driver.
				return driver;
			}
			}
			catch(Exception e)
			{
				logger.warn("lost browser", e);
				
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
