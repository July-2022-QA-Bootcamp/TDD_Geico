package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.auto.AboutYou;
import pages.common.HomePage;
import pages.home.AddressPage;
import pages.home.DOBPage;
import pages.home.EmailPage;
import pages.home.NamePage;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.Logs;
import utils.Configuration;
import static utils.IConstant.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseClass {

	protected Configuration config = new Configuration();
	WebDriver driver;
	ExtentReports extent;
	protected HomePage homePage;
	protected AboutYou aboutYou;
	protected AddressPage addressPage;
	protected NamePage namePage;
	protected DOBPage dobPage;
	protected EmailPage emailPage;

	@BeforeSuite
	public void initiatinExtentReport() {
		extent = ExtentManager.getInstance();
	}
	
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

	@BeforeMethod
	public void beforeEachTest(Method method) {
		String className = method.getDeclaringClass().getSimpleName();
		ExtentTestManager.startTest(method.getName());
		ExtentTestManager.getTest().assignCategory(className);
	}
	
	@AfterMethod
	public void afterEachTest(ITestResult result) {
		for(String testName : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(testName);
		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
			ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenShot(result.getName()));
		}else {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
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
		emailPage = new EmailPage(driver);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void closingDriverSession() {
		getDriver().quit();
	}
	
	@AfterSuite
	public void closeReport() {
		extent.flush();
	}
	
	public String takeScreenShot(String testName) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_MMddyyyy_hhmmss");
		File folder = new File("test-output/screenShots");
		if(!folder.exists()) {
			String path = new File("test-output").getAbsolutePath();
			new File(path + "/screenShots").mkdir();
			new File(path + "/screenShots").setExecutable(true);
		}
		File localFile = new File("test-output/screenShots/" + testName + format.format(date) +".png");
		TakesScreenshot ss = (TakesScreenshot) driver;
		File driverSS = ss.getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(driverSS, localFile);
			Logs.log("Screen Shot captured at \n" + localFile.getAbsolutePath());
		}catch (IOException e) {
			e.printStackTrace();
			Logs.log("Error occurs during taking ScreenShot..!");
		}
		return localFile.getAbsolutePath();
	}
}
