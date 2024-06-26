package libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverActions {
    
    private final WebDriver driver;
    static final int TIMEOUT = 60;
    
    public WebDriverActions() {
        System.setProperty("webdriver.chrome.driver", "D:/Work/EXEs/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void waitForElementToDisappear(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBePresent(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeEnabled(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(driver -> element.isEnabled());
    }

    public void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void openUrl(String url) {
        driver.get(url);
    }
    
    public void click(By locator) {
        try {
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch(Exception ex) {
            ex.printStackTrace();
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        }
    }

    public void type(By locator, String value) {
        try {
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            driver.findElement(locator).sendKeys(value);
        } catch(Exception ex) {
            ex.printStackTrace();
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            driver.findElement(locator).sendKeys(value);
        }
    }
}


