package pl.coderslab;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearchSteps {

    private WebDriver driver;

    @Given("an open browser with google.com")
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
        driver.get("http://www.google.com");
    }

    @When("^a keyword (.*) is entered in input field$")
    public void enterKeyword(String keyword) {
        driver.findElement(By.id("L2AGLb")).click();
        // Znajdź element wprowadzania tekstu na podstawie jego nazwy
        WebElement element = driver.findElement(By.name("q"));
        // Wyczyść tekst zapisany w elemencie
        element.clear();
        // Wpisz informacje do wyszukania
        element.sendKeys(keyword);
        // Prześlij formularz
        element.submit();
    }

    @Then("^the first one should contain (.*)$")
    public void theFirstOneShouldContainKeyword(String expectedText) {
        String firstResult = driver.findElement(By.cssSelector("h3.LC20lb.MBeuO.DKV0Md")).getText().toLowerCase();
        //sprawdza czy w pierwszym rezultacie istnieje fraza wyznaczona do wyszukiwania
        Assertions.assertTrue(firstResult.contains(expectedText));
    }

    @And("close browser")
    public void closeBrowser() {
        driver.quit();
    }

}
