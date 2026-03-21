package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.base.DriverManager;
import com.growkids.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        // BaseTest đã init driver ở @BeforeMethod, lấy driver theo ThreadLocal để tránh null
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Test (description = "Verify successful login with valid credentials")
    public void testLoginSuccess() {
        loginPage.login("test@gmail.com", "123456");

        Assert.assertTrue(loginPage.isLoginSuccess(), "User should login successfully and navigate to Home");
    }

    @Test (description = "Verify login fails with empty email")
    public void testLogin_EmptyEmail() {
        loginPage.enterEmail("");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed("Email is required"), "Error message should be displayed for empty email");
    }

    @Test (description = "Verify login fails with empty password")
    public void testLogin_EmptyPassword() {
        loginPage.enterEmail("test@gmail.com");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed("Password is required"), "Error message should be displayed for empty password");
    }

    @Test (description = "Verify login fails with invalid email format")
    public void testLogin_InvalidEmail() {
        loginPage.enterEmail("invalid-email");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed("Invalid email"), "Error message should be displayed for invalid email");
    }
}