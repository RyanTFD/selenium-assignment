package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ConfigReader;

public class HomePage extends PageBase {

    private final By welcomeLocator = By.xpath("//h1[contains(@class, 'h2')]");
    private final By loginLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'login')]");
    private final By customerInfoLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'/customer/info') and contains(@class, 'menubar-link')]");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(ConfigReader.get("baseUrl"));
    }

    public String getWelcomeText() {
        WebElement welcomeElement = waitVisibilityAndFindElement(welcomeLocator);
        return welcomeElement.getText();
    }

    public LoginPage clickLoginButton() {
        WebElement loginElement = waitVisibilityAndFindElement(loginLocator);
        loginElement.click();
        return new LoginPage(driver);
    }
    
    public boolean isUserLoggedIn() {
        return isElementPresent(customerInfoLocator);
    }
}
