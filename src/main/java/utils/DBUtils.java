package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import reporting.Logs;

public class DBUtils {

	Connection connection;
	
	public DBUtils(String url, String user, String pass) {
		try {
			connection = DriverManager.getConnection(url, user, pass);
			//Logs.log("JDBC Connected...");
		} catch (SQLException e) {
			//Logs.log("JDBC Connection Unsuccessful");
			e.printStackTrace();
		}
	}
	
	public Map<Integer, Map<String, String>> getJDBCMap(String query) {
		Map<Integer, Map<String, String>> map = new HashMap<>();
		try {
			Statement statement = connection.createStatement();
			statement.execute(query);
			ResultSet set = statement.getResultSet();
			ResultSetMetaData metaData = set.getMetaData();
			
			int totalColumns = metaData.getColumnCount();
			int rowIndex = 0;
			
			while (set.next()) {
				Map<String, String> rowMap = new HashMap<>();
				for(int i = 1; i <= totalColumns; i++) {
					rowMap.put(metaData.getColumnName(i), set.getString(i));
				}
				map.put(rowIndex, rowMap);
				rowIndex++;
			}
			connection.close();
		} catch (SQLException e) {
			//Logs.log(e.getLocalizedMessage());
			e.printStackTrace();
		}catch (Exception e) {
			//Logs.log(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return map;
	}
}
