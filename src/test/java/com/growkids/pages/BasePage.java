package com.growkids.pages;

import com.growkids.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.growkids.utils.WaitUtils;

/**
 * Base page class - common functionality for all page objects.
 */
public abstract class BasePage {

    protected AppiumDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected WebElement waitAndFind(By locator) {
        return WaitUtils.waitForElementVisible(driver, locator);
    }

    protected WebElement waitAndClick(By locator) {
        return WaitUtils.waitForElementClickable(driver, locator);
    }

    protected void click(By locator) {
        waitAndClick(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        waitAndFind(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitAndFind(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitAndFind(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
