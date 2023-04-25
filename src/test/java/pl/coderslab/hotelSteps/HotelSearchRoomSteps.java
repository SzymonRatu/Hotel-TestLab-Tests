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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelSearchRoomSteps {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    String checkInDate;
    String checkOutDate;
    String selectedHotelName;
    String price;


    public WebDriver driver;
    @Given("User is logged on {string} with password {string}")
    public void userLoggedin(String email, String passwd) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get("https://hotel-testlab.coderslab.pl/en/");

        // Przechodzę na stronę główna i klikam sing in
        HotelMainPage mainPage = new HotelMainPage(driver);
        HotelAuthPage authPage = mainPage.singInWithPage();

        //Zalogowanie się na utworzonego urzytkownika
        authPage.loginAs(email, passwd);
        Assertions.assertFalse(authPage.isLoginErrorDislayed(),"Login error shouldn't be displayed!");
    }

    @And("is on hotel main page")
    public void isOnHotelMainPage() {
        // wracam na stronę główną
        HotelMyAccountPage myAccountPage = new HotelMyAccountPage(driver);
        myAccountPage.goToHomePage();
    }

    @When("User select a hotel and date")
    public void userSelectAHotelAndDate() {
        HotelMainPage mainPage = new HotelMainPage(driver);
        //Wybranie potrzebych opcji
        this.selectedHotelName = mainPage.selectFirstHotel();
        //Pobranie aktualnej daty i przemiana na przyjmowany przez stronę format!
        this.checkInDate = LocalDate.now().format(dateTimeFormatter);
        mainPage.enterCheckInDate(checkInDate);
        this.checkOutDate = LocalDate.now().plusDays(1).format(dateTimeFormatter);
        mainPage.enterCheckOutDate(checkOutDate);
        mainPage.searchRooms();
    }

    @Then("User sees available hotel rooms")
    public void userSeesAvaliableHotelsRoom() {
        // porównanie asercji w celu sprawdzenia poprawności danych
        HotelRoomsListPage hotelRoomsListPage = new HotelRoomsListPage(driver);
        assertTrue(hotelRoomsListPage.getRoomsCount() > 0);
        Assertions.assertEquals(this.checkInDate, hotelRoomsListPage.getSearchCheckInDate());
        Assertions.assertEquals(this.checkOutDate, hotelRoomsListPage.getSearchCheckOutDate());
        Assertions.assertEquals(this.selectedHotelName, hotelRoomsListPage.getSearchHotelName());
    }

    @And("close search browser")
    public void closeSearchBrowser() {
        driver.quit();
    }

    @When("User add to cart room")
    public void userAddToCartRoom() {
        HotelRoomsListPage hotelRoomsListPage = new HotelRoomsListPage(driver);
        this.price = hotelRoomsListPage.getRoomPrice();
        //Wybranie pierwszego wyświetlonego hotelu i przejście do koszyka
        hotelRoomsListPage.clickBookNow();
        hotelRoomsListPage.clickProceedToCheckout();
    }

    @And("Buy it")
    public void buyIt() {
        HotelAddressToOrderPage addressToOrderPage = new HotelAddressToOrderPage(driver);
        addressToOrderPage.provideRequireUserAddressData("John", "Doe", "Armii Krajowej 2",
                                                        "00-000","Warsaw","123321222");
        HotelQuickOrderPage quickOrderPage = addressToOrderPage.clickSaveBtn();
        //Sprawdzenie poprawnosci zamownienia
        Assertions.assertEquals(price, quickOrderPage.getTotalPrice());
        Assertions.assertEquals(checkInDate, quickOrderPage.getCheckInDate());
        Assertions.assertEquals(checkOutDate, quickOrderPage.getCheckOutDate());

        //Dopełnienie zamówienia
        quickOrderPage.clickAgree();
        HotelChequePaymentPage chequePaymentPage = quickOrderPage.clickPayByCheck();
        //Potwierdzenie zamownienia
        HotelOrderConfirmationPage orderConfirmationPage = chequePaymentPage.clickConfrim();
    }

    @Then("User sees a successful alert {string}")
    public void userSeesASuccessfulBuyItAlert(String text) {
        HotelOrderConfirmationPage orderConfirmationPage = new HotelOrderConfirmationPage(driver);
        Assertions.assertEquals(text, orderConfirmationPage.getSuccessAlert());
    }

    @And("User select a hotel and date on main page")
    public void userSelectAHotelAndDateOnMainPage() {
        HotelMyAccountPage myAccountPage = new HotelMyAccountPage(driver);
        HotelMainPage mainPage = myAccountPage.goToHomePage();
        //Wybranie potrzebych opcji
        this.selectedHotelName = mainPage.selectFirstHotel();
        //Pobranie aktualnej daty i przemiana na przyjmowany przez stronę format!
        this.checkInDate = LocalDate.now().format(dateTimeFormatter);
        mainPage.enterCheckInDate(checkInDate);
        this.checkOutDate = LocalDate.now().plusDays(1).format(dateTimeFormatter);
        mainPage.enterCheckOutDate(checkOutDate);
        mainPage.searchRooms();
    }

    @When("User delete a address")
    public void userDelateAAddress() {
        HotelMyAccountPage myAccountPage = new HotelMyAccountPage(driver);
        HotelMyAddressPage myAddressPage = myAccountPage.clickMyAddress();
        myAddressPage.clickDelete();
    }

    @Then("User sees alert {string}")
    public void userSeesAlert(String text) {
        HotelMyAddressPage myAddressPage = new HotelMyAddressPage(driver);
        Assertions.assertTrue(myAddressPage.getDeleteAlertText().contains(text));
    }
}
