package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {
    
    private final By usernameLocator = By.xpath("//input[contains(@id,'UsernameOrEmail')]");
    private final By passwordLocator = By.xpath("//input[contains(@id,'Password')]");
    private final By submitLocator = By.xpath("//button[contains(@type, 'submit') and contains(@class,'btn-login')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
    }

    public HomePage login(String username, String password) {
        WebElement usernameElement = waitVisibilityAndFindElement(usernameLocator);
        usernameElement.sendKeys(username);
        WebElement passwordElement = waitVisibilityAndFindElement(passwordLocator);
        passwordElement.sendKeys(password);
        WebElement submitElement = waitVisibilityAndFindElement(submitLocator);
        submitElement.click();
        return new HomePage(this.driver);
    }
}
