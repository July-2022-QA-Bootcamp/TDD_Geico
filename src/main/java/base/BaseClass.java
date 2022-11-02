package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.auto.AboutYou;
import pages.common.HomePage;
import pages.home.AddressPage;
import pages.home.DOBPage;
import pages.home.NamePage;
import utils.Configuration;
import static utils.IConstant.*;
import java.time.Duration;

public class BaseClass {

	Configuration config = new Configuration();
	WebDriver driver;
	protected HomePage homePage;
	protected AboutYou aboutYou;
	protected AddressPage addressPage;
	protected NamePage namePage;
	protected DOBPage dobPage;

	@Parameters("browser")
	@BeforeMethod
	public void setUpDriver(String browser) {
		initDriver(browser);
		driver.manage().window().maximize();
		driver.get(config.getProperty((URL)));
		long pageLoadTime = Long.parseLong(config.getProperty(PAGELOAD_WAIT));
		long implicitWait = Long.parseLong(config.getProperty(IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		initObjectClasses();
	}

	private void initDriver(String browser) {
		switch (browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}
	
	private void initObjectClasses() {
		homePage = new HomePage(driver);
		aboutYou = new AboutYou(driver);
		addressPage = new AddressPage(driver);
		namePage = new NamePage(driver);
		dobPage = new DOBPage(driver);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void closingDriverSession() {
		//getDriver().quit();
	}
}
