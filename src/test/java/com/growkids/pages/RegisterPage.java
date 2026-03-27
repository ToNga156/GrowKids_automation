package com.growkids.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private By nameField = By.xpath("//android.widget.EditText[@text='Your name']");
    private By emailField = By.xpath("//android.widget.EditText[@text='Your email']");
    private By passwordField = By.xpath("//android.widget.EditText[@text='Your password']");
    private By registerButton = By.xpath("(//android.widget.TextView[@text='Sign up'])[2]");

    private By nameError = By.xpath("//android.widget.TextView[contains(@text,'Name is required')]");
    private By emailError = By.xpath("//android.widget.TextView[contains(@text,'Email is not valid')]");
    private By passwordError = By.xpath("//android.widget.TextView[contains(@text,'Password must')]");

    public void enterName(String name) {
        sendKeys(nameField, name);
    }

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public void register(String name, String email, String password) {
        if (name != null) enterName(name);
        if (email != null) enterEmail(email);
        if (password != null) enterPassword(password);
        clickRegister();
    }

    public boolean isNameErrorDisplayed() {
        return isDisplayed(nameError);
    }

    public boolean isEmailErrorDisplayed() {
        return isDisplayed(emailError);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError);
    }
}