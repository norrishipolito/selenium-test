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

@Listeners(com.tests.common.listeners.TestListener.class)
public class CancelCheckout extends BaseTest {

    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;
    private final long delay = 1000;//2 secs

    @BeforeClass
    public void TestSetup(){
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        productsPage = new ProductsPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
        TestListener.getTest().info("Starting Cancel Checkout Test");
    }
    @AfterClass
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
    @AfterMethod
    public void addDelay() throws Exception{
        Thread.sleep(delay);
    }

    @Test(priority = 0)
    public void LoginAsStandardUser(){
        test = TestListener.getNode();
        test.info("Starting test...");
        //Login as Standard User
        loginPage.loginStandardUser();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Successfully Login");
    }
    @Test(priority = 1)
    @Parameters({"productType"})
    public void CheckoutTShirts(@Optional("Sauce Labs") String productType){
        test= TestListener.getNode();
        test.info("Starting test...");
        test.info("Parameters: "+productType);
        //ADD TO CART ALL TSHIRTS
        productsPage.addToCartByProduct(productType);
        String totalItem= productsPage.getCartTotalItem().getText();
        Assert.assertEquals(Integer.parseInt(totalItem),5);
        test.pass("Added "+totalItem+" items in the cart");
    }

    @Test(priority = 2)
    @Parameters({"productType"})
    public void ProceedToCart(@Optional("Sauce Labs") String productType){
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
    }
    @Test(priority = 3)
    public void ProceedToCheckout(){
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

    @Test(priority = 5)
    public void CancelOrder() throws InterruptedException {
        test = TestListener.getNode();
        test.info("Starting test ...");
        checkoutPage.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Canceled Button Works");
        cartPage.clickCart();
        cartPage.removeAllInCart();
        Assert.assertTrue(cartPage.cartList.findElements(By.cssSelector(".cart_item")).isEmpty());
        test.pass("Removed All Items in the Cart");
    }
    @Test(priority = 6)
    public void Logout() {
        test = TestListener.getNode();
        test.info("Starting Test ...");
        productsPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        test.pass("Successfully Logout");
    }

}
