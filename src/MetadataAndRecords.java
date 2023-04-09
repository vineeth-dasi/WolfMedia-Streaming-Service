import java.util.Calendar;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MetadataAndRecords {
	
    public static void enterOrUpdatePlayCountInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
                Scanner input = new Scanner(System.in);
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
            	
                // Take Song ID as input
                System.out.print("Enter Song ID: ");
                int songID = input.nextInt();
                
                // Take Play Count as input
                System.out.print("Enter Play count: ");
                int playCount = input.nextInt();
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + month).toString();
                
                String delQuery = "DELETE from PlayCount WHERE SongID=%d AND yearMonth=%s;";
                delQuery = String.format(delQuery, songID, yearMonth);
                
                String insQuery = "INSERT INTO PlayCount VALUES(%d, %s, %d)";
                insQuery = String.format(insQuery, songID, yearMonth, playCount);
                
                DBConnect.statement.executeUpdate(delQuery);
                DBConnect.statement.executeUpdate(insQuery);
                
                // Commit Transaction
                DBConnect.connection.commit();
                input.close();
                System.out.println("Play count updated succesfully");
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        	// Rollback Transaction on failure
        	DBConnect.connection.rollback();
            System.out.println("Transaction Rolled back");
        }
        finally {
        	// End Transaction 
        	DBConnect.connection.setAutoCommit(true);
        }
    }
}
