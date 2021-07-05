package Infra.Pages.LoginPage;

import org.openqa.selenium.By;

public class LoginPageLocators {

    public static By userNameInput = By.id("name");
    public static By passwordInput = By.id("password");
    public static By loginButton = By.id("login");
    //Error messages

    public static By userNameErrorMessage = By.cssSelector("input#name + div[class=\"invalid-feedback\"]");
    public static By passwordErrorMessage = By.cssSelector("input#password + small + div[class=\"invalid-feedback\"]");

}
