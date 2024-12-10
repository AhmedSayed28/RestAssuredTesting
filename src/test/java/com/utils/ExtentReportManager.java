package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;       // Report UI
    public ExtentReports extent;                   // Report Info
    public ExtentTest test;                       //  Create TCs in report and update status

    @Override
    public void onStart(ITestContext context) {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/myReport.html");

        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name","MyComputer");
        extent.setSystemInfo("Env","QA");
        extent.setSystemInfo("Tester Name","Ahmed Elsayed");
        extent.setSystemInfo("OS","WIN_11");
        extent.setSystemInfo("Browser Name","Chrome");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS,"Test Case Passed is: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL,"Test Case Failed is: "+result.getName());
        test.log(Status.FAIL,"Cause: "+result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP,"Test Case Skipped is: "+result.getName());
        test.log(Status.SKIP,"Cause: "+result.getSkipCausedBy());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
