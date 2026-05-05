package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import utils.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class TestBase {
    protected WebDriver driver;

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
