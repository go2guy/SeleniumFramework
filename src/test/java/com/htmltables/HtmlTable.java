package com.htmltables;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.tables.TablePages;
import com.utils.BaseUtils;
import com.utils.ExcelUtils;

public class HtmlTable extends BaseUtils {

    private TablePages TP;
    private static String EXCEL_FILE = "src/test/resources/HTMLTables.xlsx";
    private static String EXCEL_FILE2 = "src/test/resources/test.xlsx";
    private static String EXCEL_WORKSHEET1 = "HTML Table";
    private static String EXCEL_WORKSHEET2 = "HTML Table 2";
    private ExcelUtils WriteBook;
    private ExcelUtils ReadBook;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {
        setDriver(); // sets the driver
        NavigatToUrl(); // Navigate to primary webpage

        TP = new TablePages(getDriver());
        WriteBook = new ExcelUtils();

        ReadBook = new ExcelUtils();

    }

    @Test(priority = 10)
    public void verifyWorksheetHTMLTable() throws Exception {

        // tp.printTable();
        ReadBook.setExcelFileToRead(EXCEL_FILE, EXCEL_WORKSHEET1);
        Assert.assertEquals(TP.getTableHeader(TP.tableRows, 0, 0), ReadBook.getCellData(0, 0));
        Assert.assertEquals(TP.getTableCell(TP.tableRows, 1, 0), ReadBook.getCellData(1, 0));

    }

    @Test(priority = 20)
    public void verifyWorksheet2HTMLTable() throws Exception {

        ReadBook.setExcelFileToRead(EXCEL_FILE, EXCEL_WORKSHEET2);
        Assert.assertEquals("Country", ReadBook.getCellData(0, 0));
        Assert.assertEquals("Germany", ReadBook.getCellData(1, 0));

    }

    @Test(priority = 30)
    public void writeFileAlist() throws Exception {

        WriteBook.createWorksheet(EXCEL_FILE2, EXCEL_WORKSHEET1);

        List<String> header = Arrays.asList("Country", "Contact", "Company");
        WriteBook.writeRow(EXCEL_FILE2, header);
        List<String> data = Arrays.asList("Germany", "Maria Anders", "Alfreds Futterkiste");
        WriteBook.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Mexico", "Francisco Chang", "Centro comercial Moctezuma");
        WriteBook.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Austria", "Roland Mendel", "Ernst Handel");
        WriteBook.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("UK", "Helen Bennett", "Island Trading");
        WriteBook.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Canada", "Yoshi Tannamuri", "Laughing Bacchus Winecellars");
        WriteBook.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Italy", "Giovanni Rovelli", "Magazzini Alimentari Riuniti");
        WriteBook.writeRow(EXCEL_FILE2, data);

    }

    @Test(priority = 40)
    public void writeFileAsObject() throws Exception {

        WriteBook.createWorksheet(EXCEL_FILE2, EXCEL_WORKSHEET2);

        Object[][] data = {
            {"Head First Java", "Kathy Serria", 79},
            {"Effective Java", "Joshua Bloch", 36},
            {"Clean Code", "Robert martin", 42},
            {"Thinking in Java", "Bruce Eckel", 35},};

        WriteBook.writeRow(EXCEL_FILE2, data);

    }

    //    @AfterMethod(alwaysRun = true)
    //    public void validateTest(ITestResult result) throws Exception {
    //        //Todo
    //        //        List<String> data = null;
    //        //
    //        //        if (result.getStatus() == ITestResult.FAILURE)
    //        //            data = Arrays.asList(result.getMethod().getMethodName(), "Fail", takeScreenShot(result));
    //        //        else
    //        //            data = Arrays.asList(result.getMethod().getMethodName(), "Pass", "");
    //        //
    //        //        TestExit.writeRow(TEST_EXIT_REPORT, data);
    //    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        tearDown();
    }



}
