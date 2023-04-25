package pl.coderslab.hotelpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelAuthPage {

    private final WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement newUserEmailInput;

    @FindBy(name = "SubmitCreate")
    private WebElement createAnAccountBtn;

    @FindBy(id = "email")
    private WebElement loginEmailInput;

    @FindBy(name = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement loginBtn;

    @FindBy(css = ".alert-danger li")
    private List<WebElement> loginErrors;

    public HotelAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HotelRegisterPage enterNewUserEmailAndSubmit(String email) {
        newUserEmailInput.clear();
        newUserEmailInput.sendKeys(email);

        createAnAccountBtn.click();
        return new HotelRegisterPage(driver);
    }

    public HotelMyAccountPage loginAs(String login, String passwd){
        loginEmailInput.clear();
        loginEmailInput.sendKeys(login);

        passwordInput.clear();
        passwordInput.sendKeys(passwd);

        loginBtn.click();
        return new HotelMyAccountPage(driver);
    }

    public boolean isLoginErrorDislayed(){
        //isEmpty Sprawdza czy lista jest pusta(jeśli tak to True)
        return !loginErrors.isEmpty();
    }

}
