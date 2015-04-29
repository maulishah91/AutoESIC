package com.esic.selenium.prelogin;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.esic.domain.ESICRecord;
/**
 * 
 * @author Mauli
 *user story 4, 19(checkbox one) and 23 onwards are pending
 *
 *This process will load and close the drivers and call call the InitialisePO to load
 *and execute the first page object aka PreLogin
 *It will also handle scenarios related to switching and closing new windows
 */
public class Launch {

	
	public static ESICRecord record;
	public static WebDriver driver;
	public static String base="";
	final static Logger logger = Logger.getLogger(Launch.class);
	
	
	
	public Launch() {
	loadDriver();
	}
	public static void main(String[] args) throws InterruptedException {
		try{	
		loadDriver();
		//call excel file reader
		//every row will call the process method
		//if login fails , the username will be added to preferences
		//for remaining rows if username matches, the row will be skipped 
		//with reason of " please validate username and password"
		Launch launchObject=new Launch();
		launchObject.process();		
		closeDriver();
		}
		catch(Exception e){
		logger.error("An error has occured. Exiting the application.",e);
		closeDriver();
		}
	}
	
	public void process() throws Exception{
		InitialisePO initPO=new InitialisePO();
		initPO.initialisePageObject(new PreLogin());
		/*For now the sequence is : 
		 * Prelogin--> Login --> UserHomePage --> IPRegistration --> EmployeeRegistrationForm1 --> PersonalDetails --> PresentContactDetails
			  --> NomineeDetails -->FamilyParticularsForm--> DetailsOfBankAccount -->SubmitFormAndExportErrors-->Logout
		*/	 
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


