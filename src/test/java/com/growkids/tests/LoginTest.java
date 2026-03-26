package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.pages.HomePage;
import com.growkids.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {

        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        loginPage.enterEmail("test@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        // Assert.assertTrue(homePage.isHomeDisplayed(), "Login failed!");
    }
}