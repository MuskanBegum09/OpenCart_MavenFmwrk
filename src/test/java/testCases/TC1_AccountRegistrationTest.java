package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC1_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() { // registeration for opencart website

		logger.info("******starting testcase*******************");
		try {
			HomePage hp = new HomePage(driver); // creating object of homapage class to call methods
			hp.clickMyAccount();
			logger.info("******clicked on myaccounte*");

			hp.clickRegister();
			logger.info("******clicked on register***");

			AccountRegisterationPage regpage = new AccountRegisterationPage(driver);

			logger.info("Providing customer details");

			// regpage.setFirstname("Muskan");
			regpage.setFirstname(randomString());// passing dynamically passing

			// regpage.setLastname("Begum1");
			regpage.setLastname(randomString()); // passing dynamically passing

			regpage.setEmail(randomString() + "@gamail.com");// randomly generated string calling random string methode

			regpage.setTelePhone(randomNumber());
			// regpage.setPassword("Password1");

			String password = randomAlphaNumeric();
			regpage.setPassword(password);// random alphanumeric
			regpage.setConfirmPassword(password);
			regpage.setPrivacyPolicy();

			regpage.clickContinue();
			logger.info("validating expected msg");

			String confmMsg = regpage.getConfirmationmsg();

			if (confmMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("test failed");
				logger.debug("debug logs");
				Assert.assertTrue(false);

			}

		} catch (Exception e) {

			Assert.fail();
		}
		logger.info("******finish testcase*******************");

	}

}
