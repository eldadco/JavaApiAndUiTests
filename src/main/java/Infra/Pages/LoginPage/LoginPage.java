package Infra.Pages.LoginPage;

import Infra.Pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage() {
    }

    public void navigate() {
        this.seleniumInfra.getUrl("https://example.testproject.io/web/");
        this.seleniumInfra.urlValidation("https://example.testproject.io/web/");

    }

    @Step("Insert user name")
    public void setUserName(String userNameInput) {
        WebElement userNameField = this.seleniumInfra.findElem(LoginPageLocators.userNameInput, null);
        this.seleniumInfra.writeToElement(null, null, userNameField, userNameInput);
    }

    @Step("Insert password")
    public void setPassword(String passwordInput) {
        WebElement passwordField = this.seleniumInfra.findElem(LoginPageLocators.passwordInput, null);
        this.seleniumInfra.writeToElement(null, null, passwordField, passwordInput);
    }

    public void clearUserNameField(){
        this.seleniumInfra.clearElementField(LoginPageLocators.userNameInput,null,null);
    }

    public void clearPasswordField(){
        this.seleniumInfra.clearElementField(LoginPageLocators.passwordInput,null,null);
    }

    @Step("Press on login button")
    public void pressLoginButton() {
        this.seleniumInfra.clickElement(LoginPageLocators.loginButton, null, null);
    }

    public String getUserNameErrorMessage() {
        return this.seleniumInfra.getTextFromElement(LoginPageLocators.userNameErrorMessage, null, null);
    }

    public String getPasswordErrorMessage() {
        return this.seleniumInfra.getTextFromElement(LoginPageLocators.passwordErrorMessage, null, null);
    }


}
