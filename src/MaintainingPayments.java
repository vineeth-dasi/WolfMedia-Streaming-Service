import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class MaintainingPayments {
	
	public static void generatePaymnets() throws Exception{
		System.out.println("Generating Royalty Payments....");
		generateRoyaltyPayments();
		System.out.println("Generating Podcast Payments....");
		generatePodcastPayments();
		System.out.println("Generating User Subscription Payments....");
		generateUserSubscriptionPayments();
	}
	
	public static void generateRoyaltyPayments() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM RoyaltyPayments WHERE YearMonth='%s';";
                query = String.format(query, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("Royalty Payments are already generated");
                } else {
                	String insQuery = "INSERT INTO RoyaltyPayments (SongID, YearMonth, Payment, PaidStatus) SELECT p.SongID, p.YearMonth, s.RoyaltyRate*p.Count, 'false' FROM PlayCount p INNER JOIN Song s ON p.SongID=s.SongID WHERE p.YearMonth='%s';";
                    insQuery = String.format(insQuery, yearMonth);

                    DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("Royalty Payments generated Successfully");
                }
                
                // Commit Transaction
                DBConnect.connection.commit();
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
	
	public static void generatePodcastPayments() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM PodcastPayments WHERE YearMonth='%s';";
                query = String.format(query, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("Podcast Payments are already generated");
                } else {
                	String insQuery = "INSERT INTO RoyaltyPayments (SongID, YearMonth, Payment, PaidStatus) SELECT p.SongID, p.YearMonth, s.RoyaltyRate*p.Count, 'false' FROM PlayCount p INNER JOIN Song s ON p.SongID=s.SongID WHERE p.YearMonth='%s';";
                    insQuery = String.format(insQuery, yearMonth);

                    DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("Royalty Payments generated Successfully");
                }
                
                // Commit Transaction
                DBConnect.connection.commit();
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
	
	public static void generateUserSubscriptionPayments() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM RoyaltyPayments WHERE YearMonth='%s';";
                query = String.format(query, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("Already Royalty Payments are generated");
                } else {
                	String insQuery = "INSERT INTO RoyaltyPayments (SongID, YearMonth, Payment, PaidStatus) SELECT p.SongID, p.YearMonth, s.RoyaltyRate*p.Count, 'false' FROM PlayCount p INNER JOIN Song s ON p.SongID=s.SongID WHERE p.YearMonth='%s';";
                    insQuery = String.format(insQuery, yearMonth);

                    DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("Royalty Payments generated Successfully");
                }
                
                // Commit Transaction
                DBConnect.connection.commit();
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
