package com.growkids.pages;

import org.openqa.selenium.By;
import java.util.List;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

public class GoldenTimeConfirmationPage extends BasePage {
    private By timeSlots = By.xpath("//android.widget.TextView[starts-with(@text,'⏰')]");

    private By toggles = By.xpath(
        "//android.widget.TextView[starts-with(@text,'⏰')]" +
        "/following-sibling::android.view.ViewGroup[1]"
    );

    private By confirmButton = AppiumBy.accessibilityId("Confirm");

    private By successMessage = By.id("android:id/message");

    private By okButton = By.id("android:id/button1");

    private By completedButton = AppiumBy.accessibilityId("Completed");

    public List<WebElement> getAllTimeSlots() {
        return findElements(timeSlots);
    }

    public List<WebElement> getAllToggles() {
        return findElements(toggles);
    }

    public void selectAllSlots() {
        List<WebElement> toggleList = getAllToggles();

        for (WebElement toggle : toggleList) {
            toggle.click();
        }
    }

    public void selectFirstN(int n) {
        List<WebElement> toggleList = getAllToggles();

        for (int i = 0; i < Math.min(n, toggleList.size()); i++) {
            toggleList.get(i).click();
        }
    }

    public void selectSlotByIndex(int index) {
        getAllToggles().get(index).click();
    }

    public void clickConfirm() {
        click(confirmButton);
    }

    public void clickOkButton() {
        click(okButton);
    }

    public void clickCompletedButton() {
        click(completedButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public boolean isPageDisplayed() {
        return getAllTimeSlots().size() > 0;
    }
}