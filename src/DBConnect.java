import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	public static Connection connection = null;
	public static Statement statement = null;
	// public static ResultSet result = null;
	public static String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/vyalla";
	public static String user = "vyalla";
	public static String pswd = "200473920";

	public static void EstablishConnection() throws SQLException 
	{
			connection = DriverManager.getConnection(url, user, pswd);
			statement = connection.createStatement();
			System.out.println("Successfully connected to DB"); 
	}

	public static void close() throws SQLException {
		if (connection != null) {
				connection.close();
				statement.close();
				System.out.println("Successfully closed DB Connection");
		}
	}
	
	// public static boolean execUpdate(String command) {
	// 	try {
	// 		statement.executeUpdate(command);
	// 		return true;
	// 	} catch (SQLException e) {
	// 		System.out.println("Failure: " + e);
	// 		return false;
	// 	}
	// }
	
	// public static ResultSet execQuery(String command) throws SQLException{
	// 	result = statement.executeQuery(command);
	// 	return result;
	// }
	
	// public static void beginTransaction() {
	// 	try {
	// 		connection.setAutoCommit(false);
	// 	} catch (SQLException e) {
	// 		System.out.println("Failure: " + e);
	// 	}
	// }
	
	// public static void rollBackTransaction() {
	// 	try {
	// 		connection.rollback();
	// 		connection.setAutoCommit(true);
	// 	} catch (SQLException e) {
	// 		System.out.println("Failure: " + e);
	// 	}
	// }
	
	// public static void commitTransaction() {
	// 	try {
	// 		connection.commit();
	// 		connection.setAutoCommit(true);
	// 	} catch (SQLException e) {
	// 		System.out.println("Failure: " + e);
	// 	}
	// }

}
