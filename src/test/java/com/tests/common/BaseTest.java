package com.tests.common;

import com.tests.common.utilities.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    public ExtentTest test;
    public ExtentReports extent = new ExtentReports();

    @BeforeClass
    public void ChromeSetup(){
        driver = WebDriverManager.getChromeDriver();

    }
}
