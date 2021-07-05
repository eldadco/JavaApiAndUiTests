package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestFormTests extends BaseTest {
    public TestFormTests() {
    }

    @BeforeMethod
    public void beforeMethod() {
        if (this.testFormPage.isSavedMessageDisplayed()) {
            this.testFormPage.pressLogoutButton();
        }
        if (!this.testFormPage.isOpened()) {
            this.loginProcess.performLoginProcess("Administration", "12345");
        }
    }

    @Test(description = "Insert valid credentials and verify the test form is saved successfully")
    public void positiveTestFormProcess() {
        this.testFormProcess.performTestFormProcess("Israel", "Etel", "eldad@gmail.com", "0505050502");
        boolean conditionResults = (!this.testFormPage.isSaveButtonDisplayed())
                && this.testFormPage.isSavedMessageDisplayed();

        Assert.assertTrue(conditionResults, "Verify the 'saved' message is displayed and the save button is no longer displayed");

    }

    @Test(description = "Verify the test form did not save successfully when credential were not inserted")
    public void negativeTestFormProcessEmptyCredentials() {
        this.testFormProcess.performTestFormProcess("", "", "", "");
        boolean conditionResults = this.testFormPage.isCountryErrorMessageDisplayed()
                && this.testFormPage.isAddressErrorMessageDisplayed()
                && this.testFormPage.isEmailErrorMessageDisplayed()
                && this.testFormPage.isPhoneErrorMessageDisplayed()
                && this.testFormPage.isSaveButtonDisplayed()
                && (!this.testFormPage.isSavedMessageDisplayed());

        Assert.assertTrue(conditionResults, "Verify the country,address, email, phone error messages, and save button are displayed, and that the 'saved' message is not displayed");

    }

    @Test(description = "Verify the test form did not saved successfully when inserting wrong email format")
    public void negativeTestFormProcessWrongEmailFormat() {
        this.testFormProcess.performTestFormProcess("Israel", "Etel", "eldadgmail.com", "0505050502");
        boolean conditionResults = this.testFormPage.isEmailErrorMessageDisplayed()
                && this.testFormPage.isSaveButtonDisplayed()
                && (!this.testFormPage.isSavedMessageDisplayed());
        Assert.assertTrue(conditionResults, "Verify the email error message and the save button are displayed, and that the 'saved' message does not displayed");
    }

    @Test(description = "Verify the data does not saved when inserting one digit to the phone number input")
    public void negativeTestFormProcessWrongPhoneNumberFormat() {
        this.testFormProcess.performTestFormProcess("Israel", "Etel", "eldad@gmail.com", "0");
        boolean isPhoneErrorMessageDisplayed = this.testFormPage.isPhoneErrorMessageDisplayed();
        boolean isSaveButtonDisplayed = this.testFormPage.isSaveButtonDisplayed();
        boolean isSavedMessageDisplayed = this.testFormPage.isSavedMessageDisplayed();
        Assert.assertTrue(isPhoneErrorMessageDisplayed && isSaveButtonDisplayed && (!isSavedMessageDisplayed), "Verify the phone error message and the save button are displayed, Verify the 'saved' message does not displayed");
    }

    @AfterMethod
    public void afterMethod() {
        this.testFormProcess.clearAllFields();
    }

}
