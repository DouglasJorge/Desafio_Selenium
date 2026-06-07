package Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CadastroPetPage {


    @FindBy(id = "pet-name")
    private WebElement campoNomePet;

    @FindBy(id = "pet-owner")
    private WebElement campoNomeDono;

    @FindBy(id = "pet-species")
    private WebElement comboEspecie;

    @FindBy(id = "pet-age")
    private WebElement campoIdade;

    @FindBy(id = "pet-notes")
    private WebElement campoObservacoes;

    @FindBy(id = "pet-vaccinated")
    private WebElement checkVacinado;

    @FindBy(id = "pet-photo")
    private WebElement campoFotoPet;

    @FindBy(id = "save-pet-button")
    private WebElement botaoSalvar;

    @FindBy(id = "clear-form-button")
    private WebElement botaoLimpar;

    @FindBy(id = "pet-success")
    private WebElement mensagemSucesso;

    public CadastroPetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void preencherNomePet(String nome) {
        campoNomePet.clear();
        campoNomePet.sendKeys(nome);
    }

    public void preencherNomeDono(String dono) {
        campoNomeDono.clear();
        campoNomeDono.sendKeys(dono);
    }

    public void selecionarEspecie(String especie) {

    Select select = new Select(comboEspecie);
    select.selectByVisibleText(especie);
    }

    public void preencherIdade(String idade) {
        campoIdade.clear();
        campoIdade.sendKeys(idade);
    }

    public void preencherObservacoes(String observacoes) {
        campoObservacoes.clear();
        campoObservacoes.sendKeys(observacoes);
    }

    public void marcarVacinado() {
        if (!checkVacinado.isSelected()) {
            checkVacinado.click();
        }
    }

    public void desmarcarVacinado() {
        if (checkVacinado.isSelected()) {
            checkVacinado.click();
        }
    }

    public void anexarFotoPet(String caminhoArquivo) {
        campoFotoPet.sendKeys(caminhoArquivo);
    }

    public void salvarCadastro() {
        botaoSalvar.click();
    }

    public void limparFormulario() {
    botaoLimpar.click();
    }

    public String obterMensagemSucesso() {
        return mensagemSucesso.getText();
    }
}