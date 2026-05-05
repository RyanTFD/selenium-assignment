package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ConfigReader;

public class HomePage extends BasePage {

    private final By loginLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'login')]");
    private final By customerInfoLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'/customer/info') and contains(@class, 'menubar-link')]");
    private final By logoutLocator = By.xpath("//a[contains(@href,'/logout')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void open() {
        this.driver.get(ConfigReader.get("baseUrl"));
    }

    public LoginPage clickLogin() {
        waitVisibilityAndClickElement(loginLocator);
        return new LoginPage(driver);
    }
    
    public boolean isUserLoggedIn() {
        return isElementPresent(customerInfoLocator);
    }

    public void openUserMenu() {
        waitVisibilityAndClickElement(customerInfoLocator);
    }

    public void clickLogout() {
        waitVisibilityAndClickElement(logoutLocator);
    }

    public HomePage logout() {
        openUserMenu();
        clickLogout();
        return new HomePage(this.driver);
    }
}
