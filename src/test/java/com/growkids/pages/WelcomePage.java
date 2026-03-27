package com.growkids.pages;

import org.openqa.selenium.By;

public class WelcomePage extends BasePage { 
    private By loginSuccessMessage = By.id("android:id/message");
    private By welcomeTitle = By.xpath("//android.widget.TextView[@text='Hello parents 👋']");
    private By okButton = By.id("android:id/button1");

    public boolean isLoginSuccessMessageDisplayed() {
        return isDisplayed(loginSuccessMessage);
    }

    public void clickOkButton() {
        click(okButton);
    }

    public boolean isWelcomeTitleDisplayed() {
        return isDisplayed(welcomeTitle);
    }
}