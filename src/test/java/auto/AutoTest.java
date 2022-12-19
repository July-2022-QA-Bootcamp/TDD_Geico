package auto;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import base.BaseClass;

public class AutoTest extends BaseClass{

	@Test
	public void autoQuote() {
		homePage.autoSteps("11418");
		aboutYou.aboutYouSteps("02/02/1988", "David", "Malan");
	}
	
	@Parameters({"zip", "dob", "firstName", "lastName"})
	@Test
	public void autoQuoteParameter(String zipCode, String DOB, String firstName, String lastName) {
		homePage.autoSteps(zipCode);
		aboutYou.aboutYouSteps(DOB, firstName, lastName);
	}
}
