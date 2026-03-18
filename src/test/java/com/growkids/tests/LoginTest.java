package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.pages.HomePage;
import com.growkids.pages.LoginPage;
import com.growkids.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Login test cases.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        String email = ConfigReader.getProperty("test.email", "H@gmail.com");
        String password = ConfigReader.getProperty("test.password", "12345678");

        loginPage.login(email, password);

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Homepage should be displayed after login");
    }

    @Test(description = "Verify login page elements are displayed")
    public void testLoginPageDisplayed() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
    }

    @Test(description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("invaliduser", "wrongpassword");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login page should still be visible");
    }
}
