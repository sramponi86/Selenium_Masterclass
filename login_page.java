package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.utils;

public class login_page {
	private static final By LOGIN_BUTTON = By.xpath("//*[@id='pt-login']/a");
	private static final By INPUT_USERNAME = By.id("wpName1");
	private static final By INPUT_PASSWORD = By.id("wpPassword1");
	private static final By LOGIN_SUBMIT = By.id("wpLoginAttempt");

	public static void clickLogin(){
		WebDriverWait wait = new WebDriverWait(utils.driver, 15);
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
		loginButton.click();}
	public static void setUsernameAndPassword(){
		WebDriverWait wait = new WebDriverWait(utils.driver, 15);
		WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(INPUT_USERNAME));
		usernameField.sendKeys("Laurentiur");
		
		//Taking the password from a file and storing it in a variable
		File file = new File("pass.txt");
		String password = null;
		try(FileReader fr= new FileReader(file)){
			BufferedReader br= new BufferedReader(fr);
			String pass;
			pass=br.readLine();
			password=pass;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		WebElement passwordField = utils.driver.findElement(INPUT_PASSWORD);
		passwordField.sendKeys(password);
		WebElement submitCredentials = utils.driver.findElement(LOGIN_SUBMIT);
		submitCredentials.click();
		
	}
}