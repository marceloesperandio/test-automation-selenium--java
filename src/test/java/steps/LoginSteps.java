package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static utils.DriverFactory.getDriver;
import static utils.ScreenshotUtil.takeScreenshot;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("o usuário acessa a página de login do OrangeHRM")
    public void oUsuárioAcessaAPáginaDeLoginDoOrangeHRM() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        takeScreenshot(driver, "step_login_page_acessada");
    }

    @When("o usuário informa o nome de usuário {string} e senha {string}")
    public void oUsuárioInformaONomeDeUsuárioESenha(String username, String password) {
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        takeScreenshot(driver, "step_dados_preenchidos");
    }

    @And("clica no botão de login")
    public void clicaNoBotãoDeLogin() {
        loginPage.clickLogin();
        takeScreenshot(driver, "step_login_enviado");
    }

    @Then("o sistema deve redirecionar para a dashboard")
    public void oSistemaDeveRedirecionarParaADashboard() {
        Assertions.assertTrue(loginPage.isOnDashboard(), "Dashboard não foi exibida após login");
        takeScreenshot(driver, "step_dashboard_acessada");
    }

    @Then("o sistema deve exibir uma mensagem de erro indicando falha no login.")
    public void o_sistema_deve_exibir_uma_mensagem_de_erro_indicando_falha_no_login() {
        Assertions.assertTrue(loginPage.isInvalidCredentialsMessageVisible(), "Mensagem de erro não foi exibida após login inválido");
        takeScreenshot(driver, "step_mensagem_erro_exibida");
    }
}