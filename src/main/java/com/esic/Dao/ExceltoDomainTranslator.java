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

public class ExceltoDomainTranslator {

	final static Logger logger = Logger
			.getLogger(ExceltoDomainTranslator.class);

	private Field[] fields;
	private Class<ESICRecord> clazz;
	Map<Field, Integer> fieldMappingMap;

	public ExceltoDomainTranslator() {
		clazz = ESICRecord.class;
		fields = clazz.getDeclaredFields();
		fieldMappingMap = new HashMap<Field, Integer>();
		populateFieldMapingMap();

	}

	// maps excel column number to fields in object...
	private void populateFieldMapingMap() {
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			ESICExcelColumns column = f.getAnnotation(ESICExcelColumns.class);

			if (column == null) {
				logger.error("Can not find ExcelColumn annotations for field."
						+ f);
			}
			int position = column.value().ordinal();
			fieldMappingMap.put(f, position);
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

		//for all fields...
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			//get position of column in excel..
			int position = fieldMappingMap.get(field);
			String value = row.getCell(position).getStringCellValue();

			try {
				field.set(record, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("issue in mapping column:" + position
						+ " row number:" + i, e);
			}
		}

		return record;
	}
}
