package com.esic.selenium.prelogin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.selenium.homePage.UserHomePage;

/**
 * 
 * @author Mauli
 *
 *This class will initialize the page object and call the process method of each
 *The process method will then return the next page object to be loaded
 *The process continues till the process method has no more page objects to load
 *In this scenario the user will be logged out of the application
 *
 *For now the sequence is : //Prelogin--> Login --> UserHomePage --> IPRegistration --> EmployeeRegistrationForm1 --> PersonalDetails --> PresentContactDetails
  --> PermanentContactDetails --> NomineeDetails -->FamilyParticularsForm--> DetailsOfBankAccount -->SubmitFormAndExportErrors-->Logout
 */
public class InitialisePO{
	final static Logger logger = Logger.getLogger(InitialisePO.class);
	
	/**
	 * run page object..
	 * @param object
	 * @throws Exception
	 */
	public void initialisePageObject(Object object,ESICRecord record) throws Exception{		
		try {
			object=PageFactory.initElements(Launch.driver, object.getClass());
			logger.info(object.getClass());
			Method method=object.getClass().getMethod("process");
			if(method.getReturnType().equals(Void.TYPE)){
				//return type is null . no more POs to be called. hence going to the next record		
				JOptionPane.showMessageDialog(null, "Please click save afer confirm.");
				return;
			}
			Object poToInvoke=method.invoke(object);
			if(poToInvoke!=null){
				logger.info("Next PO to execute: "+poToInvoke.getClass());
				initialisePageObject(poToInvoke, record);
				return;
			}
			else
			{
			JOptionPane.showMessageDialog(null, "Please click save afer confirm.");
			}
			//check if its authentication failure
			if(ObjectStore.blockedUsers.contains(record.getEsicUserName())){
				logger.error("authentication failure");
				
			}
			logger.error("Loading of object post call of "+object.getClass() +" has failed. Exiting out of the application");
		} catch (NoSuchMethodException e){
			e.printStackTrace();
			logger.error("method does not exist",e);
		} catch (SecurityException e) {
			logger.error("Security exception occured while loading the method",e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException occured: ",e);
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException occured: ",e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException occured: ",e);
		} catch(Exception e){
			logger.error("An error occured: ",e);
		}
	}

	public static void logout(){
		Launch.closeCurrentWindowAndSwitchToBaseWindow();
		UserHomePage homePage=PageFactory.initElements(Launch.driver, UserHomePage.class);
		homePage.logout();
	}
}
