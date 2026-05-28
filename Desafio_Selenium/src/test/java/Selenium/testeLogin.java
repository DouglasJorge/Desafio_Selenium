package Selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import Selenium.pages.LoginPage;
import Selenium.pages.baseTest;

public class testeLogin extends baseTest {
    private LoginPage loginPage;

    @Test
    public void realizarLoginSucesso()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("admin", "123456");
     
        //Verificar se o login foi realizado com sucesso

        Assert.assertTrue(driver.findElement(By.id("user-status")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//span[@id='user-status']")).getText();
        Assert.assertEquals("✅ Admin logado", mensagem);
    }

    @Test
    public void realizarLoginUserInvalido()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("douglas", "123456");
     
        //Verificar que o login falhou devido a usuário inválido

        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-error']")).getText();
        Assert.assertEquals("❌ Usuário ou senha inválidos. Use admin / 123456", mensagem);
    }

    @Test
    public void realizarLoginPassInvalido()  {
        loginPage = new LoginPage(driver);
        loginPage.realizarLogin("admin", "123456789");
     
        //Verificar que o login falhou devido a senha inválida

        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
        String mensagem = driver.findElement(By.xpath("//div[@id='login-error']")).getText();
        Assert.assertEquals("❌ Usuário ou senha inválidos. Use admin / 123456", mensagem);
    }
   
}
