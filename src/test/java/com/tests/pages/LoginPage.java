package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 3;



    public LoginPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        PageFactory.initElements(driver, this);
    }

    public void InputUsername(String username){
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
    }
    public void InputPassword(String password){
        WebElement usernameInput = driver.findElement(By.id("password"));
        usernameInput.sendKeys(password);
    }
    public void ClickLoginButton(){
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }
    public WebElement GetPageDisplay(){
        return driver.findElement(By.id("root"));
    }
    public void loginStandardUser(){
        InputUsername("standard_user");
        InputPassword("secret_sauce");
        ClickLoginButton();
    }
    public void login(String username, String password){
        InputUsername(username);
        InputPassword(password);
        ClickLoginButton();
    }
}
