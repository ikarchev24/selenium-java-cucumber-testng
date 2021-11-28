package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        waitForElementToBeClickable(element, 5);
        element.click();
    }

    public void fillData(WebElement element, String keys) {
        waitForElementToBeClickable(element, 10);
        element.click();
        element.clear();
        element.sendKeys(keys);
    }

    public boolean isWebElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isWebElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void waitForVisibilityOfElement(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfElement(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean elementOnPageContainsText(String text) {
        try {
            return isWebElementDisplayed(driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")));
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean childElementContainsText(WebElement parentElement, String text) {
        try {
            return isWebElementDisplayed(parentElement.findElement(By.xpath("//*[contains(text(),'" + text + "')]")));
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void clickElementWithText(String text) {
        driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        int counter = 0;
        while (!(isWebElementDisplayed(element)) && counter < 30) {
            executeScript("window.scrollBy(0,250)");
            Thread.sleep(500);
            counter++;
        }
    }

    public void executeScript(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public void moveToElement(WebElement element) {
        waitForVisibilityOfElement(element, 10);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
