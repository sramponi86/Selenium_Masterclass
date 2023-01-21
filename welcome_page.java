package welcome;

import org.openqa.selenium.*;

import utilities.utils;	

public class welcome_page {
	private static final WebElement ENGLISH_BUTTON = utils.driver.findElement(By.id("js-link-box-en"));
	public static void clickEnglishButton(){
		ENGLISH_BUTTON.click();
	}
}
