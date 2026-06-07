package Selenium;

import Selenium.pages.baseTest;
import Selenium.pages.LoginPage;
//import Selenium.pages.DashboardPage;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;

public class testDashboard extends baseTest {

    @Test
    public void testeBanhoTosa() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizarLogin("admin", "123456");

        //Verificar os serviços disponíveis no Dashboard
        
        //Banho & Tosa
        Assert.assertTrue(driver.findElement(By.id("card-bath")).isDisplayed());
        String banhoTosa = driver.findElement(By.xpath("//div[@id='card-bath']")).getText();
        Assert.assertEquals("🛁 Banho & Tosa", banhoTosa);

    }

}