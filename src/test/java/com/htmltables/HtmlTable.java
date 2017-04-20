package com.htmltables;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.tables.TablePages;
import com.utils.BaseUtils;
import com.utils.ExcelUtils;
import com.utils.TestngContext;

public class HtmlTable extends BaseUtils {

    private TablePages tp;
    private static String EXCEL_FILE = "src/test/resources/HTMLTables.xlsx";
    private static String EXCEL_FILE2 = "src/test/resources/test.xlsx";
    private static String EXCEL_WORKSHEET1 = "HTML Table";
    private static String EXCEL_WORKSHEET2 = "HTML Table 2";

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) {
        TestngContext.setContext(context); // sets the context from Testng,xml
        setDriver(); // sets the driver
        NavigatToUrl(); // Navigate to primary webpage

        tp = new TablePages(getDriver());
    }

    @Test(priority = 10)
    public void verifyWorksheetHTMLTable() throws Exception {

        tp.printTable();
        ExcelUtils.setExcelFile(EXCEL_FILE, EXCEL_WORKSHEET1);
        Assert.assertEquals(tp.getTableHeader(tp.tableRows, 0, 0), ExcelUtils.getCellData(0, 0));
        Assert.assertEquals(tp.getTableCell(tp.tableRows, 1, 0), ExcelUtils.getCellData(1, 0));

    }

    @Test(priority = 20)
    public void verifyWorksheet2HTMLTable() throws Exception {

        ExcelUtils.setExcelFile(EXCEL_FILE, EXCEL_WORKSHEET2);
        Assert.assertEquals("Country", ExcelUtils.getCellData(0, 0));
        Assert.assertEquals("Germany", ExcelUtils.getCellData(1, 0));

    }


    @Test(priority = 30)
    public void writeFileAlist() throws Exception {

        ExcelUtils.createWorksheet(EXCEL_FILE2, EXCEL_WORKSHEET1);

        List<String> header = Arrays.asList("Country", "Contact", "Company");
        ExcelUtils.writeRow(EXCEL_FILE2, header);
        List<String> data = Arrays.asList("Germany", "Maria Anders", "Alfreds Futterkiste");
        ExcelUtils.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Mexico", "Francisco Chang", "Centro comercial Moctezuma");
        ExcelUtils.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Austria", "Roland Mendel", "Ernst Handel");
        ExcelUtils.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("UK", "Helen Bennett", "Island Trading");
        ExcelUtils.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Canada", "Yoshi Tannamuri", "Laughing Bacchus Winecellars");
        ExcelUtils.writeRow(EXCEL_FILE2, data);
        data = Arrays.asList("Italy", "Giovanni Rovelli", "Magazzini Alimentari Riuniti");
        ExcelUtils.writeRow(EXCEL_FILE2, data);

    }

    @Test(priority = 40)
    public void writeFileAsObject() throws Exception {

        ExcelUtils.createWorksheet(EXCEL_FILE2, EXCEL_WORKSHEET2);

        Object[][] data = {
            {"Head First Java", "Kathy Serria", 79},
            {"Effective Java", "Joshua Bloch", 36},
            {"Clean Code", "Robert martin", 42},
            {"Thinking in Java", "Bruce Eckel", 35},
        };


        ExcelUtils.writeRow(EXCEL_FILE2, data);

    }

    @AfterMethod(alwaysRun = true)
    public void validateTest(ITestResult result) {
        takeScreenShot(result);
    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        tearDown();
    }

}
