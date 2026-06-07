package Selenium;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Selenium.pages.CadastroPetPage;
import Selenium.pages.DashboardPage;
import Selenium.pages.LoginPage;
import Selenium.pages.baseTest;

public class testeCadastro extends baseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CadastroPetPage cadastroPetPage;

    @Test // Cadastrar pet com sucesso
    public void CadastrarPetComSucesso() throws InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cadastroPetPage = new CadastroPetPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        dashboardPage.abrirCadastroPet();
        Thread.sleep(1000);

        // Preencher formulário para cadastrar o pet Noah
        cadastroPetPage.preencherNomePet("Noah");
        cadastroPetPage.preencherNomeDono("Douglas Jorge");
        cadastroPetPage.selecionarEspecie("Cachorro 🐕");
        cadastroPetPage.preencherIdade("5 anos");
        cadastroPetPage.preencherObservacoes("Tosa bebê, com cuidado extra na barriguinha, por causa de alergia");
        cadastroPetPage.marcarVacinado();
        Thread.sleep(2000);

        cadastroPetPage.anexarFotoPet(
        "C:\\Users\\djorger\\Desktop\\Automacao\\Noah.jpeg");
        Thread.sleep(2000);

        cadastroPetPage.salvarCadastro();
        Thread.sleep(2000);

        // Validar mensagem de sucesso após a inclusão
        Assert.assertEquals("✅ Pet cadastrado com sucesso!", cadastroPetPage.obterMensagemSucesso());

        // Validar se o registro do Noah aparece na tabela de pets cadastrados
        List<WebElement> linhas = driver.findElements(By.xpath("//tbody[@id='pets-table-body']/tr"));
        boolean encontrouNoah = false;

        for (WebElement linha : linhas) {
        List<WebElement> colunas = linha.findElements(By.tagName("td"));
        if (colunas.get(0).getText().equals("Noah")) {

        Assert.assertEquals("Noah", colunas.get(0).getText());
        Assert.assertEquals("Douglas Jorge", colunas.get(1).getText());
        Assert.assertEquals("Cachorro", colunas.get(2).getText());
        Assert.assertEquals("5 anos", colunas.get(3).getText());
        Assert.assertEquals("Sim", colunas.get(4).getText());

        encontrouNoah = true;
        break;
    }
}
        Assert.assertTrue(
        "Registro do Noah não foi encontrado na tabela", encontrouNoah);
    }

    @Test // Validar mensagem de campo obrigatório
    public void validarCampoObrigatorio() throws InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cadastroPetPage = new CadastroPetPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        dashboardPage.abrirCadastroPet();
        Thread.sleep(1000);

        // Preencher formulário para cadastro, sem informar o campo obrigatório "Nome do Pet"
        cadastroPetPage.preencherNomePet("");
        cadastroPetPage.preencherNomeDono("Douglas Jorge");
        cadastroPetPage.selecionarEspecie("Roedor 🐹");
        cadastroPetPage.preencherIdade("2 anos");
        cadastroPetPage.preencherObservacoes("Tranquilo, não gosta de barulho");
        Thread.sleep(2000);

        cadastroPetPage.salvarCadastro();
        Thread.sleep(2000);

        // Validar mensagem de campo obrigatório
        Alert alert = driver.switchTo().alert();
        String mensagem = alert.getText();

        Assert.assertEquals("⚠️ Preencha: Nome do Pet, Dono e Espécie (obrigatórios).",mensagem);
        alert.accept();;
    }

    @Test // Limpar os campos do formulário de cadastro
    public void limparCamposCadastroPet() throws InterruptedException {

    loginPage = new LoginPage(driver);
    dashboardPage = new DashboardPage(driver);
    cadastroPetPage = new CadastroPetPage(driver);

    loginPage.realizarLogin("admin", "123456");
    Thread.sleep(1000);

    dashboardPage.abrirCadastroPet();
    Thread.sleep(1000);

    cadastroPetPage.preencherNomePet("Noah");
    cadastroPetPage.preencherNomeDono("Douglas Jorge");
    cadastroPetPage.selecionarEspecie("Cachorro 🐕");
    cadastroPetPage.preencherIdade("5 anos");
    cadastroPetPage.preencherObservacoes("Pet fofinho, super tranquilo");
    cadastroPetPage.marcarVacinado();
    Thread.sleep(1000);

    cadastroPetPage.limparFormulario();
    Thread.sleep(1000);

    }
}