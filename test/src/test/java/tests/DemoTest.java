package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import pages.MainPage;
import utils.ConfigReader;

public class DemoTest extends TestBase {

    @BeforeMethod
    public void setup() throws MalformedURLException {

        Capabilities caps;
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        switch (ConfigReader.get("browser")) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                caps = chromeOptions;
                break;

            case "firefox":
            default:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                caps = firefoxOptions;
                break;
        }

        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), caps);
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