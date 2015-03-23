package com.esic.Dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.domain.ESICRecord;
import com.esic.domain.annotations.ESICExcelColumns;

/**
 * this class will get all fields from {@link ESICRecord} class... and maintain
 * list of columns which are annotated wih {@link ESICExcelColumns}
 * annotation...
 * 
 * it will get column number based on {@link ESICExcelColumns.ColumnNames} enum postions
 * 
 * when given excel sheet .. it will generate records based on
 * reflection.starting from row 1 in sheet.
 * 
 * @author meet
 *
 */
public class ExceltoDomainTranslator {

	final static Logger logger = Logger
			.getLogger(ExceltoDomainTranslator.class);

	private List<Field> excelFields;
	private Class<ESICRecord> clazz;
	Map<Field, Integer> fieldPositionMap;

	public ExceltoDomainTranslator() {
		clazz = ESICRecord.class;
		Field[] allFields = clazz.getDeclaredFields();
		populateExcelFields(allFields);
		fieldPositionMap = new HashMap<Field, Integer>();
		populateFieldPositionMap();

	}

	// filtering fields which are excel fields only...
	private void populateExcelFields(Field[] allFilds) {

		excelFields = new ArrayList<Field>();

		for (int i = 0; i < allFilds.length; i++) {
			Field f = allFilds[i];
			ESICExcelColumns column = f.getAnnotation(ESICExcelColumns.class);

			if (column == null) {

				logger.debug("not adding " + f + " to excelFields");
			} else {
				logger.debug("adding " + f + " to excelFields");
				excelFields.add(f);
			}

		}

	}

	// maps excel column number to fields in object...
	private void populateFieldPositionMap() {
		for (int i = 0; i < excelFields.size(); i++) {
			Field f = excelFields.get(i);
			ESICExcelColumns column = f.getAnnotation(ESICExcelColumns.class);

			if (column == null) {
				logger.error("Can not find ExcelColumn annotations for field."
						+ f);
			}
			int position = column.value().ordinal();
			fieldPositionMap.put(f, position);
		}
	}

	/**
	 * skip first row always...
	 * 
	 * @param sheet
	 * @return
	 */
	public List<ESICRecord> getESICRecords(XSSFSheet sheet) {
		List<ESICRecord> records = new ArrayList<ESICRecord>();

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		boolean skippedFirstRow = false;
		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (!skippedFirstRow) {
				skippedFirstRow = true;
			}
			// for normal processing or row..
			else {
				getRecordFromRow(row);
			}

		}

		return records;
	}

	private ESICRecord getRecordFromRow(Row row) {
		ESICRecord record = new ESICRecord();
		// storing for back reference...
		record.setExcelRow(row);

		// for all fields...
		for (int i = 0; i < excelFields.size(); i++) {
			Field field = excelFields.get(i);

			// get position of column in excel..
			int position = fieldPositionMap.get(field);
			String value = row.getCell(position).getStringCellValue();

			try {
				field.set(record, value);
			} catch (IllegalAccessException e) {
				logger.error("issue in mapping column:" + position
						+ " row number:" + i, e);
			}
		}

		return record;
	}
}
