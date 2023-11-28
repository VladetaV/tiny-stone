package week.first;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class UspesnoLogovanjeIspravka {


    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    public void Test(){
        WebDriver driver = WebDriverFabric.startBrowser("chrome");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.typeOnUsernameFieldXPath(USERNAME);
        loginpage.typeOnPasswordFieldCSS(PASSWORD);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginpage.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginpage.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        driver.quit();
    }

}
