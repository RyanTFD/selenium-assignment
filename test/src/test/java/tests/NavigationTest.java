package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;

import pages.CustomerInfoPage;
import pages.FurniturePage;
import pages.HomePage;
import utils.ConfigReader;

public class NavigationTest extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test 
    public void homePageLoadsCorrectly() {
        HomePage homePage = new HomePage(this.driver).open();

        Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to our store."));
        Assert.assertEquals(homePage.getPageTitle(), "Shop");
    }

    @Test
    public void furnitureMenuDisplaysDropdownOnHover() {
        HomePage homePage = new HomePage(this.driver).open();
                
        homePage.hoverFurnitureMenu();
        
        Assert.assertTrue(homePage.waitForFurnitureMenuActive());
    }

    @Test
    public void clickingOnFurnitureShouldRedirectToFurniturePage() {
        HomePage homePage = new HomePage(this.driver).open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();
        
        Assert.assertEquals(furniturePage.getPageTitle(), "Shop. Furniture");
    }

    @Test
    public void userShouldBeAbleToChangePriceFilter() {
        HomePage homePage = new HomePage(this.driver).open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();
        
        furniturePage.clickPriceButton();

        Assert.assertTrue(furniturePage.isPriceButtonSelected());
    }

    @Test
    public void browserBackAndForwardShouldNavigateBetweenPagesCorrectly() {
        HomePage homePage = new HomePage(this.driver).open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();

        driver.navigate().back();

        Assert.assertEquals(homePage.getPageTitle(), "Shop");

        driver.navigate().forward();

        Assert.assertEquals(furniturePage.getPageTitle(), "Shop. Furniture");
    }

    @Test
    public void multiplePagesShouldHaveCorrectTitles() {
        String baseUrl = ConfigReader.get("baseUrl");

        String[] pages = {
            "/",
            "/furniture",
            "/contactus",
            "/aboutus",
            "/gift-cards"
        };

        String[] titles = {
            "Shop",
            "Shop. Furniture",
            "Shop. Contact Us",
            "Shop. About Us",
            "Shop. Gift cards"
        };

        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 0; i < pages.length; ++i) {
            driver.get(baseUrl + pages[i]);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            Assert.assertEquals(driver.getTitle(), titles[i]);
        }
    }

    @Test
    public void clickingOnMyAccountShouldRedirectToCustomerInfoPage() {
        HomePage loggedInHomePage = loginAsValidUser();
        
        CustomerInfoPage contactPage = loggedInHomePage.openCustomerInfo();
        
        Assert.assertEquals(contactPage.getPageTitle(), "Shop. Account");
    }
}