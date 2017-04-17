package com.drivers;

import java.util.Set;

import com.utils.TestngContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInit {

    private static final long WAITTIME = 30;
    public static WebDriver driver;
    public static WebDriverWait wait;

    public WebDriver getDriver(DesiredCapabilities capabilities) {
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver setDriver() {

        switch (TestngContext.getContext().getCurrentXmlTest().getParameter("browse")) {

            case "Firefox":
                System.setProperty("webdriver.gecko.driver",
                    TestngContext.getContext().getCurrentXmlTest().getParameter("driverpath"));
                driver = new FirefoxDriver();
                break;

            case "Chrome":
                System.setProperty("webdriver.chrome.driver",
                    TestngContext.getContext().getCurrentXmlTest().getParameter("driverpath"));
                //                Map capabilitiesMap = new HashMap();
                //                capabilitiesMap.put("takesScreenshot", true);
                //                DesiredCapabilities capabilities = new DesiredCapabilities(capabilitiesMap);
                driver = new ChromeDriver();
                break;

            case "IE":
                System.setProperty("webdriver.ie.driver",
                    TestngContext.getContext().getCurrentXmlTest().getParameter("driverpath"));
                driver = new InternetExplorerDriver();
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                break;

            default:
                System.setProperty("webdriver.gecko.driver",
                    TestngContext.getContext().getCurrentXmlTest().getParameter("driverpath"));
                driver = new FirefoxDriver();
                break;

        }

        setWait();
        driver.manage().window().maximize();

        return getDriver();
    }

    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        driver = null;
    }


    public static void setWait() {
        long waittime = WAITTIME; //Default

        Set<String> names = TestngContext.getContext().getAttributeNames();
        if (names.contains("webdriverwait"))
            waittime = Long.parseLong(TestngContext.getContext().getCurrentXmlTest().getParameter("webdriverwait"));

        wait = new WebDriverWait(driver, waittime);
    }

    public static WebDriverWait getWait() {
        return wait;
    }



}
