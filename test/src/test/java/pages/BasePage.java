package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitVisibilityAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    protected void waitVisibilityAndClickElement(By locator) {
        WebElement element = waitVisibilityAndFindElement(locator);
        element.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
