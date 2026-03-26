package com.growkids.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By loginTitle = By.xpath("(//android.widget.TextView[@text='Sign in'])[1]");
    private By emailField = By.xpath("//android.widget.EditText[@text='Your email']");
    private By passwordField = By.xpath("//android.widget.EditText[@text='Your password']");
    private By loginButton = By.xpath("(//android.widget.TextView[@text='Sign in'])[2]");
    private By registerLink = By.xpath("//android.widget.TextView[contains(@text,'Sign up')]");

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void goToRegister() {
        click(registerLink);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginTitle);
    }
}