package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.ExcelUtils;

public class ReadingExcelData {

	public static void main(String[] args) throws IOException {
		//readSimpleFile();
		//excelOps();
		//excelUtilsUnitTests();
		fileOps();
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
}
