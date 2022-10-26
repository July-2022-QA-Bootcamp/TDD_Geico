package auto;

import org.testng.annotations.Test;
import base.BaseClass;

public class AutoTest extends BaseClass{

	@Test
	public void autoQuote() {
		homePage.autoSteps();
		aboutYou.aboutYouSteps("02/02/1988");
	}
}
