package com.esic.selenium.driver;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.esic.ObjectStore;
import com.esic.exception.ESICWebException;

public class ESICFireFoxWebDriver extends FirefoxDriver {

	public static ESICFireFoxWebDriver instance;

	protected ESICFireFoxWebDriver(FirefoxProfile profile) {
		super(profile);
	}

	public void switchToWindowWithTitle(String title) {

		Set<String> handlerSet = this.getWindowHandles();

		boolean found = false;
		for (String handler : handlerSet) {

			switchTo().window(handler);
			if (this.getTitle().trim().equals(title.trim())) {
				found = true;
				break;
			}

		}

		if (!found) {
			throw new ESICWebException("can not find window", null);
		}

	}

	public static ESICFireFoxWebDriver getInstance() {

		if(instance!=null){
			return instance;
		}
			
		String downloadLocation = ObjectStore.getUI().getFileDownloadLocation();

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		
		
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.download.dir", downloadLocation);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

		firefoxProfile.setPreference("pdfjs.disabled", true);

		// Use this to disable Acrobat plugin for previewing PDFs in Firefox (if you have Adobe reader installed on your computer)
		firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
		firefoxProfile.setPreference("plugin.scan.plid.all", false);

		
		
		

		firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.ms-excel;application/msword;application/octet-stream");
		
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting",
				false);
		firefoxProfile.setPreference("browser.download.manager.focusWhenStarting",
				false);
		firefoxProfile.setPreference("browser.download.useDownloadDir", true);
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
		firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",
				false);
		firefoxProfile.setPreference("browser.download.manager.useWindow", false);
		firefoxProfile.setPreference(
				"services.sync.prefs.sync.browser.download.manager.showWhenStarting",
				false);
		firefoxProfile.setPreference("pdfjs.disabled", true);

		instance = new ESICFireFoxWebDriver(firefoxProfile);
		return  instance;

	}
}
