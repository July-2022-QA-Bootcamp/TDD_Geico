package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;

public class EmailPage {

	/*Pre-Condition:
	 * Constructor
	 * 	- PageFactory.initElement
	 * @FindBy title
	 * @FindBy inputEmail
	 * @Findby Next
	 * Declare steps method:
	 * 	- import static CommonActions
	 * 	- Verify title
	 * 	- sendkeys email
	 * 	- click next
	 * Post-Condition
	 * 	- Declare object in baseClass
	 * 	- Initialize Object
	 * Outcome:
	 * 	- We can call into test class
	 */
	
	public EmailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//h2[@class='breakdownQuestionHeader'])[3]")
	WebElement emailTitlElement;
	@FindBy(className = "f_Email")
	WebElement emailInputElement;
	@FindBy(id = "next")
	WebElement nextButtonElement;
	@FindBy(id = "NIP011EmailAddress_validationMessage")
	WebElement errorEmailElement;
	
	public void emailPageSteps(String expected, String email) {
		assertGetText(emailTitlElement, expected);
		input(emailInputElement, email);
		click(nextButtonElement);
	}
	
	public void validateInvalidEmail(String expectedErrorMsg, String invalidEmail) {
		clear(emailInputElement);
		input(emailInputElement, invalidEmail);
		click(nextButtonElement);
		assertGetText(errorEmailElement, expectedErrorMsg);
	}
}
