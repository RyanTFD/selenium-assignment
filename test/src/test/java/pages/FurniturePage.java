package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FurniturePage extends BasePage {

    private final By priceRadioLocator = By.xpath("//input[contains(@type, 'radio') and contains(@value, '~250')]");

    public FurniturePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceRadioLocator));
    }

    public void clickPriceButton() {
        clickWithJs(waitVisibilityAndFindElement(priceRadioLocator));
    }

    public boolean isPriceButtonSelected() {
        WebElement radio = waitVisibilityAndFindElement(priceRadioLocator);
        return radio.isSelected();
    }
}
