package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private XSSFWorkbook ExcelWBookRead;
	private XSSFSheet ExcelWSheetRead;
	private XSSFWorkbook ExcelWBookWrite;
	private XSSFSheet ExcelWSheetWrite;
	private XSSFCell Cell;

	public void setExcelFileToRead(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBookRead = new XSSFWorkbook(ExcelFile);
			ExcelWSheetRead = ExcelWBookRead.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public String getCellData(int RowNum, int ColNum) throws Exception {
		try {

			Cell = ExcelWSheetRead.getRow(RowNum).getCell(ColNum);

			switch (Cell.getCellType()) {
			case 0:
				return String.valueOf(Cell.getNumericCellValue());
			case 1:
				return Cell.getStringCellValue();
			default:
				return "";
			}

		} catch (Exception e) {
			return "";
		}
	}

	public String createWorksheet(String Path, String SheetName) throws Exception {
		File file = new File(Path);
		if (!file.exists())
			ExcelWBookWrite = new XSSFWorkbook();

		try {

			ExcelWSheetWrite = ExcelWBookWrite.createSheet(SheetName);

			writeOut(Path);
			return SheetName;
		} catch (Exception e) {
			return "";

		}
	}

	public void writeRow(String Path, Object[][] data) throws IOException {

		int rowCount = ExcelWSheetWrite.getLastRowNum();

		for (Object[] payload : data) {

			XSSFRow row = ExcelWSheetWrite.createRow(rowCount++);

			int columnCount = 0;

			for (Object field : payload) {
				Cell = row.createCell(columnCount++);
				if (field instanceof String) {
					Cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					Cell.setCellValue((Integer) field);
				}
			}

		}

		writeOut(Path);
	}

	public void writeRow(String Path, List<String> data) throws IOException {

		int rowCount = ExcelWSheetWrite.getPhysicalNumberOfRows();

		XSSFRow row = ExcelWSheetWrite.createRow(rowCount++);

		int columnCount = 0;

		for (String field : data) {
			Cell = row.createCell(columnCount++);
			Cell.setCellValue((String) field);
		}

		writeOut(Path);
	}

	private void writeOut(String Path) throws FileNotFoundException, IOException {
		FileOutputStream fileOut = new FileOutputStream(Path);

		// write this workbook to an Outputstream.
		ExcelWBookWrite.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

}
