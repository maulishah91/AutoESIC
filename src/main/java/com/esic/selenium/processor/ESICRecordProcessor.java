package com.esic.selenium.processor;

import org.apache.log4j.Logger;

import com.esic.domain.ESICRecord;
import com.esic.selenium.prelogin.Launch;
import com.esic.selenium.processor.update.ESICRecordUpdater;

public class ESICRecordProcessor extends ESICRecordProcessorBase {

	final static Logger logger = Logger.getLogger(ESICRecordProcessor.class);

	
	private Launch seleniumProcessor;

	private ESICRecordUpdater updater = new ESICRecordUpdater();
	

	/**
	 * main method to process a single record
	 * It will not update {@link ESICRecord#autoEsicComments} and {@link ESICRecord#autoEsicStatus} columns.
	 * As soon as this function ends it will get updated in excel file..
	 * @param record
	 * @throws Exception 
	 */
	public void processRecord(ESICRecord record) {
		logger.debug(record);
		
		
		if(seleniumProcessor == null)
		{
			seleniumProcessor = new Launch();
		}
		
		
		if(record.getESICNo() != null && !record.getESICNo().isEmpty())
		{
			//update flow
			updater.processRecord(record);
		}
		else
		{
			//add flow..
			seleniumProcessor.process(record);
			logger.info("Comments are: "+record.getAutoEsicComments());
			record.setAutoEsicComments(record.getAutoEsicComments().trim());
			if(record.getAutoEsicComments()!=null && !record.getAutoEsicComments().equals("")){
				record.setAutoEsicStatus("FAILTEST");
			}
			else{
			record.setAutoEsicStatus("PASSTEST");
			}
		}
	}



}
