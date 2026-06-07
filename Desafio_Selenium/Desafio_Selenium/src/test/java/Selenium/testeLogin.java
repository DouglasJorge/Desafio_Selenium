package Selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Selenium.pages.LoginPage;
import Selenium.pages.baseTest;

public class testeLogin extends baseTest {
    private LoginPage loginPage;

    @Test //Verificar se o login foi realizado com sucesso.
    public void realizarLoginSucesso()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("admin", "123456");
     
        Assert.assertTrue(driver.findElement(By.id("user-status")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//span[@id='user-status']")).getText();
        Assert.assertEquals("✅ Admin logado", mensagem);
    }

    @Test //Verificar que o login falhou devido a usuário inválido
    public void realizarLoginUsuarioInvalido()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("douglas", "123456");
     
        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-error']")).getText();
        Assert.assertEquals("❌ Usuário ou senha inválidos. Use admin / 123456", mensagem);
    }

    @Test //Verificar que o login falhou devido a senha inválida
    public void realizarLoginSenhaInvalida()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("admin", "123456789");
     
        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-error']")).getText();
        Assert.assertEquals("❌ Usuário ou senha inválidos. Use admin / 123456", mensagem);
    }

    @Test //Validar mensagem ao clicar em Home sem estar logado
    public void clicarHomeSemLogin()  {
        loginPage = new LoginPage(driver);
        WebElement botaoHome = driver.findElement(By.id("nav-home"));
        botaoHome.click();
        
        Assert.assertTrue(driver.findElement(By.id("login-global-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-global-error']")).getText();
        Assert.assertEquals("⚠️ É necessário estar logado para acessar esta página. Faça login.", mensagem);
    }

    @Test //Validar mensagem ao clicar em Cadastro sem estar logado
    public void clicarCadastroSemLogin()  {
        loginPage = new LoginPage(driver);
        WebElement botaoCadastro = driver.findElement(By.id("nav-register"));
        botaoCadastro.click();
        
        Assert.assertTrue(driver.findElement(By.id("login-global-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-global-error']")).getText();
        Assert.assertEquals("⚠️ É necessário estar logado para acessar esta página. Faça login.", mensagem);
    }
   
}
