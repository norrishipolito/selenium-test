package com.tests.products;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.CartPage;
import com.tests.pages.CheckoutPage;
import com.tests.pages.LoginPage;
import com.tests.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class CheckOutTest extends BaseTest {
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;

    private final long delay = 2000;

    @BeforeTest
    public void TestSetup(){
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterTest
    public void tearDownMethod(){
        if (driver != null){
            driver.quit();
        }
    }
    @AfterMethod
    public void addDelay() throws Exception{
        Thread.sleep(2000);
    }
    @Test(priority = 1)
    public void LoginAsStandardUser(){
        test = TestListener.getTest();
        //Login as Standard User
        loginPage.loginStandardUser();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Successfully Login");
    }
    @Test(priority = 2)
    @Parameters({"productType"})
    public void CheckoutTShirts(@Optional String productType){
        //ADD TO CART ALL TSHIRTS
        productsPage.addToCartByProduct(productType);
        String totalItem= productsPage.getCartTotalItem().getText();
        Assert.assertEquals(Integer.parseInt(totalItem),2);

    }

    @Test(priority = 3)
    public void InputCheckoutInformation() throws InterruptedException {
        //Click Cart
        cartPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.inputCartInformation("John","Doe","6000");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
    }
}
