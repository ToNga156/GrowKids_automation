package com.growkids.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By loginTitle = By.xpath("//android.widget.TextView[@text='Sign in']");
    private By emailField = By.xpath("//android.widget.EditText[@text='Your email']");
    private By passwordField = By.xpath("//android.widget.EditText[@text='Your password']");
    private By loginButton = By.xpath("(//android.widget.TextView[@text='Sign in'])[2]");
    private By registerLink = By.xpath("//android.widget.TextView[contains(@text,'Sign up')]");

    private By emailError = By.xpath("//android.widget.TextView[contains(@text,'Email is not valid')]");
    private By passwordError = By.xpath("//android.widget.TextView[contains(@text,'Password must')]");
    private By loginErrorMessage = By.id("android:id/message");
    private By allowNotificationButton = By.id("com.android.permissioncontroller:id/permission_allow_button");

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void clickAllowNotificationButton() {
        click(allowNotificationButton);
    }

    public void goToRegister() {
        click(registerLink);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginTitle);
    }

    public boolean isEmailErrorDisplayed() {
        return isDisplayed(emailError);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError);
    }

    public boolean isLoginErrorMessageDisplayed() {
        return isDisplayed(loginErrorMessage);
    }
}