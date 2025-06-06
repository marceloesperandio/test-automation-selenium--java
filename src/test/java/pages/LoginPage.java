package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement inputUsername;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(css = "button[type='submit']")
    private WebElement btnLogin;

    @FindBy(css = ".oxd-topbar-header-breadcrumb")
    private WebElement dashboardTitle;

    @FindBy(css = "p.oxd-text.oxd-text--p.oxd-alert-content-text")
    private WebElement mensagemErroLogin;

    /**
     * Inputs
     */
    public void fillUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(inputUsername));
        element.clear();
        element.sendKeys(username);
    }

    public void fillPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(inputPassword));
        element.clear();
        element.sendKeys(password);
    }

    /**
     * Actions
     */
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    /**
     * Assertions
     */
    public boolean isOnDashboard() {
        return wait.until(ExpectedConditions.visibilityOf(dashboardTitle)).isDisplayed();
    }

    public boolean isInvalidCredentialsMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(mensagemErroLogin));
            return mensagemErroLogin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForErrorMessage() {
        wait.until(ExpectedConditions.textToBePresentInElement(mensagemErroLogin, "Invalid credentials"));
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(mensagemErroLogin)).getText();
    }
}