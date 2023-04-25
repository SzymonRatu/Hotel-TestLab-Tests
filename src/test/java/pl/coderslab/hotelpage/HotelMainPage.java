package pl.coderslab.hotelpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelMainPage {

    private final WebDriver driver;

    @FindBy (name = "check_in_time")
    private WebElement checkInDateInput;

    @FindBy(name = "check_out_time")
    private WebElement checkOutDateInput;

    @FindBy(id = "search_room_submit")
    private WebElement searchRoomSubmitBtn;

    @FindBy(css = "button.header-rmsearch-input")
    private WebElement selectHotelBtn;

    @FindBy(css = ".hotel_dropdown_ul li")
    private List<WebElement> hotelDropdownList;

    public HotelMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signIn() {
        driver.findElement(By.className("user_login")).click();
    }

    public HotelAuthPage singInWithPage() {
        signIn();
        return new HotelAuthPage(driver);
    }

    public void enterCheckInDate(String date){
        checkInDateInput.clear();
        checkInDateInput.sendKeys(date);
    }

    public void enterCheckOutDate(String date){
        checkOutDateInput.clear();
        checkOutDateInput.sendKeys(date);
    }

    public String selectFirstHotel(){
        selectHotelBtn.click();
        WebElement hotel = hotelDropdownList.get(0);
        String hotelName = hotel.getText();
        hotel.click();
        return hotelName;
    }

    public HotelRoomsListPage searchRooms(){
        searchRoomSubmitBtn.click();
        return new HotelRoomsListPage(driver);
    }
}
