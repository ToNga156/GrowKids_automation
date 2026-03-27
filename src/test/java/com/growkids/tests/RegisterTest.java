package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.pages.LoginPage;
import com.growkids.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(description = "TC_REGISTER_001: Verify successful register")
    public void testRegister() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register("Test User", email, "12345678");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User should be redirected to Login page after register");
    }

    @Test(description = "TC_REGISTER_002: Register with all fields empty")
    public void testRegister_EmptyAllFields() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        registerPage.register(null, null, null);

        Assert.assertTrue(registerPage.isNameErrorDisplayed(), "Name error not displayed");
        Assert.assertTrue(registerPage.isEmailErrorDisplayed(), "Email error not displayed");
        Assert.assertTrue(registerPage.isPasswordErrorDisplayed(), "Password error not displayed");
    }

    @Test(description = "TC_REGISTER_003: Register with empty name")
    public void testRegister_EmptyName() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register(null, email, "12345678");

        Assert.assertTrue(registerPage.isNameErrorDisplayed(), "Name error not displayed");
    }

    @Test(description = "TC_REGISTER_004: Register with empty email")
    public void testRegister_EmptyEmail() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        registerPage.register("Test User", null, "12345678");

        Assert.assertTrue(registerPage.isEmailErrorDisplayed(), "Email error not displayed");
    }

    @Test(description = "TC_REGISTER_005: Register with empty password")
    public void testRegister_EmptyPassword() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register("Test User", email, null);

        Assert.assertTrue(registerPage.isPasswordErrorDisplayed(), "Password error not displayed");
    }

    @Test(description = "TC_REGISTER_006: Register with invalid email")
    public void testRegister_InvalidEmail() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        loginPage.goToRegister();

        registerPage.register("Test User", "invalid-email", "12345678");

        Assert.assertTrue(registerPage.isEmailErrorDisplayed(), "Invalid email error not displayed");
    }
}