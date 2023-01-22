package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu-item-440")
    private WebElement signInButton;

    @FindBy(id = "menu-item-430")
    private WebElement shopButton;

    @FindBy(id = "menu-item-443")
    private WebElement username;

    @FindBy(id = "menu-item-45")
    private WebElement storeButton;

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;

    @FindBy(css = "#woocommerce_product_search-4 > form > button")
    private WebElement searchButton;

    @FindBy(css = "#main > div > ul > li.ast-col-sm-12.ast-article-post.astra-woo-hover-swap.product.type-product.post-2726.status-publish.first.instock.product_cat-men.product_cat-shoes.has-post-thumbnail.featured.shipping-taxable.purchasable.product-type-simple > div.astra-shop-summary-wrap > a > h2")
    private WebElement searchResults;

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void clickShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        shopButton.click();
    }

    public String getUsername() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(username));
        return username.getText();
    }

    public void clickStore() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(storeButton));
        storeButton.click();
    }

    public Boolean searchElement(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(value);
        searchButton.click();
        try {
            if(searchResults.isEnabled())
                return true;
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

}
