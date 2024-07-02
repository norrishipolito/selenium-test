package com.tests.products;

import com.aventstack.extentreports.Status;
import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Listeners(com.tests.common.listeners.TestListener.class)
public class ProductValidation extends BaseTest {

//    private WebDriver driver;
    private ProductsPage productsPage;
    private SoftAssert softAssert;
    private final long delay = 2000;//3 secs


    @BeforeTest
    public void TestSetup(){
        driver.get("https://magento.softwaretestingboard.com/");
        softAssert = new SoftAssert();
        productsPage = new ProductsPage(driver);

    }
    @AfterTest
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void VerifyProductsPageLayout() throws InterruptedException {
        test = TestListener.getTest();
        test.info("Test is Starting");
        //IMPLICITLY WAIT 5SECONDS
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement productGrid = driver.findElement(By.className("grid"));
        WebElement header = driver.findElement(By.tagName("header"));
        WebElement footer = driver.findElement(By.tagName("footer"));
        WebElement main = driver.findElement(By.tagName("main"));

        Assert.assertTrue(productGrid.isDisplayed());
        Assert.assertTrue(header.isDisplayed());
        Assert.assertTrue(footer.isDisplayed());
        Assert.assertTrue(main.isDisplayed());

        Thread.sleep(delay);
    }

    @Test
    public void VerifyPagination() throws InterruptedException {
        test = TestListener.getTest();
        test.info("Test is Starting");
        productsPage.NavigateToBags();
        //CHECK IF YOU ARE IN THE BAGS PAGE
        Assert.assertTrue(driver.getCurrentUrl().contains("bags.html"));
        test.pass("Page successfully redirect to 'bags.html'");
        productsPage.ScrollToBottom();
        Thread.sleep(delay);
        productsPage.ClickNextPagination();
        Assert.assertTrue(driver.getCurrentUrl().contains("p="));
        test.pass("Page Navigated to the next page");
        Thread.sleep(delay);
    }

    @Test
    public void VerifyProductsDetailDisplay() throws InterruptedException {
        test = TestListener.getTest();
        test.info("Test is Starting");
        productsPage.NavigateToHome();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement image = driver.findElement(By.className("product-item-photo"));
        WebElement name = driver.findElement(By.className("product-item-name"));
        WebElement price = driver.findElement(By.className("price"));

        wait.until(i->image.isDisplayed());

        softAssert.assertTrue(image.isDisplayed());
        softAssert.assertTrue(name.isDisplayed());
        softAssert.assertTrue(price.isDisplayed());

        softAssert.assertAll();
        test.pass("Image, Name and Price is displayed");
        Thread.sleep(delay);
    }

    @Test
    public void VerifyProductSorting() throws InterruptedException {
        test = TestListener.getTest();
        test.info("Test is Starting");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        productsPage.NavigateToHome();
        Thread.sleep(delay);
        productsPage.NavigateToBags();

        //SORT BY PRICE ASCENDING
        test.info("Testing SORT BY PRICE ASCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRICE,ProductsPage.SortBy.ASCENDING);
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_order=price"));
        Thread.sleep(delay);
        test.pass("Sort By Price Ascending Passed");

        //SORT BY PRICE DESCENDING
        test.info("Testing SORT BY PRICE DESCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRICE,ProductsPage.SortBy.DESCENDING);
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_order=price"));
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_dir=desc"));
        Thread.sleep(delay);
        test.pass("Sort By Price Descending Passed");

        //SORT BY NAME ASCENDING
        test.info("Testing SORT BY NAME ASCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRODUCT_NAME,ProductsPage.SortBy.ASCENDING);
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_order=name"));
        Thread.sleep(delay);
        test.pass("Sort By Product Name Descending Passed");

        //SORT BY NAME DESCENDING
        test.info("Testing SORT BY NAME DESCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRODUCT_NAME,ProductsPage.SortBy.DESCENDING);
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_order=name"));
        Assert.assertTrue(driver.getCurrentUrl().contains("product_list_dir=desc"));
        Thread.sleep(delay);
        test.pass("Sort By Product Name Descending Passed");
    }
}
