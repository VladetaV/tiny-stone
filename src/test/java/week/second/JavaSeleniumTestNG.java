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
    public void instanceDriver() {
        driver = WebDriverFabric.startBrowser("chrome");
    }

    // nakon testova gasimo chrome
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null)
            driver.quit();
    }


    @Test(dataProvider = "podaci")
    public void Test(String username, String password) {

        if (password.equals("secret_sauce")) {
            //instanicramo objekat klase LoginPage
            LoginPage login = new LoginPage(driver);
            // Vrsimo logovanje
            login.typeOnUsernameFieldXPath(username);
            login.typeOnPasswordFieldCSS(password);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            login.clickOnLoginButton();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
            // prosao je prvi deo testa
            login.getCurrentUrl();

            // Instanciramo objekat klase HomePage
            HomePage home = new HomePage(driver);
            home.clickAddToCartButton();
            home.clickBmButton();
            home.clickResetStateButton();
            home.clickLogoutButton();
            // proveravamo da li je bilo uspesno logoutovanje
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            // prosao je ceo test
            System.out.println("CURRENT URL IS -> " + driver.getCurrentUrl());
        } else { // ako smo usli ovde onda je uneta neispravna lozinka
            LoginPage loginpage = new LoginPage(driver);
            loginpage.typeOnUsernameFieldXPath(username);
            loginpage.typeOnPasswordFieldCSS(password);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            loginpage.clickOnLoginButton();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            System.out.println("CURRENT URL IS -> " + driver.getCurrentUrl());
            boolean could_find = driver.findElement(By.className(("error-message-container"))).isDisplayed();
            // bilo koji od ovih assertova je pojedinacno dovoljan, ali sam ih svejedno sve stavio radi primera
            // i dalje smo na login stranici
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            // nadjen je error message box
            Assert.assertTrue(could_find);


        }

    }

    // pravimo dataprovider
    // dva reda podataka su za ispravno logovanje a jedan red je za neispravno logovanje
    @DataProvider
    public Object[][] podaci() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
                {"wrong_username", "wrong_password"},
        };
    }

}