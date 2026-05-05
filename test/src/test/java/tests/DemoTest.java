package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import pages.MainPage;

public class DemoTest extends TestBase {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getWelcomeText().contains("Welcome to our store."));
    }

    @Test
    public void testScreenshot() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getWelcomeText().contains("Welcome to our store."));
        Assert.fail();
    }
}