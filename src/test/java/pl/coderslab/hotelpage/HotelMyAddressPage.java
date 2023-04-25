package pl.coderslab.hotelpage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelMyAddressPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/div/div/ul/li[11]/a[2]")
    private WebElement deleteBtn;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]")
    private WebElement noAddressAlert;

    @FindBy(css = "a[title= \"Add an address\"]")
    private WebElement addAddressBtn;

    @FindBy(className = "alert-warning")
    private WebElement alertWarning;

    @FindBy(css= "div.address")
    private List<WebElement> addressList;

    @FindBy(xpath = "//ul[contains(@class, \"last_item\")]/li/span[contains(@class,\"address_address1\")]" )
    private WebElement lastAddressStreet;

    @FindBy(xpath="//ul[contains(@class, \"last_item\")]/li/span[contains(@class,\"address_phone_mobile\")] ")
    private WebElement lastAddressPhone;

    @FindBy(xpath = "//ul[contains(@class, \"last_item\")]/li/span[contains(@class,\"address_city\")] ")
    private WebElement lastAddressCity;

    @FindBy(xpath = "//ul[contains(@class, \"last_item\")]/li[7]/span")
    private WebElement lastAddressZipcode;

    @FindBy(xpath = "//ul[contains(@class, \"last_item\")]/li/h3")
    private WebElement lastAddressAlias;

    @FindBy(xpath = "//ul[contains(@class, \"last_item\")]/li[contains(@class,\"address_update\")]/a[contains(@title,\"Delete\")]")
    private WebElement lastAddressDeleteBtn;


    public HotelMyAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDelete(){
        deleteBtn.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void addAnAddress(){
        addAddressBtn.click();
    }

    public String  getDeleteAlertText(){
        return noAddressAlert.getText();
    }

    public String getAlertText(){
        return alertWarning.getText();
    }
    public int getAddressCount(){
        return addressList.size();
    }

    public String getLastAddressStreet(){
        return lastAddressStreet.getText();
    }

    public String getLastAddressPhone(){
        return lastAddressPhone.getText();
    }

    public String getLastAddressCity(){
        return lastAddressCity.getText();
    }

    public String getLastAddressZipCode(){
        return lastAddressZipcode.getText();
    }

    public String getLastAddressAlias(){
        return lastAddressAlias.getText();
    }

    public void deleteLastAddress(){
        lastAddressDeleteBtn.click();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }





}
