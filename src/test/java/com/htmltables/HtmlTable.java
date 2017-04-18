package com.htmltables;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.BaseUtils;
import com.utils.TestngContext;

public class HtmlTable extends BaseUtils {

	@BeforeClass(alwaysRun = true)
	public void launchBrowser(ITestContext context) {
		TestngContext.setContext(context); // sets the context from Testng,xml
		setDriver(); // sets the driver
		NavigatToUrl(); // Navigate to primary webpage
	}

	@Test(priority = 10)
	public void ReadTable() {

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
