package com.growkids.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for explicit waits.
 */
public class WaitUtils {

    private WaitUtils() {
    }

    public static WebDriverWait getWait(AppiumDriver driver) {
        int timeout = ConfigReader.getIntProperty("explicit.wait", 15);
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WebElement waitForElementVisible(AppiumDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(AppiumDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementInvisible(AppiumDriver driver, By locator) {
        getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementPresent(AppiumDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
