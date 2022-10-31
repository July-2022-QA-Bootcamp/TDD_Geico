package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.CommonWaits;
import utils.data.AutoData;

import static common.CommonActions.*;

public class HomePage {

	WebDriver driver;
	CommonWaits waits;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		waits = new CommonWaits(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ssp-service-zip")
	WebElement zipCodElement;
	@FindBy(xpath = "//div[@class='product-cards']/div[@class='card' and contains(.,'Auto')]")
	WebElement autoProductElement;
	@FindBy(id = "bundleModalBtn")
	WebElement startMyQuotElement;
	@FindBy(xpath = "(//input[@value='Continue'])[1]")
	WebElement continuElement;

	/*
	 * By continuElementBy = By.xpath("(//input[@value='Continue'])[1]");
	 * driver.findElement(continuElementBy) ==
	 * driver.findElement(By.xpath("(//input[@value='Continue'])[1]"))
	 */

	public void autoSteps(String zipCode) {
		input(zipCodElement, zipCode);
		click(autoProductElement);
		click(startMyQuotElement);

		waits.waitUntilVisible(continuElement);
		if (isPresent(continuElement) && isDisplayed(continuElement)) {
			click(continuElement);
		}
	}
	
	public void autoSteps(AutoData autoData) {
		input(zipCodElement, autoData.getZip());
		click(autoProductElement);
		click(startMyQuotElement);

		waits.waitUntilVisible(continuElement);
		if (isPresent(continuElement) && isDisplayed(continuElement)) {
			click(continuElement);
		}
	}
}
