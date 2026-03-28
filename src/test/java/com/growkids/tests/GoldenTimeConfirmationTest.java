package com.growkids.tests;

import com.growkids.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.growkids.base.BaseTest;
import com.growkids.pages.GoldenTimeConfirmationPage;
import com.growkids.flows.LoginFlow;
import com.growkids.flows.SetupScheduleFlow;
import org.testng.annotations.BeforeMethod;


public class GoldenTimeConfirmationTest extends BaseTest {
    @BeforeMethod
    public void setupTest() {
        LoginFlow loginFlow = new LoginFlow();
        loginFlow.loginAndGoToWelcome();
        SetupScheduleFlow setupScheduleFlow = new SetupScheduleFlow();
        setupScheduleFlow.completeSetupSchedule();
    }

    @Test(description = "TC_GOLDEN_TIME_CONFIRMATION_001: Verify golden time confirmation successful with all slots")
    public void GoldenTimeConfirmation_SelectAll() {

        GoldenTimeConfirmationPage goldenTimeConfirmationPage = new GoldenTimeConfirmationPage();
        HomePage homePage = new HomePage();

        goldenTimeConfirmationPage.selectAllSlots();

        goldenTimeConfirmationPage.clickConfirm();

        Assert.assertTrue(goldenTimeConfirmationPage.isSuccessMessageDisplayed(), "Success message not displayed");
        goldenTimeConfirmationPage.clickOkButton();

        goldenTimeConfirmationPage.clickCompletedButton();

        Assert.assertTrue(homePage.isHomeTitleDisplayed(), "Home page not displayed");
    }
}