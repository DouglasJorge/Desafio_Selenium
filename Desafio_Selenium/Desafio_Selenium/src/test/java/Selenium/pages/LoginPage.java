package Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    @FindBy(id = "login-username")
    public WebElement campoUsername;

    @FindBy(id = "login-password")
    public WebElement campoPassword;

    @FindBy(id = "login-submit")
    public WebElement botaoLogin;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void realizarLogin(String user, String pass) {
        campoUsername.sendKeys(user);
        campoPassword.sendKeys(pass);
        botaoLogin.click();
    }
    
}
