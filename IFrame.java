import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_iframe.asp");

        String title = driver.switchTo().frame(0).findElement(By.cssSelector("#main > h1")).getText();
        System.out.println(title);

        WebElement iframe = driver.findElement(By.cssSelector("#main > div:nth-child(7) > iframe"));

        driver.switchTo().frame(iframe).findElement(By.cssSelector("#topnav > div > div.w3-bar.w3-left > a:nth-child(5)")).click();

        driver.close();
    }
}
