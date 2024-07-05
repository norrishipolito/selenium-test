package com.tests.products;

import com.tests.common.BaseTest;
import com.tests.pages.LoginPage;
import com.tests.pages.ProductsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CheckOutTest extends BaseTest {
    private ProductsPage productsPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;
    private final long delay = 2000;

    @BeforeTest
    public void TestSetup(){
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }
    @AfterTest
    public void tearDownMethod(){
        if (driver != null){
            driver.quit();
        }
    }
    @Test
    public void CheckoutShirts(){

    }
}
