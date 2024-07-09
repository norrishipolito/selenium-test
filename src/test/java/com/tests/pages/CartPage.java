package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 2000;

    @FindBy(css=".cart_item")
    public List<WebElement> cartItems;

    @FindBy(css=".cart_list")
    public WebElement cartList;

    public CartPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        PageFactory.initElements(driver, this);
    }
    public void clickCart(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }

    public void clickCheckout(){
        driver.findElement(By.cssSelector(".checkout_button")).click();
    }
    public void removeAllInCart() throws InterruptedException {
        for (WebElement item : cartItems) {
            item.findElement(By.cssSelector(".btn")).click();
            Thread.sleep(500);
        }
    }
}
