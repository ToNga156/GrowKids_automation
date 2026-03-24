package com.growkids.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage extends BasePage {

    private AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    private By title = By.xpath("//android.widget.TextView[@text='Sign in']");
    private By emailField = By.xpath("//android.widget.EditText[@hint='Your email']");
    private By passwordField = By.xpath("//android.widget.EditText[@hint='Your password']");
    private By loginBtn = AppiumBy.accessibilityId("Sign in");
    private By homeTitle = By.xpath("//android.widget.TextView[@text='Bilingual Learning  with your child']");

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public boolean isLoginSuccess() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(homeTitle))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed(String errorMsg) {
        return driver.getPageSource().contains(errorMsg);
    }
}