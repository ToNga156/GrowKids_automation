package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.pages.HomePage;
import com.growkids.pages.LoginPage;
import com.growkids.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC_LOGIN_001: Verify login successful and redirect to home page")
    public void testLogin() {

        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        loginPage.enterEmail("Datqt@gmail.com");
        loginPage.enterPassword("12345678");
        loginPage.clickLogin();

        loginPage.clickAllowNotificationButton();

        Assert.assertTrue(homePage.isLoginSuccessMessageDisplayed(), "Login success message not displayed");
        homePage.clickOkButton();
        Assert.assertTrue(homePage.isHomeTitleDisplayed(), "Home page not displayed");
    }

    @Test(description = "TC_LOGIN_002: Verify login successful and redirect to welcome page")
    public void testLogin_WelcomePage() {

        LoginPage loginPage = new LoginPage();
        WelcomePage welcomePage = new WelcomePage();

        loginPage.enterEmail("test1774546902981@gmail.com");
        loginPage.enterPassword("12345678");
        loginPage.clickLogin();

        loginPage.clickAllowNotificationButton();

        Assert.assertTrue(welcomePage.isLoginSuccessMessageDisplayed(), "Login success message not displayed");
        welcomePage.clickOkButton();
        Assert.assertTrue(welcomePage.isWelcomeTitleDisplayed(), "Home page not displayed");
    }

    @Test(description = "TC_LOGIN_003: Login with empty fields")
    public void testLogin_EmptyFields() {

        LoginPage loginPage = new LoginPage();

        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isEmailErrorDisplayed());
        Assert.assertTrue(loginPage.isPasswordErrorDisplayed());
    }

    @Test(description = "TC_LOGIN_004: Login with empty email")
    public void testLogin_EmptyEmail() {

        LoginPage loginPage = new LoginPage();

        loginPage.enterPassword("12345678");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isEmailErrorDisplayed());
    }

    @Test(description = "TC_LOGIN_005: Login with empty password")
    public void testLogin_EmptyPassword() {

        LoginPage loginPage = new LoginPage();

        loginPage.enterEmail("test@gmail.com");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isPasswordErrorDisplayed());
    }

    @Test(description = "TC_LOGIN_006: Login with wrong credentials")
    public void testLogin_WrongCredentials() {

        LoginPage loginPage = new LoginPage();

        loginPage.enterEmail("wrong@gmail.com");
        loginPage.enterPassword("wrong123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
    }
}