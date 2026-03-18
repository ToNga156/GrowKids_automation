package com.growkids.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By EMAIL_FIELD = By.id("com.growkids:id/email");
    private static final By PASSWORD_FIELD = By.id("com.growkids:id/password");
    private static final By LOGIN_BUTTON = By.id("com.growkids:id/loginButton");
    private static final By ERROR_MESSAGE = By.id("com.growkids:id/errorMessage");

    public LoginPage() {
        super();
    }

    public void enterEmail(String email) {
        sendKeys(EMAIL_FIELD, email);
    }

    public void enterPassword(String password) {
        sendKeys(PASSWORD_FIELD, password);
    }

    public void clickLogin() {
        click(LOGIN_BUTTON);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(LOGIN_BUTTON);
    }
}
