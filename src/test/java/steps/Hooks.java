package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.text.Normalizer;

import static utils.AllureReportSetup.prepareAllureResultsFolder;
import static utils.Logger.INFO;

public class Hooks {

    private static boolean isFirstExecution = true;

    @Before(order = 0)
    public void beforeAll() {
        if (isFirstExecution) {
            INFO("PREPARING ALLURE STRUCTURE ON FIRST EXECUTION...");

            prepareAllureResultsFolder();
            isFirstExecution = false;
        }
    }

    @Before(order = 1)
    public void setupScenario(Scenario scenario) {
        INFO("STARTING SCENARIO: " + scenario.getName());

        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickLogin();

        Assertions.assertTrue(loginPage.isOnDashboard(), "Login falhou durante o setup.");
    }

    @After(order = 1)
    public void tearDown() {
        INFO("CLOSING DRIVER...");

       DriverFactory.quitDriver();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            String stepName = Normalizer.normalize(scenario.getName(), Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .toLowerCase()
                    .replaceAll("[^a-z0-9]", "_");
            ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), "step_" + stepName);
        }
    }
}