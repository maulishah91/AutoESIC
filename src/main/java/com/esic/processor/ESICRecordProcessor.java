package com.esic.processor;

import java.util.List;

import com.esic.domain.ESICRecord;

public class ESICRecordProcessor {

	/**
	 * main method to process a single record
	 * It will update {@link ESICRecord#autoEsicComments} and {@link ESICRecord#autoEsicStatus} columns.
	 * @param record
	 */
	public void processRecord(ESICRecord record) {
		
		
		
		
		
		
		
		

	}

	public void processRecords(List<ESICRecord> records) {
		
		for (ESICRecord esicRecord : records) {

			processRecord(esicRecord);
		}

	}

}
