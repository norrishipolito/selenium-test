package com.tests.common.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentTest node;
    private static final Logger logger = LogManager.getLogger(TestListener.class);;
    static{
        extent = new ExtentReports();
        ExtentSparkReporter report = new ExtentSparkReporter("target/reports/report.html");
        extent.attachReporter(report);
    }
    @Override
    public void onStart(ITestContext context) {
        String suiteName = context.getSuite().getName();
        String testName = context.getCurrentXmlTest().getName();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        logger.info(String.format("%s is starting...", suiteName));
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite is ending...");
        extent.flush();
        test.get().info("Test is Finished");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info(String.format("Starting Test: %s", testName));
        node = test.get().createNode(testName);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        node.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Passed", ExtentColor.GREEN));
        logger.info(String.format("Test Passed: %s", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        node.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Failed due to below issues:", ExtentColor.RED));
        node.fail(result.getThrowable());
        logger.error(String.format("Test Failed: %s", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        node.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Skipped", ExtentColor.ORANGE));
        logger.warn(String.format("Test Skipped: %s", result.getMethod().getMethodName()));
    }
    public static ExtentTest getTest() {
        return test.get();
    }
    public static ExtentTest getNode(){
        return node;
    }
    public static void removeCurrentTest(){
        test.remove();
    }
    public static  ExtentTest newTest(String testName){
        removeCurrentTest();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

}
