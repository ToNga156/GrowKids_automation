package com.growkids.pages;

import org.openqa.selenium.By;

/**
 * Page Object for Home screen after login.
 * Update locators to match your app's UI.
 */
public class HomePage extends BasePage {

    // Locators - customize based on your app
    private static final By WELCOME_TEXT = By.id("com.growkids:id/welcomeText");
    private static final By LOGOUT_BUTTON = By.id("com.growkids:id/logoutButton");
    private static final By HOME_TITLE = By.id("com.growkids:id/homeTitle");

    public HomePage() {
        super();
    }

    public String getWelcomeText() {
        return getText(WELCOME_TEXT);
    }

    public boolean isHomePageDisplayed() {
        return isDisplayed(HOME_TITLE) || isDisplayed(WELCOME_TEXT);
    }

    public void clickLogout() {
        click(LOGOUT_BUTTON);
    }

    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(LOGOUT_BUTTON);
    }
}
