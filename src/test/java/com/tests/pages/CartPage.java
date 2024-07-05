package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 2000;
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
}
