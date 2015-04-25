package com.esic.Dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;
import com.esic.domain.annotations.ESICExcelColumns;
import com.esic.domain.helper.ESICRecordHelper;
import com.esic.exception.ESICException;

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

		
		// Traversing over each row of XLSX file
		
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			//skipping first 3 header rows.. 0 ,1 and 2nd rows.
			if (row.getRowNum() >2) {
				
				if(isFirstColumnEmpty(row))
				{
					break;
				}
				else
				{	
				records.add(getRecordFromRow(row));
			}
			}

		}

		return records;
	

	}

	/**
	 * is first column empty for record./excewl row..
	 * @param row
	 * @return
	 */
	private boolean isFirstColumnEmpty(Row row) {

		row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
		String value = row.getCell(0).getStringCellValue();

//		null then empty so send true .. or check one more time if its empty or not ..//
		return (value == null || value.trim().isEmpty());

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
			String value = null;

			Cell c = row.getCell(position);

			if (c != null) {

		//		System.out.println(c.getCellType());

				if (c.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(c))
				{

					Date d = c.getDateCellValue();
					value = com.esic.util.DateUtil.ddMMyyFormat.format(d);

				}
				else
				{
					// FIXME: decide if this code really has no impact on cell
					// contents..
					// seems changing cell time might loose some data about
					// cell..
					int celltype = c.getCellType();
					c.setCellType(Cell.CELL_TYPE_STRING);
					value = c.getStringCellValue();
				//	c.setCellType(celltype);
				}

				logEmptyValue(row, column, value);
				record.put(column.name(), value);

			}

		}

		ESICRecordHelper.populateDependentList(record);

		return record;
	}

	private void logEmptyValue(Row row, ESICExcelColumns column, String value) {
		if(value == null)
		{
			logger.warn("Empty Value for "+ column.name() + "At row "+ row.getRowNum());
		}
	}
	
	
	
	public void updateRecord(ESICRecord record)
 {
		
		Workbook workbook = record.getExcelRow().getSheet().getWorkbook();

		String fileName = ObjectStore.getFileName();

		if (record == null || fileName == null
				|| record.getExcelRow().getSheet().getWorkbook() == null) {
			String message = "Please populate  ObjectStore.getFileName() or record or record.getExcelRow().getSheet().getWorkbook()";
			logger.error(message, null);
			throw new ESICException(message, null);
		} else {
			logger.debug("updating " + fileName + "-OP.xlsx  ->" + "for" +record);
			ObjectStore.getFilereader().updateExcelFile(fileName + "-OP.xlsx",
					workbook);
		}

	}
}
