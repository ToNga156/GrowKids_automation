package com.growkids.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        DriverManager.quitDriver();
        Thread.sleep(3000);
    }
}
