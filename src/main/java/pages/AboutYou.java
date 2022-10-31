package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.data.AutoData;

import static common.CommonActions.*;

public class AboutYou {

	public AboutYou(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[starts-with(@id, 'Id_GiveDateOfBirth')]")
	WebElement dobElement;
	@FindBy(xpath = "//button[starts-with(@id,'Id_GiveDateOfBirth')]")
	WebElement nextElement;
	@FindBy(xpath = "//div[starts-with(@id,'Id_AskIfCustomerWantsLexisNexisPrefillMainWidgets_Container')]")
	WebElement letsSpeedUpElement;
	@FindBy(id = "labelForNo")
	WebElement noElement;
	@FindBy(xpath = "//button[starts-with(@id,'Id_AskIfCustomerWantsLexisNexisPrefill')]")
	WebElement nextElement2;
	@FindBy(xpath = "//input[starts-with(@id,'Id_GiveName') and @data-formotivid='FirstName']")
	WebElement firstNamElement;
	@FindBy(xpath = "//input[starts-with(@id,'Id_GiveName') and @data-formotivid='LastName']")
	WebElement lastNamElement;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextElement3;
	
	public void aboutYouSteps(String dob, String firstName, String lastName) {
		if(isPresent(letsSpeedUpElement) && isDisplayed(letsSpeedUpElement)) {
			click(noElement);
			click(nextElement2);
		}
		input(dobElement, dob);
		click(nextElement);
		input(firstNamElement, firstName);
		input(lastNamElement, lastName);
		click(nextElement3);
	}
	
	public void aboutYouSteps(AutoData autoData) {
		if(isPresent(letsSpeedUpElement) && isDisplayed(letsSpeedUpElement)) {
			click(noElement);
			click(nextElement2);
		}
		input(dobElement, autoData.getDob());
		click(nextElement);
		input(firstNamElement, autoData.getFirstName());
		input(lastNamElement, autoData.getLastName());
		click(nextElement3);
	}
}
