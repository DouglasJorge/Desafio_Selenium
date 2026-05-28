package Selenium.pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class baseTest {
    private final String URL = "file:///C:/Users/djorger/Desktop/Automacao/desafio_final_selenium_java.html";
    protected WebDriver driver;
    //protected LoginPage loginPage;

    @Before
    public final void iniciar() {
        driver = new FirefoxDriver();
        //loginPage = new LoginPage(driver);
        driver.get(URL);
    }

        @After
    public void finalizar() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
