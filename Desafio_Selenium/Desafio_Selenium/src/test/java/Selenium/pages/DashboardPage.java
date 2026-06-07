package Selenium.pages;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    private WebDriver driver;

    @FindBy(id = "promotion-button")
    private WebElement botaoPromocao;

    @FindBy(id = "open-register-page")
    private WebElement botaoCadastrarPet;

    @FindBy(id = "logout-button")
    private WebElement botaoLogout;

    @FindBy(css = "#services-table tbody tr")
    private List<WebElement> linhasTabela;

    @FindBy(id = "nav-login")
    private WebElement botaoLogin;

    @FindBy(className = "info-message")
    private WebElement mensagemInformativa;

    @FindBy(id = "user-status")
    private WebElement statusUsuario;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clicarPromocao() {
        botaoPromocao.click();
    }

    public String obterMensagemPromocao() {

        Alert alert = driver.switchTo().alert();
        String mensagem = alert.getText();
        alert.accept();
        return mensagem;
    }

    public void abrirCadastroPet() {
        botaoCadastrarPet.click();
    }

    public void clicarLogout() {
        botaoLogout.click();
    }

    public int quantidadeServicos() {
        return linhasTabela.size();
    }

    public void clicarLogin() {
    botaoLogin.click();
    }

    public List<String> obterServicos() {
        List<String> servicos = new ArrayList<>();
        for (WebElement linha : linhasTabela) {
            servicos.add(linha.getText());
        }
        return servicos;
    }

    public String obterMensagemInformativa() {
        return mensagemInformativa.getText();
    }

    public String obterStatusUsuario() {
        return statusUsuario.getText();
    }
}