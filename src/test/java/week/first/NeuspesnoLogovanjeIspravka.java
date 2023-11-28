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


public class NeuspesnoLogovanjeIspravka {


    private static final String WRONGUSERNAME = "wrong_user";
    private static final String WRONGPASSWORD = "wrong_password";

    @Test
    public void Test(){
        WebDriver driver = WebDriverFabric.startBrowser("chrome");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.typeOnUsernameFieldXPath(WRONGUSERNAME);
        loginpage.typeOnPasswordFieldCSS(WRONGPASSWORD);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        loginpage.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        System.out.println("Current url is: " + driver.getCurrentUrl());
        boolean could_find = driver.findElement(By.className(("error-message-container"))).isDisplayed();
        // bilo koji od ovih assertova je pojedinacno dovoljan, ali sam ih svejedno sve stavio radi primera
        // i dalje smo na login stranici
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        // nadjen je error message box
        Assert.assertTrue(could_find);
        driver.quit();
    }

}
