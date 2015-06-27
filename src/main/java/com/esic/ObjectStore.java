package com.esic;

import java.util.ArrayList;
import java.util.List;

import com.esic.Dao.ESICExcelDAO;
import com.esic.Dao.XLSXFileReader;
import com.esic.selenium.processor.ESICProcessor;
import com.esic.ui.UI;

public class ObjectStore {

	public static List<String> blockedUsers = new ArrayList<String>();

	static List<String> username_to_block=new ArrayList<String>(); //if authentication fails, disallow rest of the rows have same username to login
	
	
	static UI ui;

	static ESICExcelDAO excelDAO;

	static ESICProcessor processor;

	private static String fileName;

	private static final XLSXFileReader fileReader = new XLSXFileReader();

	public static ESICExcelDAO getExcelDAO() {

		if (excelDAO == null) {
			excelDAO = new ESICExcelDAO();
		}
		return excelDAO;
	}

	public static ESICProcessor getProcessor() {

		if (processor == null) {
			processor = new ESICProcessor();
		}
		return processor;
	}

	public static UI getUI() {
		if (ui == null) {
			ui = new UI();
		}
		return ui;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		ObjectStore.fileName = fileName;
	}

	public static XLSXFileReader getFilereader() {
		return fileReader;
	}

	public static ESICProcessor getIPDetailsDownloadProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

}
