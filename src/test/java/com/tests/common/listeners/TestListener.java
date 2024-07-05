package com.tests.common.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tests.common.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    static {
        extent = new ExtentReports();
        ExtentSparkReporter report = new ExtentSparkReporter("target/Report.html");
        extent.attachReporter(report);
    }
    @Override
    public void onStart(ITestContext context) {
        System.out.println(context.getSuite().getName()+" is starting...");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending...");
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting Test: " + result.getMethod().getMethodName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Passed", ExtentColor.GREEN));
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Failed due to below issues:", ExtentColor.RED));
        test.get().fail(result.getThrowable());
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Skipped", ExtentColor.ORANGE));
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }
    public static ExtentTest getTest() {
        return test.get();
    }
}
