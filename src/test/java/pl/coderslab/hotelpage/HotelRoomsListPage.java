package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelRoomsListPage {
    private final WebDriver driver;

    @FindBy (id ="hotel_cat_name")
    private WebElement searchHotelName;

    @FindBy (name = "check_in_time")
    private WebElement checkInSearchInput;

    @FindBy (name = "check_out_time")
    private WebElement checkOutSearchInput;

    @FindBy (className = "room_cont")
    private List<WebElement> roomsList;

    @FindBy (css = "a.ajax_add_to_cart_button")
    private WebElement bookNowBtb;

    @FindBy (css = "div.button-container > a")
    private WebElement proceedToCheckoutBnt;

    @FindBy (xpath = "//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/div[4]/span[1]")
    private WebElement roomPrice;

    public HotelRoomsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSearchHotelName(){
        return searchHotelName.getText();
    }

    public String getSearchCheckInDate(){
        return checkInSearchInput.getAttribute("value");
    }

    public String getSearchCheckOutDate(){
        return checkOutSearchInput.getAttribute("value");
    }

    public Integer getRoomsCount(){
        return roomsList.size();
    }

    public String getRoomPrice(){
        return roomPrice.getText();
    }
    public void clickBookNow(){
        bookNowBtb.click();
    }
    public HotelAddressToOrderPage clickProceedToCheckout(){
        proceedToCheckoutBnt.click();
        return new HotelAddressToOrderPage(driver);
    }

}
