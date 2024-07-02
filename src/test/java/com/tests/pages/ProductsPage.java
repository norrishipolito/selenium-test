package com.tests.pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import java.time.Duration;


public class ProductsPage {
    public enum  SortType{
        PRODUCT_NAME("Product Name"),
        PRICE("Price");

        private final String type;


        SortType(String type) {
            this.type = type;

        }

        public String getType(){
            return this.type;
        }
    }
    public enum SortBy{
        ASCENDING,
        DESCENDING
    }
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;
    private final long delay = 3;
    private final String gear_xpath = "/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[4]/a";
    private final String bags_xpath ="/html/body/div[2]/main/div[4]/div[2]/div[1]/div[2]/dl/dd/ol/li[1]/a";
    private final String next_button_xpath = "/html/body/div[2]/main/div[3]/div[1]/div[4]/div[2]/ul/li[3]/a";
    private final String home = "/html/body/div[2]/header/div[2]/a/img";
    private final String dropDown_icon_xpath = "/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[4]/a/span[1]";
    private final String neural_element = "/html/body/div[2]/div[1]/div/div[2]";

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
    }

    public void ClickGear(){
        WebElement gear = driver.findElement(By.xpath(gear_xpath));
        gear.click();

    }

    public void ClickBags(){
        WebElement bags = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bags_xpath)));
        bags.click();
    }

    public void NavigateToBags(){
       ClickGear();
       ClickBags();
    }

    public void ScrollToBottom(){
        action.moveByOffset(0,Integer.MAX_VALUE);
    }

    public void NavigateToHome(){
        WebElement home_logo = driver.findElement(By.xpath(home));
        home_logo.click();
    }

    public void ClickNextPagination() throws InterruptedException {
        WebElement nextButton = driver.findElement(By.xpath(next_button_xpath));
        Thread.sleep(delay);
        nextButton.click();
    }

    public void ClickSortDropDown(){

        WebElement sorterDD = driver.findElement(By.className("sorter-options"));
        sorterDD.click();
    }

    public void ChangeSortTypeDropDown(SortType sortType){
        WebElement sorterDD = driver.findElement(By.className("sorter-options"));
        Select select = new Select(sorterDD);

        select.selectByVisibleText(sortType.getType());

    }

    public void ChangeSortBy(SortBy sortBy){
        WebElement sorterByAction = driver.findElement(By.className("sorter-action"));
        String title = sorterByAction.getAttribute("title");

        if(title.contains("Descending") && sortBy.equals(SortBy.DESCENDING)){
            sorterByAction.click();
        }

        if(title.contains("Ascending") && sortBy.equals(SortBy.ASCENDING)){
            sorterByAction.click();
        }

    }

    public void ChangeSort(SortType sortType, SortBy sortBy){
        ClickSortDropDown();
        ChangeSortTypeDropDown(sortType);
        ChangeSortBy(sortBy);
    }
}
