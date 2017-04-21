package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import com.drivers.DriverInit;

public class BaseUtils extends DriverInit {

	public void NavigatToUrl() {
		driver.navigate().to(getParam("baseURL"));
	}

	public void NavigatToUrl(String url) {
		driver.navigate().to(url);
	}

	public void clickElement(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(Keys.RETURN);

	}

	// this is example of overload
	public void clickElement(By element) {

		wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(Keys.RETURN);

	}

	public boolean verifyTitle(String title) {

		return wait.until(ExpectedConditions.titleContains(title));

	}

	// adding new method to send text
	public void sendTextToInputFld(By element, String what) {

		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(what);
	}

	public void sendTextToInputFld(WebElement element, String what) {

		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(what);
	}

	public void selectDropDown(By type, String what) {

		wait.until(ExpectedConditions.elementToBeClickable(type));
		WebElement element = driver.findElement(type);

		Select oSelect = new Select(element);

		oSelect.selectByVisibleText(what);

	}

	public void selectDropDown(WebElement type, String what) {

		wait.until(ExpectedConditions.elementToBeClickable(type));

		Select oSelect = new Select(type);

		oSelect.selectByVisibleText(what);

	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	public static String getParam(String what) {
		return TestngContext.getContext().getCurrentXmlTest().getParameter(what);
	}

	public static String getDate(String format, int days_to_add) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days_to_add); // number of days to add
		String myDateString = (String) (sdf.format(c.getTime()));
		return myDateString;
	}

	public static String takeScreenShot(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotName = getParam("ScreenShotDirectory") + "/" + getDate("yyyy-MM-dd", 0) + "/"
					+ getDate("yyyy-MM-dd-H-m-s", 0) + "_" + result.getName() + ".png";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(screenShotName));
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			return screenShotName;
		}
		return "";

	}
}
