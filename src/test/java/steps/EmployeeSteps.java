package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.EmployeePage;
import utils.UIActions;

import static java.lang.System.out;
import static utils.DriverFactory.getDriver;
import static utils.ScreenshotUtil.takeScreenshot;

public class EmployeeSteps {

    //TODO:
    // implementar forma de importar foto.
    // implementar uma forma que não concatene com o valor já existente do campos Employee ID.
    // Criar assert para verificar se o usuário foi adicionado com sucesso.


    private WebDriver driver;
    private EmployeePage employeePage;
    private UIActions uiActions;

    @Given("o usuário está logado na plataforma OrangeHRM")
    public void oUsuárioEstáLogadoNaPlataformaOrangeHRM() {
        driver = getDriver();
        employeePage = new EmployeePage(driver);
        uiActions = new UIActions(driver);

        out.println("Usuário logado automaticamente via Hook.");
        takeScreenshot(driver, "step_usuario_logado");
    }

    @And("acessa o menu {string}")
    public void acessaOMenu(String arg0) {

        employeePage.clickMenuPim();
        takeScreenshot(driver, "step_clique_no_botão_PIM");
    }

    @And("clica na opção Add Employee")
    public void clicaNaOpcaoAddEmployee() {
        employeePage.clickMenuAddEmployee();
        takeScreenshot(driver, "step_clique_no_botão_Add_Employee");
    }

    @And("preenche os campos com os valores {}, {}, {}")
    public void preencheOsCamposComOsValores(String firstName, String middleName, String lastName) {
        employeePage.fillFirstName(firstName);
        employeePage.fillMiddleName(middleName);
        employeePage.fillLastName(lastName);
        takeScreenshot(driver, "step_preenchimento_dos_campos");
    }

    @And("preenche o campo Employee ID com o valor {}")
    public void preencheOCampoEmployeeIDComOValor(String employeeID) {
        employeePage.fillEmployeeId(employeeID);
    }

    @And("ativa a opção {string}")
    public void ativaAOpcao(String arg0) {
        employeePage.clickToggleSwitchCreateLoginDetails();
    }

    @And("informa as credenciais passando os valores: {string}, {string}, {string}.")
    public void informaAsCredenciaisPassandoOsValores(String username, String password, String confirmPassword) {
        employeePage.fillUsername(username);
        employeePage.fillPassword(password);
        employeePage.fillConfirmPassword(confirmPassword);
        takeScreenshot(driver, "step_preenchimento_das_credenciais");
    }

    @And("faz o upload da foto com contida no caminho {string}")
    public void fazOUploadDaFotoComContidaNoCaminho(String photo) {
        employeePage.clickUploadIcon();
        employeePage.uploadPhoto(photo);
        takeScreenshot(driver, "step_upload_da_foto");

        try {
            Thread.sleep(3000); // Espera 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uiActions.pressEscape();
    }

    @And("clica em {string}")
    public void clicaEm(String arg0) {
        employeePage.clickBtnSave();
        takeScreenshot(driver, "step_clicando_no_botão_Salvar");
    }

    @Then("o sistema deve exibir uma mensagem de sucesso no rodapé")
    public void oSistemaDeveExibirUmaMensagemDeSucessoNoRodape() {
        employeePage.verifyMensageSucess();
    }
}