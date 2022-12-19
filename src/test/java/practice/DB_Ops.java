package practice;

import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import utils.Configuration;
import utils.DBUtils;

public class DB_Ops{

	protected Configuration config = new Configuration();
	
	@Test(enabled = false)
	public void unitTestDBOps() {
		DBUtils dbUtils = new DBUtils(config.getProperty("jdbcUrl"), config.getProperty("jdbcUser"), config.getProperty("jdbcPass"));
		Map<Integer, Map<String, String>> map = dbUtils.getJDBCMap(config.getProperty("query"));
		System.out.println("Size of the map is : " + map.size());
		for(Integer key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}
	
	@Test(enabled = false)
	public void JDBC_connection() {
		/*
		 * JDBC - Java DataBase Connectivity (Oracle, Postgresql, MySql, MongoDB)
		 * 1. Register Driver using DriverManager ()
		 * 2. Create a Connection object , will come from DriverManager.getConnection()
		 * 3. Create a Statement object from connection
		 * 4. Pass a query inside create statement
		 * 5. statement.getResultSet() will return ResultSet object
		 * Close the connection
		 */

		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres",
					"hr");
			System.out.println("Connected...");
			Statement statement = connection.createStatement();
			statement.execute("select * from countries");
			ResultSet set = statement.getResultSet();
			ResultSetMetaData metas = set.getMetaData();
			
			int totalColumns = metas.getColumnCount();
			System.out.println("Columns numbers " + totalColumns);
			for(int i = 1; i <= totalColumns; i++) {
				System.out.println(metas.getColumnName(i));
			}
			
			System.out.println("Country ID : Country Name : Region ID");
			while (set.next()) {
				System.out.println(set.getString(1) +" : "+ set.getString(2) +" : "+ set.getInt(3));
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println("NOT Connected");
			e.printStackTrace();
		}
	}
	
	@Test
	public void dateFormate() throws ParseException {
		String date = "1988-02-23";
		Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String date2 = dateFormat.format(dateObj);
		System.out.println(date2);
	}
}
