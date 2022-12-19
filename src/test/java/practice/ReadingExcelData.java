package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.ExcelUtils;

public class ReadingExcelData {

	public static void main(String[] args) throws IOException {
		//readSimpleFile();
		//excelOps();
		//excelUtilsUnitTests();
		//fileOps();
		//readCSV();
		jsonRead();
	}
	
	public static void excelUtilsUnitTests() {
		ExcelUtils utils = new ExcelUtils("src/main/resources/AutoData.xlsx", "Sheet1");
		Map<Integer, Map<String, String>> map = utils.getMapData();
		System.out.println("Size of the map : "+ map.size());
		for (Integer key: map.keySet()) {
			Map<String, String> rowMap = map.get(key);
			for(String eachRowKey : rowMap.keySet()) {
				System.out.println(eachRowKey + ", value : " + rowMap.get(eachRowKey));
			}
		}
	}
	
	public static void excelOps() throws FileNotFoundException, IOException {
		Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/AutoData.xlsx"));
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());
	}
	
	public static void readSimpleFile() throws IOException {
		File file = new File("src/main/resources/config.properties");
		System.out.println(file.exists());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.getAbsolutePath());

		
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String lineString = null;
		while((lineString = reader.readLine()) != null) {
			System.out.println(lineString);
		}
	}
	
	public static void fileOps() {
		System.out.println(new File("test-output/screenShots").exists());
		String path = new File("test-output/").getAbsolutePath();
		System.out.println(path);
		new File(path+"/screenShots").mkdir();
		
		System.out.println(new File(path + "/screenShots").exists());
	}
	
	public static void readCSV() throws IOException {
		File file = new File("src/main/resources/HomeData.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String lineString = null;
		List<List<String>>lists = new ArrayList<>();
		while((lineString = reader.readLine()) != null) {
			String[] array = lineString.split(",");
			lists.add(Arrays.asList(array));
			//System.out.println(lineString);
		}
		lists.remove(0);
		for (Iterator iterator = lists.iterator(); iterator.hasNext();) {
			List<String> list = (List<String>) iterator.next();
			System.out.println(list);
		}
	}
	
	public static void jsonRead() throws FileNotFoundException {
		FileReader file = new FileReader("src/main/resources/HomeData.json");
		
		JsonObject jsonObject = JsonParser.parseReader(file).getAsJsonObject();
		
		Map<String, String>map = new HashMap<>();
		map.put("Zip Code", jsonObject.get("Zip Code").getAsString());
		map.put("Title1", jsonObject.get("Title1").getAsString());
		map.put("Address1", jsonObject.get("Address1").getAsString());
		
		for(String key:map.keySet()) {
			System.out.println(key +":"+map.get(key));
		}
	}
}
