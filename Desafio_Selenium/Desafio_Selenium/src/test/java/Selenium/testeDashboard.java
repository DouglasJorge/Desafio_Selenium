package Selenium;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Selenium.pages.DashboardPage;
import Selenium.pages.LoginPage;
import Selenium.pages.baseTest;

public class testeDashboard extends baseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    @Test //Validar mensagem ao clicar no botão de ver promoção.
    public void ClicarBotaoPromocao() throws InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(2000);

        dashboardPage.clicarPromocao();
        Thread.sleep(1000);
        Assert.assertEquals("🎁 Promoção liberada: 20% OFF em banho completo!",dashboardPage.obterMensagemPromocao()
        );
    }

    @Test //Validar a tabela de serviços
    public void verificarTabelaServicos() throws InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        List<String> servicos = dashboardPage.obterServicos();
        
        Assert.assertTrue(servicos.get(0).contains("Banho tradicional"));
        Assert.assertTrue(servicos.get(1).contains("Tosa higiênica"));
        Assert.assertTrue(servicos.get(2).contains("Consulta veterinária"));
        Assert.assertTrue(servicos.get(3).contains("Pacote banho + tosa"));
    }

    @Test //Validar as informações da tabela de serviço da "Tosa higiênica".
    public void verificarTabelaCompletaDeUmServico() throws InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        List<WebElement> linhas = driver.findElements(By.xpath("//table[@id='services-table']/tbody/tr"));
    for (WebElement linha : linhas) {
        if (linha.getText().contains("Tosa higiênica")) {
                List<WebElement> colunas = linha.findElements(By.tagName("td"));
            String nome = colunas.get(0).getText();
            String duracao = colunas.get(1).getText();
            String valor = colunas.get(2).getText();
            Assert.assertEquals("Tosa higiênica", nome);
            Assert.assertEquals("1 hora", duracao);
            Assert.assertEquals("R$ 80,00", valor);
            break;
            }
        }
    }
    
    @Test //Validar a duração do serviço "Consulta veterinária".
    public void verificarDuracaoServicos() throws InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        List<WebElement> linhas = driver.findElements(By.xpath("//table[@id='services-table']/tbody/tr"));
    for (WebElement linha : linhas) {
        if (linha.getText().contains("Consulta veterinária")) {
                List<WebElement> colunas = linha.findElements(By.tagName("td"));
            String duracao = colunas.get(1).getText();
            Assert.assertEquals("30 min", duracao);
            break;
            }
        }
    } 
    
    @Test //Validar o valor do serviço "Pacote banho + tosa".
    public void verificarValorServicos() throws InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        List<WebElement> linhas = driver.findElements(By.xpath("//table[@id='services-table']/tbody/tr"));
    for (WebElement linha : linhas) {
        if (linha.getText().contains("Pacote banho + tosa")) {
                List<WebElement> colunas = linha.findElements(By.tagName("td"));
            String valor = colunas.get(2).getText();
            Assert.assertEquals("R$ 115,00", valor);
            break;
            }
        }
    }

    @Test //Validar mensagem ao clicar em Login estando logado
    public void validarMensagemAoClicarLoginLogado()
        throws InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        dashboardPage.clicarLogin();
        Thread.sleep(1000);

        Assert.assertEquals(
        "ℹ️ Você já está logado. Para fazer login novamente, primeiro use \"Sair\".", dashboardPage.obterMensagemInformativa());
    }

    @Test //Realizar logout
    public void realizarLogoutComSucesso() throws InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        loginPage.realizarLogin("admin", "123456");
        Thread.sleep(1000);

        dashboardPage.clicarLogout();

        Assert.assertEquals("🔒 Deslogado",dashboardPage.obterStatusUsuario());
    }

}
