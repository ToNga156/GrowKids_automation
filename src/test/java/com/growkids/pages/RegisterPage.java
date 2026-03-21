package com.growkids.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {
    private AppiumDriver driver;

    public RegisterPage(AppiumDriver driver) {
        this.driver = driver;
    }

    private By usernameField = By.xpath("(//android.widget.EditText)[1]");
    private By emailField = By.xpath("(//android.widget.EditText)[2]");
    private By passwordField = By.xpath("(//android.widget.EditText)[3]");
    private By signupBtn = By.xpath("//android.widget.Button[@text='Sign up']");

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignup() {
        driver.findElement(signupBtn).click();
    }

    public boolean isRegisterSuccess() {
        return isDisplayed(By.xpath("//android.widget.TextView[@text='REGISTER SUCCESFULLY']"));
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(By.xpath("//android.widget.Button[@text='Sign in']"));
    }
}