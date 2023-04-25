package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelQuickOrderPage {
    private final WebDriver driver;

    @FindBy (id = "total_price")
    private WebElement totalPrice;

    @FindBy (xpath = "//*[@id=\"cart_summary\"]/tbody/tr/td[6]/p")
    private WebElement checkInDate;

    @FindBy (xpath = "//*[@id=\"cart_summary\"]/tbody/tr/td[7]/p")
    private WebElement checkOutDate;

    @FindBy (name = "submitAdvPayment")
    private WebElement paymentOKBtn;

    @FindBy(id = "cgv")
    private  WebElement aggreCheckBox;

    @FindBy (css ="a.cheque")
    private WebElement paymentChequeChoice;



    public HotelQuickOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCheckInDate(){
        return checkInDate.getText();
    }

    public String getCheckOutDate(){
        return checkOutDate.getText();
    }

    public String getTotalPrice(){
        return totalPrice.getText();
    }

    public void clickAgree(){
        aggreCheckBox.click();
    }
    public HotelChequePaymentPage clickPayByCheck(){
        paymentChequeChoice.click();
        return new HotelChequePaymentPage(driver);
    }




}
