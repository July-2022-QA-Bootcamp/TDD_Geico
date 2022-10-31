package auto;

import org.testng.annotations.Test;
import base.BaseClass;
import utils.data.AutoData;

public class AutoTest_w_AutoData extends BaseClass{

	AutoData autoData = new AutoData("11418", "08/08/1965", "Shara", "Watson");
	
	@Test(priority = 1)
	public void autoQuoteWithAutoData() {
		homePage.autoSteps(autoData.getZip());
		aboutYou.aboutYouSteps(autoData.getDob(), autoData.getFirstName(), autoData.getLastName());
	}
	
	@Test(priority = 0)
	public void autoQuoteWithAutoDataNoParam() {
		homePage.autoSteps(autoData);
		aboutYou.aboutYouSteps(autoData);
	}
}
