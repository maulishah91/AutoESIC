package com.esic.processor;

import java.util.List;

import org.apache.log4j.Logger;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;

public class ESICRecordProcessor {

	final static Logger logger = Logger.getLogger(ESICRecordProcessor.class);

	/**
	 * main method to process a single record
	 * It will not update {@link ESICRecord#autoEsicComments} and {@link ESICRecord#autoEsicStatus} columns.
	 * As soon as this function ends it will get updated in excel file..
	 * @param record
	 */
	public void processRecord(ESICRecord record) {
		
		
		logger.debug(record);
		
		record.setAutoEsicStatus("PASSTEST");
		

	}

	public void processRecords(List<ESICRecord> records) {
		
		for (ESICRecord esicRecord : records) {

			processRecord(esicRecord);
			
			ObjectStore.getExcelDAO().updateRecord(esicRecord);
			
		}

	}

}
