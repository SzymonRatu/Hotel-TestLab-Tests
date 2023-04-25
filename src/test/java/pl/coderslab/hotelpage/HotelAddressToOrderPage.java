package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelAddressToOrderPage {

    private final WebDriver driver;

    @FindBy (id = "firstname")
    private WebElement firstNameInput;

    @FindBy (id = "lastname")
    private WebElement lastNameInput;

    @FindBy (id = "address1")
    private WebElement address1Input;

    @FindBy (id = "postcode")
    private WebElement postcodeInput;

    @FindBy (id = "city")
    private WebElement cityInput;

    /*
    @FindBy (id = "id_country")
    private WebElement countrySelector;

    @FindBy (css = "id_country > option")
    private WebElement countryOptions;
     */

    @FindBy (id = "phone")
    private WebElement phoneInput;

    @FindBy (id = "submitAddress")
    private WebElement submitBtn;


    public HotelAddressToOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void provideRequireUserAddressData(String firstname, String lastname, String address, String zipCode,
                                              String city, String phoneNumber){
        firstNameInput.clear();
        firstNameInput.sendKeys(firstname);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastname);

        address1Input.clear();
        address1Input.sendKeys(address);

        postcodeInput.clear();
        postcodeInput.sendKeys(zipCode);

        cityInput.clear();
        cityInput.sendKeys(city);

        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    public HotelQuickOrderPage clickSaveBtn(){
        submitBtn.click();
        return new HotelQuickOrderPage(driver);
    }



}
