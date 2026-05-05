package tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class BaseTest {
    protected WebDriver driver;

    protected Capabilities createCapabilities() {

        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        String browser = ConfigReader.get("browser");

        switch (browser) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--window-size=1920,1080");

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                return chromeOptions;

            case "firefox":
            default:
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                return firefoxOptions;
        }
    }
    
    protected HomePage loginAsValidUser() {
        HomePage homePage = new HomePage(this.driver);
        homePage.open();

        LoginPage loginPage = homePage.clickLogin();

        loginPage.typeUsername(ConfigReader.get("username"))
                 .typePassword(ConfigReader.get("password"))
                 .submit();
        return new HomePage(this.driver);
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
