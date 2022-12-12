package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import reporting.Logs;

public class ExcelUtils {

	Workbook workbook;
	Sheet sheet;

	public ExcelUtils(String filePath, String sheetName) {
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			if (filePath.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (filePath.endsWith(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
			}
			sheet = workbook.getSheet(sheetName);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			Logs.log("File not found at : " + new File(filePath).getAbsolutePath());
		} catch (NullPointerException e) {
			e.printStackTrace();
			Logs.log("File or Sheet not found");
		}
	}

	public Map<Integer, Map<String, String>> getMapData() {
		Map<Integer, Map<String, String>> map = new HashMap<>();

		int executionCell = getExecutionCellNum();
		System.out.println("Execution cell num : " + executionCell);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Map<String, String> rowMap = new HashMap<>();
			boolean executionFlag = false;
			try {
				executionFlag = sheet.getRow(i).getCell(executionCell).getStringCellValue().equalsIgnoreCase("Y");
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			if (executionFlag) {
				for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
					try {
						String key = sheet.getRow(0).getCell(j).getStringCellValue().trim();
						String value = sheet.getRow(i).getCell(j).getStringCellValue().trim();
						rowMap.put(key, value);
						// System.out.println("Key : "+key + " ---> value : " + value);
					} catch (NullPointerException e) {
						// e.printStackTrace();
					}
				}
				map.put(i, rowMap);
			}
		}
		return map;
	}

	private int getExecutionCellNum() {
		int cellNum = 0;
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
			try {
				if (sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase("Execution")) {
					cellNum = i;
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return cellNum;
	}
}
