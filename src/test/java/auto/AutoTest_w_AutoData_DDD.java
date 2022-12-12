package auto;

import org.testng.annotations.Test;
import java.util.Map;
import org.testng.annotations.DataProvider;
import base.BaseClass;
import utils.ExcelUtils;
import utils.data.AutoData;
import static utils.IConstant.*;

public class AutoTest_w_AutoData_DDD extends BaseClass{

	@DataProvider(name = "autoData")
	public Object[][] testData(){
		ExcelUtils utils = new ExcelUtils(config.getProperty(EXCEL), config.getProperty(SHEET));
		Map<Integer, Map<String, String>> map = utils.getMapData();
		Object[][] objects = new Object[map.size()][1];
		int index = 0;
		for(Integer set : map.keySet()) {
			Map<String, String> rowMap = map.get(set);
			AutoData data = new AutoData(rowMap.get("Zip Code"), rowMap.get("DOB"), 
					rowMap.get("First Name"), rowMap.get("Last Name"));
			objects[index][0] = data;
			index++;
		}
		return objects;
	}
	
	@DataProvider(name = "autoDataMap")
	public Object[][] testDataMap(){
		ExcelUtils utils = new ExcelUtils(config.getProperty(EXCEL), config.getProperty(SHEET));
		Map<Integer, Map<String, String>> map = utils.getMapData();
		Object[][] objects = new Object[map.size()][1];
		int index = 0;
		for(Integer set : map.keySet()) {
			Map<String, String> rowMap = map.get(set);
			objects[index][0] = rowMap;
			index++;
		}
		return objects;
	}
	
	@Test(enabled = false, dataProvider = "autoData")
	public void autoQuoteWithAutoData(AutoData autoData) {
		homePage.autoSteps(autoData.getZip());
		aboutYou.aboutYouSteps(autoData.getDob(), autoData.getFirstName(), autoData.getLastName());
	}
	
	@Test(enabled = true, dataProvider = "autoDataMap")
	public void autoQuoteWithAutoDataMap(Map<String, String> map) {
		homePage.autoSteps(map.get("Zip Code"));
		aboutYou.aboutYouSteps(map.get("DOB"), 
				map.get("First Name"), map.get("Last Name"));
	}
}
