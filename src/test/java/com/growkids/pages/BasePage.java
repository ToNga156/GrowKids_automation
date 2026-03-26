package com.growkids.pages;

import com.growkids.base.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class BasePage {

    protected AppiumDriver driver;
    private static final long DEFAULT_WAIT_SECONDS = 20;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected void click(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
            .until(ExpectedConditions.elementToBeClickable(locator))
            .click();
    }

    protected void sendKeys(By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
            .until(ExpectedConditions.visibilityOfElementLocated(locator))
            .sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .isDisplayed();
    }
}