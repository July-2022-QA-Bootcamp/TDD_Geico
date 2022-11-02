package home;

import org.testng.annotations.Test;

import base.BaseClass;

public class HomeQuoteTest extends BaseClass{

	@Test
	public void homeownersQuoteFlow() {
		homePage.homeSteps("11423");
		addressPage.addressPageSteps("What is the address of the home you want to insure? ", "10021 202nd Street", "2B", "HOLLIS");
		namePage.namePageSteps("What is your name?", "ABD", "Villers");
		dobPage.dobPageSteps("When were you born? ?","02/02/1977");
	}
}
