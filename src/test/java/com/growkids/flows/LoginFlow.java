package com.growkids.flows;

import com.growkids.pages.LoginPage;
import com.growkids.pages.WelcomePage;

public class LoginFlow {

    public void loginAndGoToWelcome() {

        LoginPage loginPage = new LoginPage();
        WelcomePage welcomePage = new WelcomePage();

        loginPage.enterEmail("Na22@gmail.com");
        loginPage.enterPassword("12345678");
        loginPage.clickLogin();

        loginPage.clickAllowNotificationButton();
        welcomePage.clickOkButton();
    }
}