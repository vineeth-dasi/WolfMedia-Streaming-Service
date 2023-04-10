import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetadataAndRecords {
	
    public static void enterOrUpdatePlayCountInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
            	// Begin Transaction
            	DBConnect.connection.setAutoCommit(false);
            	
                // Take Song ID as input
                System.out.print("Enter Song ID: ");
                int songID = Main.input.nextInt();
                
                // Take Play Count as input
                System.out.print("Enter Play count: ");
                int playCount = Main.input.nextInt();
                
                Calendar calendar = Calendar.getInstance(); 
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; 
                String yearMonth = (year + "-" + String.format("%02d",month)).toString();
                
                String delQuery = "DELETE FROM PlayCount WHERE SongID=%d AND yearMonth='%s';";
                delQuery = String.format(delQuery, songID, yearMonth);
                
                String insQuery = "INSERT INTO PlayCount VALUES(%d, '%s', %d)";
                insQuery = String.format(insQuery, songID, yearMonth, playCount);
                
                DBConnect.statement.executeUpdate(delQuery);
                DBConnect.statement.executeUpdate(insQuery);
                
                // Commit Transaction
                DBConnect.connection.commit();
                System.out.println("Play count Updated Successfully");
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
    
    public static void enterOrUpdateMonthlyListenersInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Artist ID as input
                System.out.print("Enter Artist ID: ");
                int artistID = Main.input.nextInt();
                
                // Take Monthly Listeners as input
                System.out.print("Enter Monthly Listeners: ");
                int monthlyListeners = Main.input.nextInt();
                
                String updateQuery = "UPDATE Artist SET MonthlyListeners=%d WHERE ArtistID=%d;";
                updateQuery = String.format(updateQuery, monthlyListeners, artistID);
                
                DBConnect.statement.executeUpdate(updateQuery);
                System.out.println("Monthly Listeners Updated Successfully");
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void enterOrUpdateTotalSubscribesForPodcastInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Podcast ID as input
                System.out.print("Enter Podcast ID: ");
                int podcastID = Main.input.nextInt();
                
                // Take Total Subscribers as input
                System.out.print("Enter Total Subscribers: ");
                int totalSubscribers = Main.input.nextInt();
                
                String updateQuery = "UPDATE Podcast SET TotalSubscribers=%d WHERE PodcastID=%d;";
                updateQuery = String.format(updateQuery, totalSubscribers, podcastID);
                
                DBConnect.statement.executeUpdate(updateQuery);
                System.out.println("Total Subscribers Updated Successfully");
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void enterOrUpdateRatingForPodcastInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Podcast ID as input
                System.out.print("Enter Podcast ID: ");
                int podcastID = Main.input.nextInt();
                
                // Take Rating as input
                System.out.print("Enter Rating: ");
                Double rating = Main.input.nextDouble();
                
                String updateQuery = "UPDATE Podcast SET Rating=%f WHERE PodcastID=%d;";
                updateQuery = String.format(updateQuery, rating, podcastID);
                
                DBConnect.statement.executeUpdate(updateQuery);
                System.out.println("Rating Updated Successfully");
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void enterOrUpdateListeningCountForPodcastEpisodeInfo() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Podcast Episode ID as input
                System.out.print("Enter Podcast Episode ID: ");
                int episodeID = Main.input.nextInt();
                
                // Take Listening Count as input
                System.out.print("Enter Listening Count: ");
                int listeningCount = Main.input.nextInt();
                
                String updateQuery = "UPDATE PodcastEpisode SET ListeningCount=%d WHERE EpisodeID=%d;";
                updateQuery = String.format(updateQuery, listeningCount, episodeID);
                
                DBConnect.statement.executeUpdate(updateQuery);
                System.out.println("Listening Count Updated Successfully");
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void findSongsWithArtist() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Artist ID as input
                System.out.print("Enter Artist ID: ");
                int artistID = Main.input.nextInt();
                
                String query = "SELECT * FROM Song WHERE SongID IN (SELECT SongID FROM PerformedBy WHERE ArtistID=%d);";
                query = String.format(query, artistID);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                printResult(result);
                result.close();
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void findSongsInAlbum() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Album ID as input
                System.out.print("Enter Album ID: ");
                int albumID = Main.input.nextInt();
                
                String query = "SELECT * FROM Song WHERE AlbumID=%d;";
                query = String.format(query, albumID);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                printResult(result);
                result.close();
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void findEpisodesInPodcast() throws Exception{
        try{
            if (DBConnect.connection!=null){
            	
                // Take Podcast ID as input
                System.out.print("Enter Podcast ID: ");
                int podcastID = Main.input.nextInt();
                
                String query = "SELECT * FROM PodcastEpisode WHERE PodcastID=%d;";
                query = String.format(query, podcastID);
                
                ResultSet result = DBConnect.statement.executeQuery(query);
                printResult(result);
                result.close();
            }
            else {
                throw new Exception("Connection is null");
            }     
        } catch (SQLException e) {
        	System.out.print("Failure: "+e);
        }
    }
    
    public static void printResult(ResultSet result) throws SQLException {
    	ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        for (int i = 1; i <= columnCount; i++) {
        	String columnName = metaData.getColumnName(i);
            System.out.printf("%-20s", columnName);
        }
        System.out.println();
        
        while (result.next()) {
        	for (int i = 1; i <= columnCount; i++) {
                String value = result.getString(i);
                System.out.printf("%-20s", value);
            }
            System.out.println();
        }
    }
}
