package steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.EmployeePage;

import static java.lang.System.out;
import static utils.DriverFactory.getDriver;
import static utils.ScreenshotUtil.takeScreenshot;

public class EmployeeSteps {

    private WebDriver driver;
    private EmployeePage employeePage;

    @Given("o usuário está logado na plataforma OrangeHRM")
    public void oUsuárioEstáLogadoNaPlataformaOrangeHRM() {
        driver = getDriver();
        out.println("Usuário logado automaticamente via Hook.");
        takeScreenshot(driver, "step_usuario_logado");
    }
}