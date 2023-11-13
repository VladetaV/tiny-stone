package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FirstWeekAdvanced {



    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    WebDriver driver = null;

    //@Parameters({"browser"})
    //@BeforeMethod
    public void setup(){

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com/");

            //driver = WebDriverFabric.startBrowser(browser);

    }


    @Test
    public void thirdTest(){


     //   String currentURL =
     //           driver.getCurrentUrl();
     //   System.out.println("CURRENT URL IS -> " + currentURL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.getCurrentUrl();


    //    WebElement usernameFieldXPath =
    //            driver.findElement(By.xpath("//input[@type='text']"));
    //    usernameFieldXPath.sendKeys("admin");

        loginPage.typeOnUsernameFieldXPath(USERNAME);


    //    WebElement usernameFieldCSS =
    //           driver.findElement(By.cssSelector("input[type='password']"));
    //    usernameFieldCSS.sendKeys("password");

        loginPage.typeOnPasswordFieldCSS(PASSWORD);

    //    WebElement logInButtonCSS =
    //            driver.findElement(By.cssSelector("input[value='Login']"));
    //    logInButtonCSS.click();

        loginPage.clickOnLoginButton();


        HomePage homePage = new HomePage(driver);


    //    WebDriverWait wait =
    //            new WebDriverWait(driver, Duration.ofSeconds(10));
    //    wait.until(ExpectedConditions.titleIs("Swag Labs"));

        homePage.waitUntilPageTitleIsCorrect(5, HomePage.PAGE_TITLE);


    //    currentURL =
    //            driver.getCurrentUrl();
    //    System.out.println("CURRENT URL IS -> " + currentURL);

        homePage.getCurrentUrl();


    //    WebElement menu = driver.findElement((By.id("react-burger-menu-btn")));
    //    menu.click();

        TopMenu topMenu = new TopMenu(driver);

        topMenu.clickTopMenu();



    //    WebElement logOutButton =
    //            driver.findElement(By.id("logout_sidebar_link"));
    //    logOutButton.click();

        homePage.clickLogoutButton();


    //    currentURL =
    //            driver.getCurrentUrl();
    //    System.out.println("CURRENT URL IS -> " + currentURL);

        loginPage.getCurrentUrl();

        Assert.assertTrue(loginPage.isLoginButtonVisible(), "yrytryturuytrjtyr");

    }


    @AfterMethod
    public void teardown(){


            LoginPage loginPage = new LoginPage(driver);

            loginPage.quitBrowser();

    }

}






