package Infra.Pages;

import Infra.DriverSingleton.DriverSingleton;
import SeleniumInfra.SeleniumInfraStructure;
import io.qameta.allure.Attachment;

public class BasePage {
    protected SeleniumInfraStructure seleniumInfra = DriverSingleton.getInstance().getSeleniumInfra();

}
