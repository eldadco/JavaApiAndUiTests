package Tests;

import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {


    public LoginTests() {

    }

    @BeforeMethod
    public void beforeMethod(){
        this.testFormPage.pressLogoutButton();
        this.loginProcess.clearUserNameAndPasswordFields();

}
    @Test(description = "Verify login succeeded when inserting correct credentials")
    public void positiveLoginTest(){
        String userNameInput = "Administration";
        this.loginProcess.performLoginProcess(userNameInput,"12345");
        Assert.assertTrue(this.testFormPage.getGreetingMessage().contains(userNameInput)
                ,String.format("Verify the login process succeeded and the user name: %s displayed in the greeting message", userNameInput));
        Assert.assertTrue(this.testFormPage.isOpened(),"Verify the test form page is opened");
    }

    @Test(description = "Verify the login failed when user name is empty")
    public void userNameEmptyNegativeLoginTest(){
        this.loginProcess.performLoginProcess("","12345");
        Assert.assertTrue(this.loginPage.getUserNameErrorMessage().contains("Please provide your full name"),"Verify user name error displayed");
        Assert.assertFalse(this.testFormPage.isOpened(), "Verify the test form page is not opened");
    }
    @Test(description = "Verify the login failed when password is empty")
    public void passwordEmptyNegativeLoginTest(){
        this.loginProcess.performLoginProcess("Administration","");
        Assert.assertTrue(this.loginPage.getPasswordErrorMessage().contains("Password is invalid"),"Verify password error displayed");
        Assert.assertFalse(this.testFormPage.isOpened(), "Verify the test form page is not opened");

    }

    @Test(description = "Verify login failed when not inserting user name and password ")
    public void userNameAndPasswordEmptiesNegativeLoginTest() {
        this.loginProcess.performLoginProcess("", "");
        Assert.assertTrue(this.loginPage.getUserNameErrorMessage().contains("Please provide your full name"), "Verify user name error displayed");
        Assert.assertTrue(this.loginPage.getPasswordErrorMessage().contains("Password is invalid"), "Verify password error displayed");

    }
    @Test(description = "Verify login failed when the password is incorrect")
    public void incorrectPasswordNegativeLoginTest(){
        this.loginProcess.performLoginProcess("Administration","123");
        Assert.assertTrue(this.loginPage.getPasswordErrorMessage().contains("Password is invalid"),"Verify password error displayed");
        Assert.assertFalse(this.testFormPage.isOpened(), "Verify the test form page is not opened");

    }
}
