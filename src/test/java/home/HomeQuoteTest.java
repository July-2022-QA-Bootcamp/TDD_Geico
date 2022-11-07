package home;

import org.testng.annotations.Test;

import base.BaseClass;

public class HomeQuoteTest extends BaseClass{

	@Test(enabled = true)
	public void homeownersQuoteFlow() {
		homePage.homeSteps("11423");
		addressPage.addressPageSteps("What is the address of the home you want to insure? ", "10021 202nd Street", "2B", "HOLLIS");
		namePage.namePageSteps("What is your name?", "ABD", "Villers");
		dobPage.dobPageSteps("When were you born? ?","02/02/1977");
		emailPage.emailPageSteps("What is your email address? ?","test@test.com");
	}
	
	@Test
	public void homeownersQuoteFlowInvalidEmailCheck() {
		homePage.homeSteps("11423");
		addressPage.addressPageSteps("What is the address of the home you want to insure? ", "10021 202nd Street", "2B", "HOLLIS");
		namePage.namePageSteps("What is your name?", "ABD", "Villers");
		dobPage.dobPageSteps("When were you born? ?","02/02/1977");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "test");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "*123@");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "123");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "test@test");
	}
	
	@Test
	public void homeownersQuoteFlowInvalidEmailCheckFail() {
		homePage.homeSteps("11423");
		addressPage.addressPageSteps("What is the address of the home you want to insure? ", "10021 202nd Street", "2B", "HOLLIS");
		namePage.namePageSteps("What is your name?", "ABD", "Villers");
		dobPage.dobPageSteps("When were you born? ?","02/02/1977");
		emailPage.emailPageSteps("What is your email address? ?","test@test.com");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "test");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "*123@");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "123");
		emailPage.validateInvalidEmail("Please enter a valid email address.", "test@test");
	}
}
