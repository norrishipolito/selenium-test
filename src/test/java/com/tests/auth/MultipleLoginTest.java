package com.tests.auth;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import com.tests.common.utilities.DataProviderUtil;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners(com.tests.common.listeners.TestListener.class)
public class MultipleLoginTest extends BaseTest {
    private LoginPage loginPage;
    private SoftAssert softAssert;
    private final long delay = 3;
    private WebDriverWait wait;

    @DataProvider(name="LoginData")
    public Object[][] getLoginData(){
        return DataProviderUtil.LoginData();
    }

    @BeforeClass
    public void TestSetup(){

        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        TestListener.getTest().info("Starting Multiple Login Test");
        TestListener.getTest().getModel().setDescription("Test All Existing User");
        TestListener.getTest().assignCategory("Data Driven Test");
    }
    @AfterClass
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(dataProvider = "LoginData")
    public void LoginCheck(String username, String password){
        driver.get("https://www.saucedemo.com/");
        test = TestListener.getNode();
        test.getModel().setName(String.format("Login Check -> Username: %s Password: %s",username,password));
        test.assignCategory("");
        test.info("Login Test Starting:");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        loginPage.InputUsername(username);
        loginPage.InputPassword(password);

        loginPage.ClickLoginButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

}
