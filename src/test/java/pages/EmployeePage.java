package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement menuPim;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private  WebElement menuAddEmployee;

    @FindBy(xpath = "//i[contains(@class, 'bi-plus')]")
    private WebElement addPhotoButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement inputUploadFile;

    @FindBy(name = "firstName")
    private WebElement fieldFirstName;

    @FindBy(name = "middleName")
    private WebElement fieldMiddleName;

    @FindBy(name = "lastName")
    private WebElement fieldLastName;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement fieldEmployeeId;

    @FindBy(xpath = "//span[contains(@class,'oxd-switch-input')]")
    private WebElement toggleSwitchCreateLoginDetails;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement inputUsername;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement inputPassword;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement inputConfirmPassword;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement buttonSave;

    public void clickMenuPim() {
        wait.until(ExpectedConditions.elementToBeClickable(menuPim)).click();
    }

    public void clickMenuAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(menuAddEmployee)).click();
    }

    public void clickUploadIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(addPhotoButton)).click();
    }

    public void uploadPhoto(String relativePath) {
        String absolutePath = Paths.get("src/test/resources/images", relativePath).toAbsolutePath().toString();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        inputUploadFile.sendKeys(absolutePath);
    }

    //ASSERT
    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[contains(text(), 'Successfully')]")
    private WebElement toastMensageSucess;

    public void fillFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(fieldFirstName)).sendKeys(firstName);
    }

    public void fillMiddleName(String middleName) {
        wait.until(ExpectedConditions.visibilityOf(fieldMiddleName)).sendKeys(middleName);
    }

    public void fillLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(fieldLastName)).sendKeys(lastName);
    }

    public void fillEmployeeId(String employeeId) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fieldEmployeeId));
        // Etapa 1: clicar no campo
        element.click();

        element.clear(); // Tenta limpar normalmente
//        element.sendKeys(CONTROL + "a"); // Seleciona tudo
//        element.sendKeys(DELETE);        // Apaga
//        element.sendKeys(employeeId);         // Insere novo valor
    }

    public void clickToggleSwitchCreateLoginDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(toggleSwitchCreateLoginDetails)).click();
    }

    public void fillUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(inputUsername)).clear();
        inputUsername.sendKeys(username);
    }

    public void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(inputPassword)).clear();
        inputPassword.sendKeys(password);
    }

    public void fillConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(inputConfirmPassword)).clear();
        inputConfirmPassword.sendKeys(password);
    }

    public void clickBtnSave() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
    }

    public void verifyMensageSucess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOf(toastMensageSucess));

        String mensagem = toast.getText().trim();
        assertTrue(mensagem.contains("Successfully"), "Mensagem esperada não foi exibida: " + mensagem);
    }
}