package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;

	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "dataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/122.0.0.0 Safari/537.36");
//		 driver = new ChromeDriver(options);

		logger = LogManager.getLogger(this.getClass()); // log4j logs

		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))

		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// capabilities.setPlatform(Platform.WIN10);
			// capabilities.setBrowserName("chrome");
			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS");
				return;
			}

			// browser
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdgw");
				break;

			default:
				System.out.println("no matching browser");
				return;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local"))

		{

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		// driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// driver.get("https://demo.opencart.com/");
		// driver.get("https://tutorialsninja.com/demo");
		driver.get(p.getProperty("appUrl"));// reading url from properties file.
		logger.info("******launch the url testcase*******************");

		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "dataDriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() { // for genrating randon number

		String generatedString = RandomStringUtils.randomAlphanumeric(5);
		return generatedString;

	}

	public String randomNumber() {
		String generatNumber = RandomStringUtils.randomNumeric(10);
		return generatNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatNumber);

	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// time stamp

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + " " + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourcefile.renameTo(targetFile);
		return targetFilePath;
	}

}
