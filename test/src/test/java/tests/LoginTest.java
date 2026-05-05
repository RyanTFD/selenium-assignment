package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import pages.LoginPage;
import pages.HomePage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {
        HomePage homePage = new HomePage(this.driver);
        Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to our store."));
        Assert.assertEquals(homePage.getPageTitle(), "Shop");
        LoginPage loginPage = homePage.clickLoginButton();
        Assert.assertEquals(loginPage.getPageTitle(), "Shop. Login");
        homePage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @Test
    public void testScreenshot() {
        HomePage homePage = new HomePage(this.driver);
        Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to our store."));
        Assert.fail();
    }
}