package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) { //for every class we need tha constructor of that class it is mandatory 
		super(driver);   //calling the baseClass parent constructor so no need to add webdriver as global veriable 
	}
	
	//elements locators

	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	//@FindBy(xpath="//a[normalize-space()='Login']")
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	
	//action methods
	public void clickMyAccount() {
		linkMyaccount.click();
	}
	
	public void clickRegister() {
		linkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
}
