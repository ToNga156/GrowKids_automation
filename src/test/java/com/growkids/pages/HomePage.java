package com.growkids.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By homeTitle = By.xpath("//android.widget.TextView[contains(@text,'Home')]");

    public boolean isHomeDisplayed() {
        return isDisplayed(homeTitle);
    }
}