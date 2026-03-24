package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txttelePhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstname(String Fname) {
		txtFirstname.sendKeys(Fname);
	}

	public void setLastname(String Lname) {
		txtLastname.sendKeys(Lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelePhone(String phoneNum) {
		txttelePhone.sendKeys(phoneNum);
	}
	

	public void setPassword(String password){
		txtPassword.sendKeys(password);
	}
	
	
	public void setConfirmPassword(String Cpassword){
		txtConfirmPassword.sendKeys(Cpassword);
	}
	public void setPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		// sol1
		btnContinue.click();
	}

	public String getConfirmationmsg() { //no validation if the msg fail then exception will throw
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}
	}

}
