package Infra.Pages.TestFormPage;

import org.openqa.selenium.By;

public class TestFormPageLocators {
    public static By greetingMessage = By.id("greetings");
    public static By logoutButton = By.id("logout");
    public static By countryInput = By.id("country");
    public static By addressInput = By.id("address");
    public static By emailInput = By.id("email");
    public static By phoneInput = By.id("phone");
    public static By saveButton = By.id("save");
    //error messages
    public static By countryInputErrorMessage = By.cssSelector("#country + div.invalid-feedback");
    public static By addressInputErrorMessage = By.cssSelector("#address+ div.invalid-feedback");
    public static By emailInputErrorMessage = By.cssSelector("#email + div.invalid-feedback");
    public static By phoneInputErrorMessage = By.cssSelector("#phone + div.invalid-feedback");

    //success save meesage
    public static By savedMessage = By.id("saved");
}
