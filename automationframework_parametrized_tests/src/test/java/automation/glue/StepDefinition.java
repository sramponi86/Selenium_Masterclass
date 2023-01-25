package automation.glue;

import automation.config.RunFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.CheckoutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utils.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
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
    ExtentTest test;
    static ExtentReports report = new ExtentReports("report/TestReport.html");

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homepage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        TestCases[] tests = TestCases.values();
        test = report.startTest(tests[Utils.testCount].getTestName());
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
        //Utils.testCount++;
    }

    @Given("I go to the website")
    public void navigate_to_website(){
        Log.info("INFO: Navigating to the website");
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        test.log(LogStatus.PASS,"Navigating to the website");
    }

    @When("I click on the signIn button")
    public void click_on_singin(){
        Log.info("INFO: Clicking on the Sign In button");
        homepage.clickSignIn();
        test.log(LogStatus.PASS, "Click on the signin");
    }

    @And("I specify my credential and click login")
    public void insert_credentials(){
        Log.info("INFO: Insert correct credentials");
        signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
        test.log(LogStatus.PASS,"Perform the login");
    }

    @Then("I can login into the website")
    public void check_login(){
        Log.info("INFO: Verify the login procedure");
        assertEquals(homepage.getUsername(),"Hello, Laurentiu");
        test.log(LogStatus.PASS,"Verify the correct login");
        //configurationProperties.getUsername()
    }

    @After
    public void closeObjects(){
        //Log.endTest(test);
        report.endTest(test);
        report.flush();
        DriverSingleton.closeObjectInstance();
    }
}
