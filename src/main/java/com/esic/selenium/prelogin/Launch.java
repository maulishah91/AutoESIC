package com.esic.selenium.prelogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.esic.domain.ESICRecord;
/**
 * 
 * @author Mauli
 *This process will load and close the drivers and call call the InitialisePO to load
 *and execute the first page object aka PreLogin
 *It will also handle scenarios related to switching and closing new windows
 */
public class Launch {

	
	public static ESICRecord record;
	public static WebDriver driver;
	public static String base="";
	final static Logger logger = Logger.getLogger(Launch.class);
	static List<String> username_to_block=new ArrayList<String>(); //if authentication fails, disallow rest of the rows have same username to login
	//this will stop the account from getting locked.
	public Launch() {
	loadDriver();
	}

	public void process(){
		try{
		InitialisePO initPO=new InitialisePO();
		String usernameValue=Launch.record.getEsicUserName();
		String passwordValue=record.getEsicPassword();
		if(usernameValue==null || passwordValue==null){
			logger.info("Skipping record since the username/password is not present");
			Launch.record.setAutoEsicComments("Skipping record since the username/password is not present");
		}
		else if(usernameValue.equals("") || record.getEsicPassword().equals("")){
			logger.info("Skipping record since the username/password is not present");
			Launch.record.setAutoEsicComments("Skipping record since the username/password is not present");
		}
		// since we have already performed the null check we can use string operations
		//ToLowerCase because while adding elements to blocklist we are storing it in lowercase
		else if(Launch.username_to_block.contains(usernameValue.toLowerCase())){
			logger.error("username "+usernameValue+" belongs to the list of invalid user credentials. Skipping the row");
			Launch.record.setAutoEsicComments("Skipping the row due to invalid login credentials");
		}
		else{
		initPO.initialisePageObject(new PreLogin()); 
		}
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


