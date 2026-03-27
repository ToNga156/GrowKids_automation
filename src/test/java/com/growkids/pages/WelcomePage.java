package com.growkids.pages;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;

public class WelcomePage extends BasePage { 
    private By loginSuccessMessage = By.id("android:id/message");
    private By welcomeTitle = By.xpath("//android.widget.TextView[@text='Hello parents 👋']");
    private By okButton = By.id("android:id/button1");
    private By startButton = AppiumBy.accessibilityId("Start");

    public boolean isLoginSuccessMessageDisplayed() {
        return isDisplayed(loginSuccessMessage);
    }

    public void clickOkButton() {
        click(okButton);
    }

    public void clickStartButton() {
        click(startButton);
    }

    public boolean isWelcomeTitleDisplayed() {
        return isDisplayed(welcomeTitle);
    }
}