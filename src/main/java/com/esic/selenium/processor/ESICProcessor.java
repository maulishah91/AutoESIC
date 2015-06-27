package com.esic.selenium.processor;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.ObjectStore;
import com.esic.Dao.ESICExcelDAO;
import com.esic.domain.ESICRecord;
import com.esic.exception.ESICException;
import com.esic.selenium.processor.downloader.InsertIPDetailsDownloadRecordProcessor;

/**
 * Main backend logic class...
 * @author meet
 *
 */
public class ESICProcessor {

	final static Logger logger = Logger.getLogger(ESICProcessor.class);

	
	Map<ProcessName, ESICRecordProcessorBase> processorMap = new HashMap<ESICProcessor.ProcessName, ESICRecordProcessorBase>();
	
	
	public enum ProcessName
	{
		ESIC_FILE_PROCESS,
		ESIC_IP_DETAILS_DOWNLOAD
		
	}
	/**
	 * Main processor
	 * 
	 * @param fileName
	 */
	
	public ESICProcessor() {
		processorMap.put(ProcessName.ESIC_FILE_PROCESS, new ESICRecordProcessor());
		processorMap.put(ProcessName.ESIC_IP_DETAILS_DOWNLOAD, new InsertIPDetailsDownloadRecordProcessor());
		
		
	}
	
	public void processFile(String fileName,ProcessName processName) {
		List<ESICRecord> records;
		ObjectStore.setFileName(fileName);
		
		try {
			records = getEsicRecords(fileName);
		//	printRecords(records);

			
			
			
			ESICRecordProcessorBase recordProcessor = processorMap.get(processName);
			recordProcessor.processRecords(records);
			 
			

		} catch (Exception e) {
			logger.error("Error in Processing file", e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private List<ESICRecord> getEsicRecords(String fileName)
			throws ESICException, ParseException {
		
	
		XSSFSheet sheet;
		try {
			sheet = ObjectStore.getFilereader().readSheetInExcel(fileName);
			logger.info("Opened file " + fileName);
		} catch (IOException e) {//THROWS NEW
			logger.error("Can not open file", e);
			throw new ESICException("can not open file" + fileName, e);
		}

	//	fileReader.printExcelFile(sheet);
		ESICExcelDAO domainTranslator = ObjectStore.getExcelDAO();
		List<ESICRecord> records = domainTranslator.getESICRecords(sheet);
		return records;
	}

	public void printRecords(List<ESICRecord> records) {
		for (ESICRecord esicRecord : records) {
			System.out.println(esicRecord);
		}
	}

}
