package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.utils;

public class main_page {
	private static By SEARCH_FIELD = By.xpath("//*[@id='searchInput']");
	private static By SEARCH_BUTTON = By.id("searchButton");
	
	public static void testSearch(String searchStr){
		WebElement searchField = utils.driver.findElement(SEARCH_FIELD);
		searchField.sendKeys(searchStr);
		utils.driver.findElement(SEARCH_BUTTON).click();
	}
}
