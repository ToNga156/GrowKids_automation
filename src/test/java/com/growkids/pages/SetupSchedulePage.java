package com.growkids.pages;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SetupSchedulePage extends BasePage {

    private By questionText = By.xpath("//android.widget.TextView");

    private By answerOptions = By.xpath("//android.view.ViewGroup[@clickable='true']");

    private By continueButton = AppiumBy.accessibilityId("Continue");

    private By viewSuggestionsButton = AppiumBy.accessibilityId("View suggestions");

    private By successMessage = By.xpath("//android.widget.TextView[contains(@text,'schedule has been saved')]");

    private By okButton = By.id("android:id/button1");


    public String getQuestion() {
        return getText(questionText);
    }

    public void selectAnswerByIndex(int index) {
        List<WebElement> options = findElements(answerOptions);
        options.get(index).click();
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickViewSuggestions() {
        click(viewSuggestionsButton);
    }

    public void clickOkButton() {
        click(okButton);
    }

    public void answerQuestion(int index) {
        selectAnswerByIndex(index);
        clickContinue();
    }

    public void answerEndQuestion(int index) {
        selectAnswerByIndex(index);
        clickViewSuggestions();
    }

    public boolean isQuestionDisplayed() {
        return isDisplayed(questionText);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}