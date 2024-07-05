package com.tests.auth;

import com.tests.common.BaseTest;
import com.tests.common.listeners.TestListener;
import com.tests.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.tests.common.utilities.DataProviderUtil;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners(com.tests.common.listeners.TestListener.class)
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private SoftAssert softAssert;
    private final long delay = 3;
    private WebDriverWait wait;

    @DataProvider(name="LoginData")
    public Object[][] getLoginData(){
        return DataProviderUtil.LoginData();
    }

    @BeforeTest
    public void TestSetup(){
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
    }
    @AfterTest
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(dataProvider = "LoginData")
    public void LoginCheck(String username, String password){
        driver.get("https://www.saucedemo.com/");
        test = TestListener.getTest();
        test.info("Login Test Starting:");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        wait.until(ExpectedConditions.visibilityOf(loginPage.GetPageDisplay()));
        loginPage.InputUsername(username);
        loginPage.InputPassword(password);

        loginPage.ClickLoginButton();

//        driver.quit();
    }

}
