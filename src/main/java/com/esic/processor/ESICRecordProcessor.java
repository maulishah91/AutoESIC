package com.esic.processor;

import java.util.List;

import com.esic.domain.ESICRecord;

public class ESICRecordProcessor {

	public void processRecord(ESICRecord record) {

	}

	public void processRecords(List<ESICRecord> records) {
		
		for (ESICRecord esicRecord : records) {

			processRecord(esicRecord);
		}

	}

}
