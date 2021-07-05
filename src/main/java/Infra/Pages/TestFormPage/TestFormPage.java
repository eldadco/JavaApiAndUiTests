package Infra.Pages.TestFormPage;

import Infra.Pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TestFormPage extends BasePage {
    public TestFormPage() {
    }

    public String getGreetingMessage() {
        return this.seleniumInfra.getTextFromElement(TestFormPageLocators.greetingMessage, null, null);
    }

    public boolean isOpened() {
        return this.seleniumInfra.findElem(TestFormPageLocators.countryInput, null).isDisplayed();
    }

    public void setCountry(String country) {
        Select countrySelect = new Select(this.seleniumInfra.findElem(TestFormPageLocators.countryInput, null));
        countrySelect.selectByVisibleText(country);
    }


    public void setAddress(String address) {
        this.seleniumInfra.writeToElement(TestFormPageLocators.addressInput, null, null, address);

    }

    public void clearAddressField() {
        this.seleniumInfra.clearElementField(TestFormPageLocators.addressInput, null, null);
    }

    public void setEmail(String email) {
        this.seleniumInfra.writeToElement(TestFormPageLocators.emailInput, null, null, email);
    }

    public void clearEmailField() {
        this.seleniumInfra.clearElementField(TestFormPageLocators.emailInput, null, null);
    }


    public void setPhone(String phone) {
        this.seleniumInfra.writeToElement(TestFormPageLocators.phoneInput, null, null, phone);
    }

    public void clearPhoneField() {
        this.seleniumInfra.clearElementField(TestFormPageLocators.phoneInput, null, null);
    }

    public Boolean isCountryErrorMessageDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.countryInputErrorMessage, null).isDisplayed();
    }

    public Boolean isAddressErrorMessageDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.addressInputErrorMessage, null).isDisplayed();
    }

    public Boolean isEmailErrorMessageDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.emailInputErrorMessage, null).isDisplayed();
    }

    public Boolean isPhoneErrorMessageDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.phoneInputErrorMessage, null).isDisplayed();
    }


    public void pressLogoutButton() {
        WebElement logoutButton = this.seleniumInfra.findElem(TestFormPageLocators.logoutButton, null);
        if (logoutButton.isDisplayed()) {
            this.seleniumInfra.clickElement(TestFormPageLocators.logoutButton, null, null);
        }
    }

    public void pressSaveButton() {

        this.seleniumInfra.scrollByOption("untilDown",null);
        this.seleniumInfra.clickElement(TestFormPageLocators.saveButton, null, null);
    }

    public boolean isSaveButtonDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.saveButton, null).isDisplayed();
    }

    public boolean isSavedMessageDisplayed() {
        return this.seleniumInfra.findElem(TestFormPageLocators.savedMessage, null).isDisplayed();
    }


}
