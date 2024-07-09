package com.tests.products;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.LoginPage;
import com.tests.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners(com.tests.common.listeners.TestListener.class)
public class ProductValidation extends BaseTest {

//    private WebDriver driver;
    private ProductsPage productsPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;
    private final long delay = 2000;//2 secs


    @BeforeClass
    public void TestSetup(){
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        productsPage = new ProductsPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
        TestListener.getTest().info("Starting Validation Test");
    }
    @AfterClass
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(priority = 1)
    @Parameters({"user","pass"})
    public void VerifyProductsPageLayout(@Optional String username,String password) throws InterruptedException {
        test = TestListener.getNode();
        test.info("Test is Starting");

        loginPage.login(username, password);
        test.info("Successfully Login");
        test.info("Validating Page Layout if it exists");
        Assert.assertTrue(productsPage.inventoryGrid.isDisplayed());
        Assert.assertTrue(productsPage.header.isDisplayed());
        Assert.assertTrue(productsPage.footer.isDisplayed());
        Assert.assertTrue(productsPage.main.isDisplayed());

        test.pass("Page Layout Containers exists");
        Thread.sleep(1000);
    }


    @Test(priority = 2)
    public void VerifyProductsDetailDisplay() throws InterruptedException {
        test = TestListener.getNode();
        test.info("Test is Starting ...");
        test.info("Verifying products details if they exist");

        int itemsSize = productsPage.totalItems.size();
        Assert.assertEquals(6,itemsSize);

        test.pass("Image, Name and Price are displayed");
    }

    @Test(priority = 3)
    public void VerifyProductSorting() throws InterruptedException {
        test = TestListener.getNode();
        test.info("Test is Starting");

        //SORT BY PRICE ASCENDING
        test.info("Testing SORT BY PRICE ASCENDING");
        Assert.assertTrue(productsPage.sortButton.isDisplayed());
        productsPage.ChangeSort(ProductsPage.SortType.PRICE_ASC);
        Thread.sleep(delay);
        test.pass("Sort By Price Ascending Passed");

        //SORT BY PRICE DESCENDING
        test.info("Testing SORT BY PRICE DESCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRICE_DESC);
        Thread.sleep(delay);
        test.pass("Sort By Price Descending Passed");

        //SORT BY NAME ASCENDING
        test.info("Testing SORT BY NAME ASCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRODUCT_NAME_ASC);
        Thread.sleep(delay);
        test.pass("Sort By Product Name Descending Passed");

        //SORT BY NAME DESCENDING
        test.info("Testing SORT BY NAME DESCENDING");
        productsPage.ChangeSort(ProductsPage.SortType.PRODUCT_NAME_DESC);
        Thread.sleep(delay);
        test.pass("Sort By Product Name Descending Passed");
    }
}
