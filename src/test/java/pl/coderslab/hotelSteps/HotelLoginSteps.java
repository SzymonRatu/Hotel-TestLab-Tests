package pl.coderslab.hotelSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.coderslab.hotelpage.HotelAuthPage;
import pl.coderslab.hotelpage.HotelMyAccountPage;

public class HotelLoginSteps {
    private WebDriver driver;

    @Given("I'm on hotel main page")
    public void openGoogleSearch() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://hotel-testlab.coderslab.pl/en/");
        // Klika na przycisk Sign in
        driver.findElement(By.cssSelector("span.hide_xs")).click();
    }

    @When("^I enter email address (.*) and password (.*)$")
    public void enterKeyword(String email, String passwd) {
        HotelAuthPage authPage = new HotelAuthPage(driver);
        // Logowanie na podany adres i hasło
        authPage.loginAs(email, passwd);
        Assertions.assertFalse(authPage.isLoginErrorDislayed(), "Login error shouldn't be displayed!");
    }

    @Then("^User sees name as (.*)")
    public void loggedIn(String name) {
        HotelMyAccountPage myAccountPage = new HotelMyAccountPage(driver);
        Assertions.assertEquals(name, myAccountPage.getUserName());
    }

    @And("close login browser")
    public void closeBrowser() {
        driver.quit();
    }

}
