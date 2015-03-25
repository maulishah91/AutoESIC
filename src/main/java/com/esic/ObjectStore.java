package com.esic;

import com.esic.Dao.ESICExcelDAO;
import com.esic.processor.ESICProcessor;
import com.esic.processor.ESICRecordProcessor;
import com.esic.ui.UI;

public class ObjectStore {

	static UI ui;

	static ESICExcelDAO excelDAO;

	static ESICProcessor processor;

	static ESICRecordProcessor recordProcessor;

	public static ESICRecordProcessor getRecordProcessor() {

		if (recordProcessor == null) {
			recordProcessor = new ESICRecordProcessor();
		}
		return recordProcessor;
	}

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

}
