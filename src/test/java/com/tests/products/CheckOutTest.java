package com.tests.products;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.CartPage;
import com.tests.pages.CheckoutPage;
import com.tests.pages.LoginPage;
import com.tests.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;


public class CheckOutTest extends BaseTest {
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;

    private final long delay = 2000;

    @BeforeClass
    public void TestSetup(){
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
        TestListener.getTest().info("Starting Checkout End to End Test");
    }
    @AfterClass
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
        test = TestListener.getNode();
        test.info("Starting test...");
        //Login as Standard User
        loginPage.loginStandardUser();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Successfully Login");
    }
    @Test(priority = 2)
    @Parameters({"productType"})
    public void CheckoutTShirts(@Optional String productType){
        test= TestListener.getNode();
        test.info("Starting test...");
        test.info("Parameters: "+productType);
        //ADD TO CART ALL TSHIRTS
        productsPage.addToCartByProduct(productType);
        String totalItem= productsPage.getCartTotalItem().getText();
        Assert.assertEquals(Integer.parseInt(totalItem),2);
        test.pass("Added "+totalItem+" items in the cart");


    }

    @Test(priority = 3)
    @Parameters({"productType"})
    public void ProceedToCheckout(@Optional String productType){
        test = TestListener.getNode();
        test.info("Starting test...");
        test.info("Parameters: "+productType);
        cartPage.clickCart();
        Assert.assertFalse(checkoutPage.cartItems.isEmpty());
        test.pass("Cart is Not Empty");
        test.info("Checking all cart items");
        //Get Cart Items
        List<WebElement> cartItems = checkoutPage.cartItems;

        for(WebElement item: cartItems){
            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            Assert.assertTrue(itemName.contains(productType));
        }
        test.pass("All items in the cart are "+productType);
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutPage.pageTitle.isDisplayed());
        test.pass("Successfully Navigated to Checkout Page");
    }

    @Test(priority = 4)
    public void InputCheckoutInformation() throws InterruptedException {
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "6000";
        test = TestListener.getNode();
        test.info("Starting test...");

        Assert.assertTrue(checkoutPage.firstName.isDisplayed(),"Element Not Found!");
        Assert.assertTrue(checkoutPage.lastName.isDisplayed(),"Element Not Found!");
        Assert.assertTrue(checkoutPage.postalCode.isDisplayed(),"Element Not Found!");
        test.pass("Checkout Form is available");
        test.info("Filling up Checkout Form");
        //Click Cart
        checkoutPage.inputCartInformation(firstName,lastName,postalCode);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.pageTitle.getText(),"Checkout: Overview");
        test.pass("Successfully Navigated to Confirmation Page");
    }

    @Test (priority = 5)
    @Parameters({"productType"})
    public void CheckoutFinalValidation(String productType){
        test = TestListener.getNode();
        test.info("Starting test...");
        test.info("Parameters: "+productType);
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),"Page Error!");
        test.pass("Page is on the final step of checkout");
        List<WebElement> cartItems = checkoutPage.cartItems;
        double totalPrice = 0;

        for(WebElement item: cartItems){
            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            String price = item.findElement(By.cssSelector(".inventory_item_price")).getText();
            Assert.assertTrue(itemName.contains(productType));
            totalPrice = totalPrice + checkoutPage.parseAmount(price);
        }
        Assert.assertEquals(cartItems.size(),2);
        test.pass("There are 2 items in the cart");
        test.pass("All items in the cart are "+productType);

        double tax = checkoutPage.getTax();
        totalPrice=tax + totalPrice;


        Assert.assertEquals(checkoutPage.getTotal(),totalPrice);
        test.pass("Total Price is Correct");
        checkoutPage.clickFinish();
    }

    @Test(priority = 6)
    public void ValidateSuccessCheckout(){
        test = TestListener.getNode();
        test.info("Starting test...");
        Assert.assertTrue(checkoutPage.completeHeader.isDisplayed());
        Assert.assertEquals(checkoutPage.completeHeader.getText(),"Thank you for your order!");
        test.pass("Success Order Complete.");
    }
}
