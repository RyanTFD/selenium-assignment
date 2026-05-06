package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import pages.FurniturePage;
import pages.HomePage;

public class NavigationTest extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test 
    public void homePageLoadsCorrectly() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();

        Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to our store."));
        Assert.assertEquals(homePage.getPageTitle(), "Shop");
    }

    @Test
    public void furnitureMenuDisplaysDropdownOnHover() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();
                
        homePage.hoverFurnitureMenu();
        
        Assert.assertTrue(homePage.waitForFurnitureMenuActive());
    }

    @Test
    public void clickingOnFurnitureShouldRedirectToFurniturePage() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();
        
        Assert.assertEquals(furniturePage.getPageTitle(), "Shop. Furniture");
    }

    @Test
    public void userShouldBeAbleToChangePriceFilter() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();
        
        furniturePage.clickPriceButton();

        Assert.assertTrue(furniturePage.isPriceButtonSelected());
    }

    @Test
    public void browserBackAndForwardShouldNavigateBetweenPagesCorrectly() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();
                
        FurniturePage furniturePage = homePage.clickFurnitureMenu();

        driver.navigate().back();

        Assert.assertEquals(homePage.getPageTitle(), "Shop");

        driver.navigate().forward();

        Assert.assertEquals(furniturePage.getPageTitle(), "Shop. Furniture");
    }
}