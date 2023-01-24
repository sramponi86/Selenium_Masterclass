package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.utils.Utils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "body > div.xoo-el-container.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div.scroll-content > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-aff-group.xoo-el-username_cont > div > input")
    private WebElement signInEmail;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div.scroll-content > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-aff-group.xoo-el-password_cont > div > input")
    private WebElement password;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div.scroll-content > div > div > div.xoo-el-section.xoo-el-active > div > form > button")
    private WebElement signInButton;

    public void logIn(String email, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInEmail.sendKeys(email);
        this.password.sendKeys(Utils.decode64(password));
        signInButton.click();
    }
}
