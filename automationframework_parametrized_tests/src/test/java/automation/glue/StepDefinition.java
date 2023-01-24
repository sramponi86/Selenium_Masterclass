package automation.glue;

import automation.config.RunFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.CheckoutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@ContextConfiguration(classes = RunFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private HomePage homepage;
    private SignInPage signInPage;
    private CheckoutPage checkoutPage;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homepage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }

    @Given("I go to the website")
    public void navigate_to_website(){
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("I click on the signIn button")
    public void click_on_singin(){
        homepage.clickSignIn();
    }

    @And("I specify my credential and click login")
    public void insert_credentials(){
        signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
    }

    @Then("I can login into the website")
    public void check_login(){
        assertEquals(homepage.getUsername(),"Hello, Laurentiu");
        //configurationProperties.getUsername()
    }
}
