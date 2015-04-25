package com.esic.processor;

import java.util.List;

import org.apache.log4j.Logger;

import com.esic.domain.ESICRecord;

public class ESICRecordProcessor {

	final static Logger logger = Logger.getLogger(ESICRecordProcessor.class);

	/**
	 * main method to process a single record
	 * It will update {@link ESICRecord#autoEsicComments} and {@link ESICRecord#autoEsicStatus} columns.
	 * @param record
	 */
	public void processRecord(ESICRecord record) {
		
		
		logger.debug(record);
		

	}

	public void processRecords(List<ESICRecord> records) {
		
		for (ESICRecord esicRecord : records) {

			processRecord(esicRecord);
		}

	}

}
