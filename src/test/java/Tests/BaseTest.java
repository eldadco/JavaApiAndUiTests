package Tests;

import Infra.BuisnessProcesses.TestFormProcess;
import Infra.DriverSingleton.DriverSingleton;
import Infra.BuisnessProcesses.LoginProcess;
import Infra.Pages.LoginPage.LoginPage;
import Infra.Pages.TestFormPage.TestFormPage;
import SeleniumInfra.SeleniumInfraStructure;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected final LoginPage loginPage;
    protected final TestFormPage testFormPage;
    protected final TestFormProcess testFormProcess;
    protected final LoginProcess loginProcess;
    private final SeleniumInfraStructure seleniumInfra = DriverSingleton.getInstance().getSeleniumInfra();

    public BaseTest() {
        this.loginProcess = new LoginProcess();
        this.loginPage = new LoginPage();
        this.testFormPage = new TestFormPage();
        this.testFormProcess = new TestFormProcess();
    }

    @BeforeClass
    public void navigateToLoginPage(){
    this.loginPage.navigate();
    }


    @AfterSuite
    public void afterClass(){
        this.seleniumInfra.close();
        this.seleniumInfra.quit();
    }

}
