package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ConfigReader;

public class MainPage extends PageBase {

    private final By welcomeLocator = By.xpath("//h1[contains(@class, 'h2')]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get(ConfigReader.get("baseUrl"));
    }

    public String getWelcomeText() {
        WebElement welcomeElement = waitVisibilityAndFindElement(welcomeLocator);
        return welcomeElement.getText();
    }
}
