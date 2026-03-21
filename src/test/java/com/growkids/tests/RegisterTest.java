package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.base.DriverManager;
import com.growkids.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void setupPage() {
        // BaseTest đã init driver ở @BeforeMethod, lấy driver theo ThreadLocal để tránh null
        registerPage = new RegisterPage(DriverManager.getDriver());
    }

    @Test (description = "Verify successful registration with valid credentials")
    public void testRegisterSuccess() {
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("123456");

        registerPage.clickSignup();

        Assert.assertTrue(registerPage.isRegisterSuccess(), "Register success message should be displayed");
        Assert.assertTrue(registerPage.isLoginPageDisplayed(), "Home page should be displayed");
        System.out.println("Register success test executed");
    }

    @Test (description = "Verify registration fails with empty username")
    public void testRegister_EmptyUsername() {
        registerPage.enterUsername("");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("123456");

        registerPage.clickSignup();

        // TODO: verify error message
        System.out.println("Empty username test executed");
    }

    @Test (description = "Verify registration fails with empty email")
    public void testRegister_EmptyEmail() {
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("");
        registerPage.enterPassword("123456");

        registerPage.clickSignup();

        // TODO: verify error message
        System.out.println("Empty email test executed");
    }

    @Test (description = "Verify registration fails with empty password")
    public void testRegister_EmptyPassword() {
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("");

        registerPage.clickSignup();

        // TODO: verify error message
        System.out.println("Empty password test executed");
    }

    @Test (description = "Verify registration fails with invalid email format")
    public void testRegister_InvalidEmail() {
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("invalid-email");
        registerPage.enterPassword("123456");

        registerPage.clickSignup();

        // TODO: verify error message
        System.out.println("Invalid email format test executed");
    }
}