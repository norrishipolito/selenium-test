package com.tests.auth;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.LoginPage;
import com.tests.pages.ProductsPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners(com.tests.common.listeners.TestListener.class)
public class WorkflowTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private SoftAssert softAssert;
    private final long delay=1000;
    private WebDriverWait wait;

    @BeforeClass
    public void TestSetup(){
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
        TestListener.getTest().info("Starting Authentication Workflow Test");
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
    @Test(priority = 1)
    @Parameters({"username","password"})
    public void Login(@Optional String username, String password){
        test = TestListener.getNode();
        test.info("Starting test ...");
        test.info("Filling up Login Form");
        loginPage.login(username,password);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Successfully Login");
    }
    @Test(priority = 2)
    public void Logout() throws InterruptedException {
        test = TestListener.getNode();
        test.info("Starting Test ...");
        productsPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        test.pass("Successfully Logout");
    }
}
