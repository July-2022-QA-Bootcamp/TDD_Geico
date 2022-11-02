package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;

public class DOBPage {

	/*
	 * Pre-condition:
	 * - create a constructor
	 * - page factory
	 * - @FindBy with locator
	 * - define web element
	 * - create a step method
	 * - import static commonaction
	 * 1. Validate the title
	 * 2. enter dob
	 * 3. click next
	 * Add it in BaseClass
	 * declare
	 * initialization
	 * call it in test
	 */
	
	public DOBPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//h2[@class='breakdownQuestionHeader'])[2]")
	WebElement dobPageTitlElement;
	@FindBy(xpath = "//input[@id='CD003DateOfBirth']")
	WebElement dobElement;
	@FindBy(id = "next")
	WebElement nextElement;
	
	public void dobPageSteps(String exppected, String dob) {
		assertGetText(dobPageTitlElement, exppected);
		input(dobElement, dob);
		click(nextElement);
	}
}
