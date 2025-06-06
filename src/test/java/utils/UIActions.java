package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class UIActions {

    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    public UIActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void pressEscape() {
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void pressTab() {
        actions.sendKeys(Keys.TAB).perform();
    }

    public void uploadFile(WebElement inputFile, String filePath) {
        inputFile.sendKeys(filePath);
    }

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clearAndType(WebElement element, String text) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }
}