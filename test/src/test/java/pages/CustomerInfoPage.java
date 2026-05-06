package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerInfoPage extends BasePage {

    private final By firstNameLocator = By.xpath("//input[contains(@id, 'FirstName')]");
    private final By lastNameLocator = By.xpath("//input[contains(@id, 'LastName')]");
    private final By submitLocator = By.xpath("//button[contains(@type, 'submit') and contains(@name, 'save-info-button')]");

    public CustomerInfoPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitLocator));
    }

    public CustomerInfoPage typeFirstName(String firstName) {
        WebElement lastNameElement = waitVisibilityAndFindElement(firstNameLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(firstName);
        return this;
    }

    public CustomerInfoPage typeLastName(String lastName) {
        WebElement lastNameElement = waitVisibilityAndFindElement(lastNameLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
        return this;
    }

    public void submit() {
        waitVisibilityAndClickElement(submitLocator);
    }

    public String getFirstName() {
        return waitVisibilityAndFindElement(firstNameLocator).getAttribute("value");
    }

    public String getLastName() {
        return waitVisibilityAndFindElement(lastNameLocator).getAttribute("value");
    }
}
