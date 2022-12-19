package auto;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.testng.annotations.DataProvider;
import base.BaseClass;
import utils.DBUtils;
import utils.data.AutoData;

public class AutoTest_w_AutoData_DDD_JDBC extends BaseClass{

	@DataProvider(name = "autoData")
	public Object[][] testData() throws ParseException{
		DBUtils utils = new DBUtils(config.getProperty("jdbcUrl"), config.getProperty("jdbcUser"), config.getProperty("jdbcPass"));
		Map<Integer, Map<String, String>> map = utils.getJDBCMap(config.getProperty("query"));
		Object[][] objects = new Object[map.size()][1];
		int index = 0;
		for(Integer set : map.keySet()) {
			Map<String, String> rowMap = map.get(set);
			
			Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(rowMap.get("birth_date"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dob = dateFormat.format(dateObj);
			
			AutoData data = new AutoData("11423", dob, 
					rowMap.get("first_name"), rowMap.get("last_name"));
			objects[index][0] = data;
			index++;
		}
		return objects;
	}
	
	@Test(enabled = false, dataProvider = "autoData")
	public void autoQuoteWithAutoDataNoParam(AutoData autoData) {
		homePage.autoSteps(autoData);
		aboutYou.aboutYouSteps(autoData);
	}
	
	@Test(enabled = true, dataProvider = "autoData")
	public void autoQuoteWithAutoData(AutoData autoData) {
		homePage.autoSteps(autoData.getZip());
		aboutYou.aboutYouSteps(autoData.getDob(), autoData.getFirstName(), autoData.getLastName());
	}
}
