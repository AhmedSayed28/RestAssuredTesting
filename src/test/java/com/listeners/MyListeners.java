package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

    public void onStart(ITestContext context) {
        System.out.println("Started...");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Ahmed the test is running");
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Ahmed the test is Successssssss!!!!!!!!");
    }
}
