package pl.coderslab.hotelSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.coderslab.hotelpage.*;

import java.time.Duration;

public class HotelEditAddressSteps {
    WebDriver driver;
    int addressCount;

    @Given("I'm on the hotel main page")
    public void userIsLoggedInToCodersLabShop() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();


        driver.get("https://hotel-testlab.coderslab.pl/en/");
    }

    @When("I go to login page")
    public void iGoToLoginPage() {
        HotelMainPage mainPage = new HotelMainPage(driver);
        mainPage.signIn();
    }


    @And("I login using {string} and {string}")
    public void iLoginUsingAnd(String email, String password) {
        HotelAuthPage authPage = new HotelAuthPage(driver);
        authPage.loginAs(email, password);
    }

    @And("I go to my addresses page")
    public void iGoToMyAddressesPage() {
        HotelMyAccountPage myAccountPage = new HotelMyAccountPage(driver);
        myAccountPage.getMyAddress();
    }

    @And("I add new address")
    public void iAddNewAddress() {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        myAddressPage.addAnAddress();
    }

    @And("I enter new address {string}, {string}, {string}, {string} , {string}")
    public void iEnterNewAddress(String firstAddress, String street, String zipCode, String city, String phoneNumber) {
        HotelYourAddressPage yourAddressPage = new HotelYourAddressPage(driver);
        yourAddressPage.enterNewAddressDate(firstAddress, street, city, zipCode, phoneNumber);
    }

    @And("I can see there is no addresses")
    public void iCanSeeThereIsNoAddresses() {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        addressCount = myAddressPage.getAddressCount();
        Assertions.assertEquals("No addresses are available. Add a new address",myAddressPage.getAlertText());
    }

    @Then("I can see new address")
    public void iCanSeeNewAddress() {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        Assertions.assertEquals(addressCount+1,myAddressPage.getAddressCount());
        addressCount = myAddressPage.getAddressCount();
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.quit();
    }

    @And("I verify created address {string}, {string}, {string}, {string} , {string}")
    public void iVerifyCreatedAddress(String alias, String street, String zipCode, String city, String phoneNumber) {

        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        Assertions.assertEquals(alias.toUpperCase(), myAddressPage.getLastAddressAlias());
        Assertions.assertEquals(street,myAddressPage.getLastAddressStreet());
        Assertions.assertEquals(zipCode, myAddressPage.getLastAddressZipCode());
        Assertions.assertEquals(city, myAddressPage.getLastAddressCity());
        Assertions.assertEquals(phoneNumber, myAddressPage.getLastAddressPhone());


    }

    @And("I remove the address")
    public void iRemoveTheAddress() {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        myAddressPage.deleteLastAddress();
        myAddressPage.acceptAlert();
    }

    @And("I can see the address was removed")
    public void iCanSeeTheAddressWasRemoved() {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        Assertions.assertEquals(addressCount-1,myAddressPage.getAddressCount());
    }
}
