import login.login_page;
import main.main_page;
import utilities.utils;
import welcome.welcome_page;

public class test {

	public static void main(String[] args) {
		utils.instantiate();
		welcome_page.clickEnglishButton();
		login_page.clickLogin();
		login_page.setUsernameAndPassword();
		main_page.testSearch("Document Object Model");
		utils.closeDriver();
		}
	}

