//package Tests;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import com.aceonlineshoeportal.tests.pages.HomePage;
//import com.aceonlineshoeportal.tests.pages.ProductsPage;
//
//public class SeleniumTest {
//    public static WebDriver driver;
//
//    static ExtentReports report;
//    public static ExtentTest test;
//    static ExtentReports extent = new ExtentReports();
//
//    public static SoftAssert softAssert = new SoftAssert();
//
//    @BeforeTest
//    public static void Setup() throws InterruptedException {
//        driver = new ChromeDriver();
//        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
//        extent.attachReporter(spark);
//        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
//        HomePage.click_hamburger_menu();
//        HomePage.click_orderProducts_link();
//    }
//
//    @Test
//    void ValidateTitles_OnlineProducts(){
//        test = extent.createTest(
//          "Validate Shoe Titles on Products Page",
//          "This test validates that the different Shoetypes are correct on Online Products Page"
//        );
//        ProductsPage.formalShoes_verifyTitle();
//        ProductsPage.sportsShoes_verifyTitle();
//        ProductsPage.sneakers_verifyTitle();
//        extent.flush();
//        softAssert.assertAll();
//    }
//
//    @Test
//    void ValidateFirstSportsShoes(){
//        test = extent.createTest(
//                "Validate First Shoe in Sports Shoes on Products Page",
//                "This test validates the first shoe on Online Products Page"
//        );
//        ProductsPage.sportsShoes_dropdown_click();
//        ProductsPage.sportsShoes_firstshoename_verify();
//    }
//
//    @Test
//    void ValidateFirstSneakers(){
//        test = extent.createTest(
//                "Validate First Shoe in Sneakers on Products Page",
//                "This test validates the first shoe on Online Products Page"
//        );
//        ProductsPage.sneakersShoes_dropdown_click();
//        ProductsPage.sneakers_firstshoename_verify();
//    }
//
//    @Test
//    void ValidateFirstFormalShoes(){
//        test = extent.createTest(
//                "Validate First Shoe in Formal Shoes on Products Page",
//                "This test validates the first shoe on Online Products Page"
//        );
//        ProductsPage.formalShoes_dropdown_click();
//        ProductsPage.formalShoes_firstshoename_verify();
//    }
//
//    @AfterSuite
//    public static void cleanup(){
//        extent.flush();
//    }
//}
