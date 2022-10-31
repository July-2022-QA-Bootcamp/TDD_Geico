package home;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import reporting.Logs;
import utils.retry.RetryClass;

public class HomeTest extends BaseClass{

	@Test(priority = 1, testName = "Testing home quote with priority 1")
	public void homeQuote1() {
		Logs.log("This is priority 1");
	}
	
	@Test(priority = 0, groups = {"home", "priority"})
	public void homeQuote2() {
		Logs.log("This is priority 0");
	}
	
	@Test(enabled = false, groups = {"home", "noPriority"})
	public void homeQuote3() {
		Logs.log("No priority 3");
	}
	
	@Test(groups = "home", dependsOnGroups = "home", alwaysRun = true, retryAnalyzer = RetryClass.class)
	public void homeQuote4() {
		Logs.log("No priority 4");
		Assert.fail();
	}
	
	@Test(groups = "home", dependsOnMethods = "homeQuote3", ignoreMissingDependencies = true)
	public void aHomeTest() {
		Logs.log("No priority 5");
	}
	
	@Test(priority = 100, enabled = false)
	public void bHomeTest() {
		Logs.log("This is priority 100");
	}
}
