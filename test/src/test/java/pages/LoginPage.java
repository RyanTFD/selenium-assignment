package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    
    private final By usernameLocator = By.xpath("//input[contains(@id,'UsernameOrEmail')]");
    private final By passwordLocator = By.xpath("//input[contains(@id,'Password')]");
    private final By submitLocator = By.xpath("//button[contains(@type, 'submit') and contains(@class,'btn-login')]");
    private final By loginErrorLocator = By.xpath("//div[contains(@class,'validation-summary-errors')]//span[contains(text(), 'Login was unsuccessful.')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
    }

    public LoginPage typeUsername(String username) {
        waitVisibilityAndFindElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        waitVisibilityAndFindElement(passwordLocator).sendKeys(password);
        return this;
    }

    public void submit() {
        waitVisibilityAndClickElement(submitLocator);
    }

    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
