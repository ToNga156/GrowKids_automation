package com.growkids.flows;

import com.growkids.pages.SetupSchedulePage;
import com.growkids.pages.WelcomePage;

public class SetupScheduleFlow {

    public void completeSetupSchedule() {

        WelcomePage welcomePage = new WelcomePage();
        SetupSchedulePage page = new SetupSchedulePage();

        welcomePage.clickStartButton();

        for (int i = 0; i < 6; i++) {
            page.answerQuestion(0);
        }

        page.answerEndQuestion(0);

        page.clickOkButton();
    }
}