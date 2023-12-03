package week.second;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import week.first.HomePage;
import week.first.LoginPage;
import week.first.WebDriverFabric;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class JavaSeleniumTestNG {

    public static WebDriver driver = null;


    // pre pokretanja testova kreiramo objekat klase Webdriver
    @BeforeMethod(alwaysRun = true)
    public void instanceDriver(){
        driver = WebDriverFabric.startBrowser("chrome");
    }

    // nakon testova gasimo chrome
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        if (driver != null)
            driver.quit();
    }


    @Test(dataProvider = "podaci")
    public void Test(String username, String password){

        //instanicramo objekat klase LoginPage
        LoginPage login = new LoginPage(driver);
        // Vrsimo logovanje
        login.typeOnUsernameFieldXPath(username);
        login.typeOnPasswordFieldCSS(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        login.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        // prosao je prvi deo testa
        login.getCurrentUrl();

        // Instanciramo objekat klase HomePage
        HomePage home = new HomePage(driver);
        home.clickAddToCartButton();
        home.clickBmButton();
        home.clickResetStateButton();
        home.clickLogoutButton();
        // proveravamo da li je bilo uspesno logoutovanje
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        // prosao je ceo test
        System.out.println("CURRENT URL IS -> " + driver.getCurrentUrl());
    }

    // pravimo dataprovider
    // dva reda podataka su za ispravno logovanje a jedan red je za neispravno logovanje
    // pa ce samim tim 2 testa proci a jedan pasti
    @DataProvider
    public Object[][] podaci(){
        return new Object[][]{
                {"standard_user","secret_sauce"},
                {"visual_user", "secret_sauce"},
                {"wrong_username", "wrong_password"},
        };
    }
}
