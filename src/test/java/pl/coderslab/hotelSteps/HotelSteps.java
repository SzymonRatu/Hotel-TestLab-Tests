package pl.coderslab.hotelSteps;

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

import java.time.Duration;

public class HotelSteps {
    private WebDriver driver;

    @Given("User is on hotel main page")
    public void openHotelMainPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get("https://hotel-testlab.coderslab.pl/en/");
    }


    @When("User sign in")
    public void userSignIn() {
        driver.findElement(By.cssSelector("span.hide_xs")).click();
    }

    @And("enter email that is not already taken on authentication page")
    public void enterEmailThatIsNotAlreadyTakenOnAuthenticationPage() {
        String name = "szy" + System.currentTimeMillis() + "@test.com";
        driver.findElement(By.name("email_create")).sendKeys(name);
        driver.findElement(By.id("SubmitCreate")).click();
        //Dodać asercje do sprawdzenia czy email juz nie istnieje
    }

    /*
        @And("enter name {word}, surname {word} and password {word}")
        public void enterNameSurnameAndPassword(String name, String lastName, String passwd) {
            driver.findElement(By.id("customer_firstname")).sendKeys(name);
            driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
            driver.findElement(By.id("passwd")).sendKeys(passwd);
            driver.findElement(By.id("submitAccount")).click();
        }
     */

    @And("enter name {string}, surname {string} and password {string}")
    public void enterNameSurnameAndPassword(String name, String lastName, String passwd) {
        driver.findElement(By.id("customer_firstname")).sendKeys(name);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
        driver.findElement(By.id("passwd")).sendKeys(passwd);
        driver.findElement(By.id("submitAccount")).click();
    }

    @Then("User sees success message with text {string}")
    public void userSeesSuccessMessageWithText(String text) {
        WebElement alert = driver.findElement(By.cssSelector(".alert.alert-success"));
        Assertions.assertTrue(alert.isDisplayed());
        Assertions.assertEquals(text, alert.getText());
    }

    @And("close hotel browser")
    public void closeHotelBrowser() {
        driver.quit();
    }
}
