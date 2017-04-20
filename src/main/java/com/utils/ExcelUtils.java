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

    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelWSheet;
    private static XSSFCell Cell;

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    // This method is to read the test data from the Excel cell, in this we are
    // passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

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

    public static String createWorksheet(String Path, String SheetName) throws Exception {
        File file = new File(Path);
        if (!file.exists())
            ExcelWBook = new XSSFWorkbook();

        try {

            ExcelWSheet = ExcelWBook.createSheet(SheetName);

            writeOut(Path);
            return SheetName;
        } catch (Exception e) {
            return "";

        }
    }


    public static void writeRow(String Path, Object[][] data) throws IOException {

        int rowCount = ExcelWSheet.getLastRowNum();

        for (Object[] payload : data) {

            XSSFRow row = ExcelWSheet.createRow(rowCount++);

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

    public static void writeRow(String Path, List<String> data) throws IOException {

        int rowCount = ExcelWSheet.getPhysicalNumberOfRows();

        XSSFRow row = ExcelWSheet.createRow(rowCount++);

        int columnCount = 0;

        for (String field : data) {
            Cell = row.createCell(columnCount++);
            Cell.setCellValue((String) field);
        }

        writeOut(Path);
    }

    private static void writeOut(String Path) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(Path);

        //write this workbook to an Outputstream.
        ExcelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

}
