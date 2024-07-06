package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ProductsPage {
    public enum SortType {
        PRODUCT_NAME_ASC("Name (A to Z)"),
        PRODUCT_NAME_DESC("Name (Z to A)"),
        PRICE_ASC("Price (low to high)"),
        PRICE_DESC("Price (high to low)");

        private final String type;


        SortType(String type) {
            this.type = type;

        }

        public String getSortType() {
            return this.type;
        }
    }


    @FindBy(css = ".inventory_item" )
    private List<WebElement> allProducts;
    @FindBy(css="#logout_sidebar_link")
    public WebElement logoutButton;
    @FindBy(css="#react-burger-menu-btn")
    public WebElement hamburgerButton;

    @FindBy(css=".inventory_list")
    public WebElement inventoryGrid;

    @FindBy(css="#header_container")
    public WebElement header;

    @FindBy(css=".footer")
    public WebElement footer;

    @FindBy(css=".inventory_container")
    public WebElement main;

    @FindBy(css=".inventory_item")
    public List<WebElement> totalItems;


    @FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select")
    public WebElement sortButton;

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 3;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        PageFactory.initElements(driver, this);
    }

    public void ClickSort(){
        sortButton.click();
    }

    public WebElement getCartTotalItem(){
        return driver.findElement(By.cssSelector(".shopping_cart_badge"));
    }


    public void ChangeSortTypeDropDown(SortType sortType) {
        Select select = new Select(sortButton);

        select.selectByVisibleText(sortType.getSortType());

    }


    public void ChangeSort(SortType sortType) {
        ClickSort();
        ChangeSortTypeDropDown(sortType);
    }

    public void addToCartByProduct(String productName) {
//        List<WebElement> allProducts = driver.findElements(By.className())
        for (WebElement product : allProducts) {
          String itemName = product.findElement(By.cssSelector(".inventory_item_name")).getText();
          if(itemName.contains(productName)){
            product.findElement(By.cssSelector(".btn")).click();
          }
        }
    }

    public void logout() {
        hamburgerButton.click();
        logoutButton.click();
    }
}
