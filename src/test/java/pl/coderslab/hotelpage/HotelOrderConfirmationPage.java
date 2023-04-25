package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelOrderConfirmationPage {
    private final WebDriver driver;

    @FindBy (css ="p.alert-success")
    private WebElement successAlert;

    public HotelOrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSuccessAlert(){
        return successAlert.getText();
    }


}
