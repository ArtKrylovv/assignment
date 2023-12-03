package com.krylov.assignment.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebTestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger(WebTestListener.class);

    public void onTestStart(ITestResult result){
        LOGGER.info("Staring test: "+result.getName());
    }
    public void onTestSuccess(ITestResult result){
        LOGGER.info("Test passed: "+result.getName());
    }

    public void onTestFailure(ITestResult result){
        LOGGER.info("Test failed: "+result.getName());
    }

}
