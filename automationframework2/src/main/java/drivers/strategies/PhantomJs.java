package drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJs implements DriverStrategy {
    public WebDriver setStrategy() {
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        WebDriver driver = new PhantomJSDriver(desiredCapabilities);

        return driver;
    }
}
