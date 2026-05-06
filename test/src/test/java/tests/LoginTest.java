package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import pages.LoginPage;
import utils.RandomDataGenerator;
import pages.HomePage;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), createCapabilities());
        this.driver.manage().window().maximize();
    }

    @Test
    public void userCannotLoginWithRandomUsernameAndPassword() {
        HomePage homePage = new HomePage(this.driver).open();

        LoginPage loginPage = homePage.clickLogin();

        String username = RandomDataGenerator.randomUsername();
        String password = RandomDataGenerator.randomPassword();

        loginPage.typeUsername(username)
                 .typePassword(password)
                 .submit();

        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void userCannotLoginWithRandomEmailAndPassword() {
        HomePage homePage = new HomePage(this.driver).open();

        LoginPage loginPage = homePage.clickLogin();

        String username = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.randomPassword();

        loginPage.typeUsername(username)
                 .typePassword(password)
                 .submit();

        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void loginShouldHaveCorrectTitle() {
        HomePage homePage = new HomePage(this.driver).open();

        LoginPage loginPage = homePage.clickLogin();

        Assert.assertEquals(loginPage.getPageTitle(), "Shop. Login");
    }

    @Test
    public void userCannotLoginWithInvalidCredentials() {
        HomePage homePage = new HomePage(this.driver).open();

        LoginPage loginPage = homePage.clickLogin();

        loginPage.typeUsername("invalid")
                 .typePassword("invalid")
                 .submit();

        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void userCanLoginSuccessfully() {
        HomePage loggedInHomePage = loginAsValidUser();

        Assert.assertTrue(loggedInHomePage.isUserLoggedIn());
    }

    @Test(dependsOnMethods = { "userCanLoginSuccessfully" })
    public void userCanLogoutSuccessfully() {
        HomePage homePage = loginAsValidUser().logout();

        Assert.assertFalse(homePage.isUserLoggedIn());
    }
}