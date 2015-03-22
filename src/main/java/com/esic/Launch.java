package com.esic;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.homePage.EmployeeRegistrationForm1;
import com.esic.homePage.IPRegistration;
import com.esic.homePage.UserHomePage;
/**
 * 
 * @author Mauli
 *
 */
public class Launch {

	public static WebDriver driver;
	public static String base="";
	final static Logger logger = Logger
			.getLogger(Launch.class);
	
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
	
	private void process()throws Exception{
		PreLogin preLogin=PageFactory.initElements(driver, PreLogin.class);
		Login login=preLogin.launchLoginPage();
		if(login==null){
			throw new Exception("Loading of login page has failed. Exiting out of the application");
		}
		//to do: get username and password from excel sheet
		UserHomePage homePage= login.login("35000012800001099", "12345march");
		if(homePage==null){
			throw new Exception("Loading of home page has failed. Exiting out of the application");
		}
		base= Launch.driver.getWindowHandle(); //home page of application is the base URL
		IPRegistration ipRegister=homePage.closePopupForSession();
		if(ipRegister==null){
			throw new Exception("Loading of IP Registration page has failed. Exiting out of the application");
		}
		EmployeeRegistrationForm1 form=ipRegister.openRegisterNewIP();
		if(form==null){
			throw new Exception("Loading of Employee Registration Form-1 page has failed. Exiting out of the application");
		}
		closeCurrentWindowAndSwitchToBaseWindow();
		homePage.logout();
	}
	
	public static void loadDriver(){
		try{
		driver = new FirefoxDriver();
		}
		catch(Exception e){
			logger.error("Error in creating Firefox Driver");
		}
	}
	
	public static void closeDriver(){
		try{
			driver.quit();
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
			logger.debug("base window handle: "+base);
		    Set <String> set = Launch.driver.getWindowHandles();
		    logger.debug("set of window handles: "+set.toString());
		    set.remove(base);
		    if(set.size()>=1){
		    Launch.driver.switchTo().window((String) set.toArray()[0]);
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


