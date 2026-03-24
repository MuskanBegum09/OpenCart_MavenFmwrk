package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC2_LoginTest extends BaseClass{
	@Test(groups={"Sanity", "Master"})
public void verify_login() {
		logger.info("****stating TC02_loginTest***********");
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
	//login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		
		lp.clickLogin();
		
		//My account
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***finedshed tc2 login test***********************");

	}
}
