package com.esic.selenium.prelogin;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.esic.domain.ESICRecord;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
/**
 * 
 * @author Mauli
 *This process will load and close the drivers and call call the InitialisePO to load
 *and execute the first page object aka PreLogin
 *It will also handle scenarios related to switching and closing new windows
 */
public class Launch {

	

	//private static ESICRecord record;
//	public static WebDriver driver;
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
		InitialisePO iterativeProcessor=new InitialisePO();
		
		
		iterativeProcessor.initialisePageObject(new PreLogin(), record); 
		
		}catch(Exception e){
			logger.error("An error has occured. Exiting the application.",e);
			closeDriver();
			}
	}
	
	
	public static void loadDriver(){
		
		//short return if driver already present.
		try{
		WebDriver driver = ESICFireFoxWebDriver.getInstance();
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
		    Set <String> set = ESICFireFoxWebDriver.getInstance().getWindowHandles();
		    logger.info("set of window handles: "+set.toString());
		    set.remove(base);
		    if(set.size()>=1){
		    ESICFireFoxWebDriver.getInstance().switchTo().window((String) set.toArray()[set.size()-1]);
		    logger.info("url: "+ESICFireFoxWebDriver.getInstance().getCurrentUrl());
		    return true;
		    }
		return false;
	}
	
	public static void closeCurrentWindowAndSwitchToBaseWindow() throws ErrorInClosingNewWindow{
		//closing second window and going back to base window
		ESICFireFoxWebDriver.getInstance().close(); //closing second window and switching to base window
		ESICFireFoxWebDriver.getInstance().switchTo().window(base);
	}
	
	class ErrorInClosingNewWindow extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;		
	}

}


