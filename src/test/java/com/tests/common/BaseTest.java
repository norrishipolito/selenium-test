package com.tests.common;

import com.tests.common.utilities.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    public ExtentTest test;
    public ExtentReports extent = new ExtentReports();

    @BeforeSuite
    public void Setup(){
        driver = WebDriverManager.getDriver();
        ExtentSparkReporter report = new ExtentSparkReporter("target/Report.html");
        extent.attachReporter(report);

    }
}
