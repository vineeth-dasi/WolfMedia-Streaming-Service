import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection connection;
	public static String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
	public static String user = "vdasi";
	public static String pswd = "200473135";

	public static void EstablishConnection()
	{
			try {
			connection = DriverManager.getConnection(url, user, pswd);
			System.out.println("Successfully connected to DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Successfully closed DB Connection");
			} catch (Throwable whatever) {
				System.out.println("ERROR closing connection" + whatever.toString());
			}
		}
	}

}
