package com.esic.selenium.prelogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.util.ESICRecordUtil;
/**
 * 
 * @author Mauli
 *This process will load and close the drivers and call call the InitialisePO to load
 *and execute the first page object aka PreLogin
 *It will also handle scenarios related to switching and closing new windows
 */
public class Launch {

	
	private static final String SKIPPING_THE_ROW_DUE_TO_INVALID_LOGIN_CREDENTIALS = "Skipping the row due to invalid login credentials";

	private static final String SKIPPING_RECORD_SINCE_THE_USERNAME_PASSWORD_IS_NOT_PRESENT = "Skipping record since the username/password is not present";
	
	//private static ESICRecord record;
	public static WebDriver driver;
	public static String base="";
	final static Logger logger = Logger.getLogger(Launch.class);
	
	//this will stop the account from getting locked.
	public Launch() {
	loadDriver();
	}

	/**
	 * process current record.
	 * @param record 
	 */
	public void process(ESICRecord record){
		try{
		InitialisePO initPO=new InitialisePO();
		String usernameValue=record.getEsicUserName();
		String passwordValue=record.getEsicPassword();
		
		if(!ESICRecordUtil.isLoginDetailPresent(record))
		{
			logger.info(SKIPPING_RECORD_SINCE_THE_USERNAME_PASSWORD_IS_NOT_PRESENT);
			record.setAutoEsicComments(SKIPPING_RECORD_SINCE_THE_USERNAME_PASSWORD_IS_NOT_PRESENT);
			//TODO set status to failed.
			//shortcut return as we dont need to do anything else for this record.
			return;
		}
		
		// since we have already performed the null check we can use string operations
		//ToLowerCase because while adding elements to blocklist we are storing it in lowercase
		 if(ObjectStore.blockedUsers.contains(usernameValue.toLowerCase())){
			logger.error("username "+usernameValue+" belongs to the list of invalid user credentials. Skipping the row");
			record.setAutoEsicComments(SKIPPING_THE_ROW_DUE_TO_INVALID_LOGIN_CREDENTIALS);
			//TODO set status to failed.
			//shortcut return as we dont need to do anything else for this record.
			return;
		}
		 
		
		initPO.initialisePageObject(new PreLogin(), record); 
		
		}catch(Exception e){
			logger.error("An error has occured. Exiting the application.",e);
			closeDriver();
			}
	}
	
	
	public static void loadDriver(){
		
		//short return if driver already present.
		if (driver != null) {
			return;
		}
		
		
		try{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e){
			logger.error("Error in creating Firefox Driver");
		}
	}
	
	public static void closeDriver(){
		try{
	//TODO: temporary disable.		driver.quit();
			}
			catch(Exception e){
				logger.error("Error in closing Firefox Driver");
			}
	}
	
	public static boolean switchToNewWindow(){
			if(base.equals("")){
				logger.error("Base URL has not been initialised.");
				return false;
			}
			logger.info("base window handle: "+base);
		    Set <String> set = Launch.driver.getWindowHandles();
		    logger.info("set of window handles: "+set.toString());
		    set.remove(base);
		    if(set.size()>=1){
		    Launch.driver.switchTo().window((String) set.toArray()[set.size()-1]);
		    logger.info("url: "+Launch.driver.getCurrentUrl());
		    return true;
		    }
		return false;
	}
	
	public static void closeCurrentWindowAndSwitchToBaseWindow() throws ErrorInClosingNewWindow{
		//closing second window and going back to base window
		driver.close(); //closing second window and switching to base window
		driver.switchTo().window(base);
	}
	
	class ErrorInClosingNewWindow extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;		
	}

}


