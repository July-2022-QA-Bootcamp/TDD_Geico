package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;
import common.CommonWaits;

public class AddressPage {

	CommonWaits waits;
	
	public AddressPage(WebDriver driver) {
		waits = new CommonWaits(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "AddressQuestionHeader")
	WebElement addressPageTitle;
	@FindBy(id = "AddressAutoCompleteField")
	WebElement addressElement;
	@FindBy(className = "f_Apt")
	WebElement unitElement;
	@FindBy(id = "next")
	WebElement nextElement;
	@FindBy(id = "NIP015PIPropertyCityList")
	WebElement cityElement;
	
	public void addressPageSteps(String expected, String address, String unit, String city) {
		waits.waitUntilVisible(addressPageTitle);
		assertGetText(addressPageTitle, expected);
		input(addressElement, address);
		input(unitElement, unit);
		if(isPresent(cityElement) && isDisplayed(cityElement)) {
			selectDropdown(cityElement, city);
		}
		click(nextElement);
	}
}
