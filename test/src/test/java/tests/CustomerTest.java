package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import pages.CustomerInfoPage;
import pages.HomePage;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;

public class CustomerTest extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test
    public void userCanChangeFirstAndLastName() {
        HomePage loggedInHomePage = loginAsValidUser();

        CustomerInfoPage contactPage = loggedInHomePage.openCustomerInfo();

        contactPage.typeFirstName("John")
                   .typeLastName("Smith")
                   .submit();

        Assert.assertEquals(contactPage.getFirstName(), "John");
        Assert.assertEquals(contactPage.getLastName(), "Smith");
    }
}