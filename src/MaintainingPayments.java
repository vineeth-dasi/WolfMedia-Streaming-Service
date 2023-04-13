import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class MaintainingPayments {
	
	public static void makeRoyaltyPayment() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
            	
                // Take Song ID as input
                System.out.print("Enter Song ID: ");
                int songID = Main.input.nextInt();
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM RoyaltyPayments WHERE SongID=%d AND yearMonth='%s' AND PaidStatus='true';";
                query = String.format(query, songID, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("\nPayment is already made");
                } else {
                	String insQuery = "UPDATE RoyaltyPayments SET PaidStatus='true' WHERE SongID=%d AND yearMonth='%s'";
                    insQuery = String.format(insQuery, songID, yearMonth);
                    
                    int res = DBConnect.statement.executeUpdate(insQuery);
                    if (res > 0) {
                    	System.out.println("\nMade payment Successfully");
                    } else {
                    	System.out.println("\nNo payment records found");
                    }
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
	
	public static void makePodcastPayment() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
            	
                // Take Podcast Host ID as input
                System.out.print("Enter Podcast Host ID: ");
                int hostID = Main.input.nextInt();
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM PodcastPayments WHERE HostID=%d AND yearMonth='%s' AND PaidStatus='true';";
                query = String.format(query, hostID, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("\nPayment is already made");
                } else {
                	String insQuery = "UPDATE PodcastPayments SET PaidStatus='true' WHERE HostID=%d AND yearMonth='%s'";
                    insQuery = String.format(insQuery, hostID, yearMonth);
                    
                    int res = DBConnect.statement.executeUpdate(insQuery);
                    if (res > 0) {
                    	System.out.println("\nMade payment Successfully");
                    } else {
                    	System.out.println("\nNo payment records found");
                    }
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
	
	public static void receiveSubscribersPayment() throws Exception {
		try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
            	
                // Take User ID as input
                System.out.print("Enter User ID: ");
                int userID = Main.input.nextInt();
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String query = "SELECT COUNT(*) FROM SubscriptionPayments WHERE UserID=%d AND yearMonth='%s' AND ReceivedStatus='true';";
                query = String.format(query, userID, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("\nPayment is already received");
                } else {
                	String insQuery = "UPDATE SubscriptionPayments SET ReceivedStatus='true' WHERE UserID=%d AND yearMonth='%s'";
                    insQuery = String.format(insQuery, userID, yearMonth);
                    
                    int res = DBConnect.statement.executeUpdate(insQuery);
                    if (res > 0) {
                    	System.out.println("\nReceived payment Successfully");
                    } else {
                    	System.out.println("\nNo payment records found");
                    }
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
	
	public static void generatePayments() throws Exception{
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
                	System.out.println("\nRoyalty Payments are already generated");
                } else {
                	String insQuery = "INSERT INTO RoyaltyPayments (SongID, YearMonth, Payment, PaidStatus) SELECT p.SongID, p.YearMonth, s.RoyaltyRate*p.Count, 'false' FROM PlayCount p INNER JOIN Song s ON p.SongID=s.SongID WHERE p.YearMonth='%s';";
                    insQuery = String.format(insQuery, yearMonth);
                    
                    DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("\nRoyalty Payments generated Successfully");
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
                	System.out.println("\nPodcast Payments are already generated");
                } else {
                	String insQuery = "INSERT INTO PodcastPayments (HostID, YearMonth, Payment, PaidStatus)\r\n"
                            + "SELECT cnt.HostID, DATE_FORMAT(pe.ReleaseDate, %s), FORMAT(SUM(p.FeePerEpisode + (pe.AdvertisementCount * 10)) / cnt.NumHostsPerPodcast,2) AS RevenuePerHost, 'false' FROM PodcastEpisode pe JOIN Podcast p ON pe.PodcastID = p.PodcastID JOIN ( SELECT h.PodcastID, h.HostID, hp.NumHostsPerPodcast FROM HostedBy h INNER JOIN ( SELECT PodcastID, COUNT(HostID) AS NumHostsPerPodcast FROM HostedBy GROUP BY PodcastID ) AS hp ON h.PodcastID = hp.PodcastID ) AS cnt ON cnt.PodcastID = p.PodcastID WHERE DATE_FORMAT(pe.ReleaseDate, %s) = '%s' GROUP BY cnt.HostID, DATE_FORMAT(pe.ReleaseDate, %s);";
                	insQuery = String.format(insQuery, "'%Y-%m'", "'%Y-%m'", yearMonth, "'%Y-%m'");
                    
                	DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("\nPodcast Payments generated Successfully");
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
               
                String yearMonth = (year + "-" + String.format("%02d",month%12)).toString();
                String date = (year + "-" + String.format("%02d",(month+1)%12)).toString() + "-01";
                
                String query = "SELECT COUNT(*) FROM SubscriptionPayments WHERE YearMonth='%s';";
                query = String.format(query, yearMonth);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                result.next();
                if(result.getInt(1) > 0) {
                	System.out.println("\nAlready Subscription Payments are generated");
                } else {
                	String insQuery = "INSERT INTO SubscriptionPayments (UserID, YearMonth, Payment, ReceivedStatus)\r\n"
                			+ "SELECT u.UserID, '%s', u.MonthlySubscriptionFee, 'false' FROM User u\r\n"
                			+ "WHERE u.UserID IN (SELECT UserID from User) AND u.StatusOfSubscription='true' AND u.RegistrationDate<'%s';";
                	insQuery = String.format(insQuery, yearMonth, date);
                    
                	DBConnect.statement.executeUpdate(insQuery);
                    System.out.println("\nSubscription Payments generated Successfully");
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
