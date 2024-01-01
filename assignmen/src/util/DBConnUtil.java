package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	public static Connection getConnection(String props)
	{
		Connection connection=null;
		String[] properties=props.split(" ");
		try {
			Class.forName(properties[4]);
			System.out.println("class loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Established");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(  
					properties[0],properties[1],properties[2]);
			System.out.println("con established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
