package home;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import base.BaseClass;

public class HomeQuoteTest_w_DDD_CSV extends BaseClass{
	
	/*
	 * 1. Data Source - (Excel, CSV, JSON, DB)
	 * 2. Storing Data - Data Structure (Map, List Arrays)
	 * 3. Passing Data into tests - @DataProvider (Iterator, Object[][])
	 * 4. Tests should take data during runtime
	 */
	
	@SuppressWarnings("resource")
	@DataProvider(name = "listHomeData")
	public Iterator<List<String>> getDataList() throws IOException{
		File file = new File("src/main/resources/HomeData.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String lineString = null;
		List<List<String>>lists = new ArrayList<>();
		while((lineString = reader.readLine()) != null) {
			String[] array = lineString.split(",");
			lists.add(Arrays.asList(array));
		}
		lists.remove(0);
		return lists.iterator();
	}

	@Test(enabled = true, dataProvider = "listHomeData")
	public void homeownersQuoteFlow(List<String>list) {
		homePage.homeSteps(list.get(0));
		addressPage.addressPageSteps(list.get(1), list.get(2), list.get(3), list.get(4));
		namePage.namePageSteps(list.get(5), list.get(6), list.get(7));
		dobPage.dobPageSteps(list.get(8),list.get(9));
		emailPage.emailPageSteps(list.get(10), list.get(11));
	}
}
