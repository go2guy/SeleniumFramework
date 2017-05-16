package com.htmltables;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.tables.TablePages;
import com.utils.BaseUtils;
import com.utils.ExcelUtils;

public class HtmlTable2 extends BaseUtils {

    private TablePages TP;
    private static String EXCEL_FILE = "src/test/resources/HTMLTables.xlsx";
    private static String EXCEL_WORKSHEET1 = "HTML Table";
    private static String EXCEL_WORKSHEET2 = "HTML Table 2";
    private ExcelUtils ReadBook;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {
        setDriver(); // sets the driver
        NavigatToUrl(); // Navigate to primary webpage

        TP = new TablePages(getDriver());

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

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        tearDown();
    }


}
