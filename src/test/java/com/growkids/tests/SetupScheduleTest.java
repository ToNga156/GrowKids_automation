package com.growkids.tests;

import com.growkids.base.BaseTest;
import com.growkids.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import com.growkids.pages.WelcomePage;
import com.growkids.pages.SetupSchedulePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupScheduleTest extends BaseTest {
    @BeforeMethod
    public void setupTest() {
        LoginPage loginPage = new LoginPage();
        WelcomePage welcomePage = new WelcomePage();
        loginPage.enterEmail("Na6@gmail.com");
        loginPage.enterPassword("12345678");
        loginPage.clickLogin();
        loginPage.clickAllowNotificationButton();
        welcomePage.clickOkButton();
        welcomePage.clickStartButton();
    }

    @Test(description = "TC_SETUP_SCHEDULE_001: Verify setup schedule successful")
    public void testSetupSchedule() {
        SetupSchedulePage setupSchedulePage = new SetupSchedulePage();

        for (int i = 0; i < 6; i++) { 
            Assert.assertTrue(
                setupSchedulePage.isQuestionDisplayed(),
                "Question should be displayed"
            );

            setupSchedulePage.answerQuestion(0); 
        }

        Assert.assertTrue(setupSchedulePage.isQuestionDisplayed(), "Question should be displayed");
        setupSchedulePage.answerEndQuestion(0);

        Assert.assertTrue(setupSchedulePage.isSuccessMessageDisplayed(), "Success message should be displayed");
        setupSchedulePage.clickOkButton();
    }
}