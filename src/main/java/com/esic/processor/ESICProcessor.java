package com.esic.processor;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.ObjectStore;
import com.esic.Dao.ESICExcelDAO;
import com.esic.Dao.XLSXFileReader;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICException;

public class ESICProcessor {

	final static Logger logger = Logger.getLogger(ESICProcessor.class);

	/**
	 * Main processor
	 * 
	 * @param fileName
	 */
	public void processFile(String fileName) {
		List<ESICRecord> records;
		try {
			records = getEsicRecords(fileName);
			printRecords(records);

			ESICRecordProcessor recordProcessor = ObjectStore
					.getRecordProcessor();
			recordProcessor.processRecords(records);
			
			
			

		} catch (ESICException e) {
			// TODO Auto-generated catch block
			logger.error("Error in Processing file", e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private List<ESICRecord> getEsicRecords(String fileName)
			throws ESICException {
		XLSXFileReader fileReader = new XLSXFileReader();
		XSSFSheet sheet;
		try {
			sheet = fileReader.readSheetInExcel(fileName);
			logger.info("Opened file " + fileName);
		} catch (IOException e) {
			logger.error("Can not open file", e);
			throw new ESICException("can not open file" + fileName, e);
		}

		fileReader.printExcelFile(sheet);
		ESICExcelDAO domainTranslator = ObjectStore.getExcelDAO();
		List<ESICRecord> records = domainTranslator.getESICRecords(sheet);
		return records;
	}

	private void printRecords(List<ESICRecord> records) {
		for (ESICRecord esicRecord : records) {
			System.out.println(esicRecord);
		}
	}

}
