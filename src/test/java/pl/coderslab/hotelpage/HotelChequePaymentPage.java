package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelChequePaymentPage {
    private final WebDriver driver;

    @FindBy (css ="button.btn.pull-right.htl-reservation-form-btn-default")
    private WebElement confirmOrderBtn;

    public HotelChequePaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HotelOrderConfirmationPage clickConfrim(){
        confirmOrderBtn.click();
        return new HotelOrderConfirmationPage(driver);
    }


}
