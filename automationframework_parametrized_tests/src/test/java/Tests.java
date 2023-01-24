import automation.drivers.DriverSingleton;
import automation.pages.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import automation.pages.*;
import automation.utils.Constants;
import automation.utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;

public class Tests {

    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static CheckoutPage checkoutPage;
    static ShopPage shopPage;
    static CartPage cartPage;

    @BeforeClass
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }

    @Test
    public void testingAuthentication(){
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUsername());
    }

    @Test
    public void testingAddingThingsToCart(){
        driver.get(Constants.URL);
        shopPage.addElementToCart();
        assertEquals(Constants.CART_QUANTITY, shopPage.getNumberOfProducts());
    }

    @Test
    public void testingTheFullBuyingProcess(){
        driver.get(Constants.URL);
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        assertEquals("Order received", checkoutPage.getOrderStatus());
    }

    @AfterClass
    public static void closeObjects(){
        driver.close();
    }
}
