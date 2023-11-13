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
import java.util.concurrent.TimeUnit;


public class NeuspesnoLogovanje {

    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    public static void Test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String currentURL =
                driver.getCurrentUrl();

        System.out.println("CURRENT URL IS -> " + currentURL);

        WebElement usernameFieldXPath =
                driver.findElement(By.xpath("//input[@type='text']"));

        usernameFieldXPath.sendKeys("pogresan_username");


        WebElement usernameFieldCSS =
                driver.findElement(By.cssSelector("input[type='password']"));

        usernameFieldCSS.sendKeys("pogresan_password");

        WebElement logInButtonCSS =
                driver.findElement(By.cssSelector("input[value='Login']"));

        logInButtonCSS.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        logInButtonCSS =
                driver.findElement(By.cssSelector("input[value='Login']"));

        Assert.assertTrue(logInButtonCSS != null, "test passed!");

        driver.close();
    }
}