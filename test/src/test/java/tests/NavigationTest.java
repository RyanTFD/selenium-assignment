package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

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

}