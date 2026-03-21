package com.growkids.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    private By title = By.xpath("//android.widget.TextView[@text='Sign in']");
    private By emailField = By.xpath("//android.widget.EditText[@text='Your email']");
    private By passwordField = By.xpath("//android.widget.EditText[@text='Your password']");
    private By loginBtn = By.xpath("//android.widget.Button[@text='Sign in']");

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
        return driver.getPageSource().contains("Home");
    }

    public boolean isErrorDisplayed(String errorMsg) {
        return driver.getPageSource().contains(errorMsg);
    }
}