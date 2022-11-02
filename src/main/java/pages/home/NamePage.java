package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.CommonWaits;

import static common.CommonActions.*;

public class NamePage {

	CommonWaits waits;
	
	public NamePage(WebDriver driver) {
		waits = new CommonWaits(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//h2[@class='breakdownQuestionHeader'])[1]")
	WebElement namePageTitlElement;
	@FindBy(xpath = "//input[@id='NIP002PIFirstName']")
	WebElement firstNameElement;
	@FindBy(xpath = "//input[@id='NIP003PILastName']")
	WebElement lastNamElement;
	@FindBy(id = "next")
	WebElement nextElement;
	
	public void namePageSteps(String expected, String firstName, String lastName) {
		waits.waitUntilVisible(namePageTitlElement);
		assertGetText(namePageTitlElement, expected);
		input(firstNameElement, firstName);
		input(lastNamElement, lastName);
		click(nextElement);
	}
}
