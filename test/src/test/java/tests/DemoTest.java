package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import pages.MainPage;
import utils.ConfigReader;

public class DemoTest {
    private WebDriver driver;

    @Before
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
            
        driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), caps);
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getWelcomeText().contains("Welcome to our store."));

        /*
        WebElement usernameElement = waitVisibilityAndFindElement(usernameLocator);
        usernameElement.sendKeys("tomsmith");
        
        WebElement passwordElement = waitVisibilityAndFindElement(passwordLocator);
        passwordElement.sendKeys("SuperSecretPassword!");
        
        WebElement loginButtonElement = waitVisibilityAndFindElement(loginButtonLocator);
        loginButtonElement.click();
        
        WebElement flashLoginElement = waitVisibilityAndFindElement(flashLocator);
        Assert.assertTrue(flashLoginElement.getText().contains("You logged into a secure area!"));
        
        WebElement logoutButtonElement = waitVisibilityAndFindElement(logoutButtonLocator);
        logoutButtonElement.click();
        
        this.wait.until(ExpectedConditions.urlContains("/login"));
        
        WebElement flashLogoutElement = waitVisibilityAndFindElement(flashLocator);        
        Assert.assertTrue(flashLogoutElement.getText().contains("You logged out of the secure area!"));
        */
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}