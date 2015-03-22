package com.esic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.esic.homePage.UserHomePage;
/**
 * 
 * @author Mauli
 *
 */
public class Launch {

	static WebDriver driver;
	final static Logger logger = Logger
			.getLogger(Launch.class);
	
	public static void main(String[] args) throws InterruptedException {
		try{
		//call excel file reader	
		loadDriver();
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
		homePage.closePopupForSession();
		homePage.logout();
		closeDriver();
		}
		catch(Exception e){
		e.printStackTrace();
		logger.error("An error has occured. Exiting the application.");
		closeDriver();
		}
		//Thread.sleep(1000000);
		//commenting the UI related part for now
		//JOptionPane.showConfirmDialog(null,"Hello");
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

}


