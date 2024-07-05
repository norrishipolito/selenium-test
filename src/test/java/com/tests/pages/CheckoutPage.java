package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 2000;

    @FindBy(id="first-name")
    public WebElement firstName;

    @FindBy(id="last-name")
    public WebElement lastName;

    @FindBy(id="postal-code")
    public WebElement postalCode;

    @FindBy(css=".submit-button")
    public WebElement submitButton;

    @FindBy(id="finish")
    public WebElement finish;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        PageFactory.initElements(driver, this);
    }
    public void inputCartInformation(String firstName, String lastName, String postalCode){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys((postalCode));
    }

    public void clickContinue(){
        this.submitButton.click();
    }

    public void clickFinish(){
        this.finish.click();
    }
}
