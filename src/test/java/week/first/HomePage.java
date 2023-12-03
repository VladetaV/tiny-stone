package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){

        super(driver);
    }

    public static final String PAGE_TITLE = "Swag Labs";

    By logOutButton = By.id("logout_sidebar_link");

    By bmItemList = By.id("react-burger-menu-btn");

    By resetStateButton = By.id("reset_sidebar_link");

    By addToCartBackpackButton = By.id("add-to-cart-sauce-labs-backpack");

    public void clickBmButton(){
        driver.findElement(bmItemList).click();
    }
    public void  clickResetStateButton(){
        driver.findElement(resetStateButton).click();
    }
    public void clickAddToCartButton(){
        driver.findElement(addToCartBackpackButton).click();
    }
    public void clickLogoutButton(){
        driver.findElement(logOutButton).click();
    }


}
