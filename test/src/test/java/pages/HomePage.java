package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.ConfigReader;

public class HomePage extends BasePage {

    private final By welcomeLocator = By.xpath("//h1[contains(@class, 'h2')]");
    private final By loginLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'login')]");
    private final By customerInfoLocator = By.xpath("//div[@class='dropdown']//a[contains(@href,'/customer/info') and contains(@class, 'menubar-link')]");
    private final By logoutLocator = By.xpath("//a[contains(@href,'/logout')]");
    private final By furnitureNavLocator = By.xpath("//a[contains(@class, 'nav-link') and contains(@href,'/furniture')]/ancestor::li");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public HomePage open() {
        this.driver.get(ConfigReader.get("baseUrl"));
        return waitUntilLoaded();
    }

    public HomePage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeLocator));
        return this;
    }

    public String getWelcomeText() {
        WebElement welcomeElement = waitVisibilityAndFindElement(welcomeLocator);
        return welcomeElement.getText();
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

    public FurniturePage clickFurnitureMenu() {
        waitVisibilityAndClickElement(furnitureNavLocator);
        return new FurniturePage(driver);
    }

    public void hoverFurnitureMenu() {
        hover(furnitureNavLocator);
    }

    public boolean waitForFurnitureMenuActive() {
        return wait.until(ExpectedConditions.attributeContains(furnitureNavLocator, "class", "active"));
    }

    public HomePage logout() {
        openUserMenu();
        clickLogout();
        return new HomePage(this.driver).waitUntilLoaded();
    }
}
