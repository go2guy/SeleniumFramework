package com.htmltables;

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

	@AfterMethod(alwaysRun = true)
	public void validateTest(ITestResult result) {
		takeScreenShot(result);
	}

	@AfterClass(alwaysRun = true)
	public void terminateApp() {
		tearDown();
	}

}
