package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelYourAddressPage {

    private final WebDriver driver;

    @FindBy(id ="address1")
    private WebElement addressInput;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(name = "postcode")
    private WebElement postcodeInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "phone_mobile")
    private WebElement phoneNumberInput;

    @FindBy (id="alias")
    private WebElement aliasInput;

    @FindBy (id = "submitAddress")
    private WebElement submitBtn;

    public HotelYourAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HotelMyAddressPage enterNewAddressDate(String alias, String address, String city, String postcode,
                                                  String phoneNumber){
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.clear();
        addressInput.sendKeys(address);

        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);

        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);

        cityInput.clear();
        cityInput.sendKeys(city);

        submitBtn.click();
        return new HotelMyAddressPage(driver);
    }




}
