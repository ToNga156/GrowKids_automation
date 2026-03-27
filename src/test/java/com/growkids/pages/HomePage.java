package com.growkids.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By loginSuccessMessage = By.id("android:id/message");
    private By okButton = By.id("android:id/button1");
    private By homeTitle = By.xpath("//android.widget.TextView[@text='Bilingual Learning with your child']");

    public boolean isLoginSuccessMessageDisplayed() {
        return isDisplayed(loginSuccessMessage);
    }

    public void clickOkButton() {
        click(okButton);
    }

    public boolean isHomeTitleDisplayed() {
        return isDisplayed(homeTitle);
    }
}