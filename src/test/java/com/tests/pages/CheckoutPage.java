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

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 2000;

    @FindBy(css="#first-name")
    public WebElement firstName;

    @FindBy(css="#last-name")
    public WebElement lastName;

    @FindBy(css="#postal-code")
    public WebElement postalCode;

    @FindBy(css=".submit-button")
    public WebElement submitButton;

    @FindBy(id="finish")
    public WebElement finish;

    @FindBy(css=".cart_item")
    public List<WebElement> cartItems;

    @FindBy(css=".title")
    public WebElement pageTitle;

    @FindBy(css=".summary_tax_label")
    public WebElement taxLabel;

    @FindBy(css=".summary_total_label")
    public WebElement totalLabel;

    @FindBy(css=".complete-header")
    public WebElement completeHeader;

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

    public double parseAmount(String str){
        String[] splitPrice = str.split("\\$");
        return Double.parseDouble(splitPrice[1]);
    }

    public double getTax(){
        return parseAmount(taxLabel.getText());
    }

    public double getTotal(){
        return parseAmount(totalLabel.getText());
    }

}
