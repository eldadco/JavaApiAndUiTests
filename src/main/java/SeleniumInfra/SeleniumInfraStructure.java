package SeleniumInfra;


import io.qameta.allure.Attachment;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SeleniumInfraStructure {
    @Getter
    protected WebDriver driver;
    protected WebDriverWait waitForElems;
    protected JavascriptExecutor js;
    protected ExpectedConditions conditions;

    public SeleniumInfraStructure() {
        String osType = System.getProperty("os.name").toLowerCase();
        String resourceName = "";
        if (osType.contains("win")) {
            resourceName = "winChromeDriver.exe";
        } else if (osType.contains("mac")) {
            resourceName = "macChromeDriver";
        } else if (osType.contains("linux")) {
            resourceName = "linuxChromeDriver";
        }

        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(this.getClass().getClassLoader().getResource(resourceName)).getPath());
        this.driver = new ChromeDriver();
        this.waitForElems = new WebDriverWait(this.driver, 40);
        this.js = (JavascriptExecutor) driver;

    }

    public void getUrl(String url) {
        try {
            this.driver.get(url);
            this.driver.manage().window().maximize();
            System.out.println("Selenium-infra success message:\t The requested site has opened successfully");
        } catch (Exception e) {
            System.out.println("Selenium-infra error : \t" + e.toString());
        }
    }

    public boolean isElementExists(By locator, WebElement fromElement) {
        if (this.findElem(locator, fromElement) != null) {
            System.out.println("The element has found successfully");
            return true;
        } else {
            return false;
        }
    }


    public Boolean urlValidation(String url) {
        try {
            if (this.driver.getCurrentUrl().contains(url)) {
                System.out.println("SeleniumInfra success message : the current url is: " + url);
                return true;
            } else {
                System.out.println("SeleniumInfra failed message : the current url does not contain: " + url);
                System.out.println("The current url is " + this.driver.getCurrentUrl());
                return false;
            }
        } catch (Exception excep) {
            System.out.println("SeleniumInfra exception message : " + excep.toString());
            return false;
        }
    }

    public WebElement findElem(By locator, WebElement fromElement) {

        try {
            if (fromElement != null) {
                return fromElement.findElement(locator);
            } else {
                return this.driver.findElement(locator);
            }

        } catch (InvalidSelectorException excep) {
            System.out.println("Selenium-infra error : \t" + "invalid selector located : " + locator);
        } catch (NoSuchElementException excep) {
            System.out.println("Selenium-infra error : No such Element Exception located : " + locator);

        } catch (ElementNotVisibleException excep) {

            System.out.println("Selenium-infra error : element not visible exception located : " + locator);
        }
        return null;

    }

    public void clearElementField(By locator, WebElement fromElement, WebElement element) {
        try {
            if (element == null) {
                element = this.findElem(locator, fromElement);
            }
            element.clear();
        } catch (Exception exception) {
            System.out.println("seleniumInfra error: " + exception.toString());
        }
    }

    public String getTextFromElement(By locator, WebElement fromElement, WebElement element) {
        try {
            if (element == null) {
                element = this.findElem(locator, fromElement);
            }
            new WebDriverWait(this.driver, 5).until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (Exception exception) {
            System.out.println("seleniumInfra error " + exception.toString());
            return "getTextFromElement function failed";
        }
    }

    public void clickElement(By locator, WebElement fromElement, WebElement element) {
        try {
            if (fromElement == null) {
                element = this.findElem(locator, fromElement);
            }
            element.click();
            System.out.println(String.format("Selenium-infra success message:\tThe element located %s was successfully clicked",locator));
        } catch (ElementNotVisibleException | ElementNotSelectableException exception) {
            System.out.println("Selenium-infra error : \t" + exception.toString());
        }
    }

    public void writeToElement(By locator, WebElement fromElement, WebElement element, String text) {
        try {
            if (element == null) {
                element = this.findElem(locator, fromElement);
            }

            element.sendKeys(text);
            System.out.println(String.format("Selenium-infra success message:\twriting text to the element located",locator));
        } catch (ElementNotVisibleException exception) {
            System.out.println("Selenium-infra error : \t" + exception.toString());
        }
    }

    public void wait(int number) {
        this.driver.manage().timeouts().implicitlyWait(number, TimeUnit.SECONDS);
    }

    public void scrollByOption(String option, WebElement element) {
        switch (option) {
            case "toElement": {
                if (element != null) {
                    this.js.executeScript("arguments[0].scrollIntoView(true)", element);
                }
                break;
            }
            case "untilDown": {
                this.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                break;
            }
        }
    }

    public void refresh() {
        this.driver.navigate().refresh();
    }

    public void closeTabByIndex(int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            this.driver.switchTo().window(tabs.get(index));
            this.driver.close();
            this.driver.switchTo().window(tabs.get(index - 1));
        }
    }

    public void switchToTheNewTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public void close() {

        this.driver.close();
    }

    public void quit() {
        this.driver.quit();
    }
}
