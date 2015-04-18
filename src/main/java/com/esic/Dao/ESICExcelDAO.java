package com.esic.Dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.domain.ESICRecord;
import com.esic.domain.annotations.ESICExcelColumns;
import com.esic.domain.helper.ESICRecordHelper;

/**
 * this class will get all fields from {@link ESICRecord} class... 
 * 
 * it will get column number based on {@link ESICExcelColumns} enum
 * postions
 * 
 * when given excel sheet .. it will generate records based on
 * reflection.starting from row 1 in sheet.
 * 
 * @author meet
 *
 */
public class ESICExcelDAO {

	final static Logger logger = Logger.getLogger(ESICExcelDAO.class);

	/**
	 * skip first row always...
	 * 
	 * @param sheet
	 * @return
	 * @throws ParseException 
	 */ 
	public List<ESICRecord> getESICRecords(XSSFSheet sheet) throws ParseException {
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
				records.add(getRecordFromRow(row));
			}

		}

		return records;
	}

	/**
	 * generate single record from excel row.
	 * 
	 * @param row
	 * @return
	 * @throws ParseException 
	 */
	private ESICRecord getRecordFromRow(Row row) throws ParseException {
		ESICRecord record = new ESICRecord();

		// storing for back reference...
		record.setExcelRow(row);

		for (ESICExcelColumns column : ESICExcelColumns.values()) {
			int position = column.ordinal();
			row.getCell(position).setCellType(Cell.CELL_TYPE_STRING);
			String value = row.getCell(position).getStringCellValue();
			record.put(column.name(), value);
			
			
		}
		
		ESICRecordHelper.populateDependentList(record);
		

		return record;
	}
}
