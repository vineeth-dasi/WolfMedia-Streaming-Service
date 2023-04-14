/**
 * 
 */

/**
 * @author sahithiammana
 *
 */

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import org.verdictdb.commons.DBTablePrinter;

public class ReportGeneration {
	
//  Monthly play count per song
	public static void calculateMonthlyPlayCountPerSong() {
		String sql = "SELECT Song.SongID, Song.SongTitle, YearMonth, Count FROM PlayCount "
				+ "JOIN Song ON Song.SongID = PlayCount.SongID;";
		showResults(sql);
	}
	
//  Monthly play count per album
	public static void calculateMonthlyPlayCountPerAlbum() {
		String sql = "SELECT s.AlbumID, a.AlbumName, p.YearMonth, SUM(p.Count) as MonthlyPlayCount FROM PlayCount p "
				+ "JOIN Song s ON (s.SongID = p.SongID) JOIN Album a ON a.AlbumID = s.AlbumID GROUP BY s.AlbumID, p.YearMonth;";
		showResults(sql);
	}
	
//  Monthly play count per artist
	public static void calculateMonthlyPlayCountPerArtist() {
		String sql = "SELECT pb.ArtistID, a.ArtistName, p.YearMonth, SUM(p.Count) as MonthlyPlayCount FROM PlayCount p "
				+ "JOIN PerformedBy pb ON (p.SongID = pb.SongID) JOIN Artist a ON a.ArtistID = pb.ArtistID GROUP BY pb.ArtistID, p.YearMonth;";
		showResults(sql);
	}
	
//	Total payments made out to host per a given time period
	public static void calculateTotalPaymentsToHost() {
		String sql = "SELECT HostID, SUM(Payment) as Payment FROM PodcastPayments WHERE YearMonth BETWEEN '%s' AND '%s' GROUP BY HostID;";

//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//	Payments made out to host per month for a given time period
	public static void calculatePaymentsToHostPerMonth() {
		String sql = "SELECT HostID, YearMonth, Payment as Payment FROM PodcastPayments WHERE YearMonth BETWEEN '%s' AND '%s' GROUP BY HostID, YearMonth;";

//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//	Total payments made out to host per a given time period
	public static void calculateTotalPaymentsToArtist() {
		String sql = "SELECT a.ArtistID, a.ArtistName, FORMAT(SUM(rp.Payment * 0.7 / num_artists), 2) AS TotalPayment FROM Artist a "
				+ "JOIN PerformedBy pb ON a.ArtistID = pb.ArtistID "
				+ "JOIN Song s ON pb.SongID = s.SongID "
				+ "JOIN RoyaltyPayments rp ON s.SongID = rp.SongID "
				+ "JOIN (SELECT pb.SongID, COUNT(DISTINCT pb.ArtistID) AS num_artists FROM PerformedBy pb "
				+ "GROUP BY pb.SongID) AS cnt ON pb.SongID = cnt.SongID "
				+ "WHERE rp.YearMonth BETWEEN '%s' AND '%s' AND rp.PaidStatus = 'true' GROUP BY a.ArtistID, a.ArtistName;";
	
//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//	Payments made out to host per month a given time period
	public static void calculatePaymentsToArtistPerMonth() {
		String sql = "SELECT a.ArtistID, a.ArtistName, rp.YearMonth, SUM(FORMAT(rp.Payment * 0.7 / num_artists, 2)) AS TotalPayment FROM Artist a "
				+ "JOIN PerformedBy pb ON a.ArtistID = pb.ArtistID "
				+ "JOIN Song s ON pb.SongID = s.SongID "
				+ "JOIN RoyaltyPayments rp ON s.SongID = rp.SongID "
				+ "JOIN (SELECT pb.SongID, COUNT(DISTINCT pb.ArtistID) AS num_artists FROM PerformedBy pb "
				+ "GROUP BY pb.SongID) AS cnt ON pb.SongID = cnt.SongID "
				+ "WHERE rp.YearMonth BETWEEN '%s' AND '%s' AND rp.PaidStatus = 'true' GROUP BY a.ArtistID, a.ArtistName, rp.YearMonth;";
	
//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//	Total payments made out to record label per a given time period
	public static void calculateTotalPaymentsToRecordLabel() {
		String sql = "SELECT r.LabelName, FORMAT(SUM(rp.Payment*0.3),2) AS TotalPayments "
				+ "FROM RoyaltyPayments rp "
				+ "INNER JOIN (SELECT DISTINCT SongID, ArtistID FROM PerformedBy WHERE MainArtistStatus = 'true') pb ON rp.SongID = pb.SongID "
				+ "INNER JOIN Contract c ON pb.ArtistID = c.ArtistID "
				+ "INNER JOIN RecordLabel r ON c.LabelName = r.LabelName "
				+ "INNER JOIN Song s ON s.SongID = pb.SongID "
				+ "WHERE (rp.YearMonth BETWEEN '%s' AND '%s') AND rp.PaidStatus= 'true' AND (s.ReleaseDate BETWEEN c.StartDate AND c.EndDate) "
				+ "GROUP BY r.LabelName;";
	
//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//	Total payments made out to record label per month in a given time period
	public static void calculatePaymentsToRecordLabelPerMonth() {
		String sql = "SELECT r.LabelName, rp.YearMonth, SUM(FORMAT(rp.Payment*0.3,2)) AS TotalPayments "
				+ "FROM RoyaltyPayments rp "
				+ "INNER JOIN (SELECT DISTINCT SongID, ArtistID FROM PerformedBy WHERE MainArtistStatus = 'true') pb ON rp.SongID = pb.SongID "
				+ "INNER JOIN Contract c ON pb.ArtistID = c.ArtistID "
				+ "INNER JOIN RecordLabel r ON c.LabelName = r.LabelName "
				+ "INNER JOIN Song s ON s.SongID = pb.SongID "
				+ "WHERE (rp.YearMonth BETWEEN '%s' AND '%s') AND rp.PaidStatus= 'true' AND (s.ReleaseDate BETWEEN c.StartDate AND c.EndDate) "
				+ "GROUP BY r.LabelName, rp.YearMonth;";
	
//		Take start of time period as input
		System.out.print("Enter start of time period in the format YYYY-MM: ");
		String startMonth = Main.input.next();
		
//		Take end of time period as input
		System.out.print("Enter end of time period in the format YYYY-MM: ");
		String endMonth = Main.input.next();
		sql = String.format(sql, startMonth, endMonth);
		showResults(sql);
	}
	
//  Total revenue of the streaming service per month
	public static void calculateTotalRevenuePerMonth() {
//		String sql = "SELECT YearMonth, SUM(payment) AS Revenue FROM SubscriptionPayments WHERE ReceivedStatus= 'true' GROUP BY YearMonth;";
		String sql = "SELECT p.YearMonth, COALESCE(SUM(p.payment),0)+COALESCE(Ads,0)*10 AS Revenue FROM SubscriptionPayments as p "
				+ "INNER JOIN (SELECT SUBSTRING(ReleaseDate, 1, 7) AS ReleaseMonth, SUM(AdvertisementCount) AS Ads FROM PodcastEpisode GROUP BY ReleaseMonth) pe "
				+ "ON p.YearMonth = pe.ReleaseMonth "
				+ "WHERE ReceivedStatus= 'true' GROUP BY p.YearMonth;";
		showResults(sql);
	}
	
//  Total revenue of the streaming service per year
	public static void calculateTotalRevenuePerYear() {
//		String sql = "SELECT p.Year, COALESCE(p.payment, 0) AS Revenue "
//				+ "FROM (SELECT SUBSTRING(YearMonth, 1, 4) AS Year, SUM(payment) AS payment FROM SubscriptionPayments WHERE ReceivedStatus = 'true' GROUP BY Year) AS p;";
		String sql = "SELECT p.Year, COALESCE(SUM(p.payment),0)+COALESCE(Ads,0)*10 AS Revenue "
				+ "FROM (SELECT SUBSTRING(YearMonth, 1, 4) AS Year, SUM(payment) AS payment FROM SubscriptionPayments WHERE ReceivedStatus = 'true' GROUP BY Year) AS p "
				+ "INNER JOIN (SELECT SUBSTRING(ReleaseDate, 1, 4) AS ReleaseYear, SUM(AdvertisementCount) AS Ads FROM PodcastEpisode GROUP BY ReleaseYear) pe "
				+ "ON p.Year = pe.ReleaseYear "
				+ "GROUP BY p.Year;";
		showResults(sql);
	}
	
//  Total profit of the streaming service per month
	public static void calculateTotalProfitPerMonth() {
		String sql = "SELECT p.YearMonth, COALESCE(p.payment, 0) + COALESCE(pe.Ads*10) - COALESCE(i1.payment, 0) - COALESCE(i2.payment, 0) AS PaymentDiff "
				+ "FROM (SELECT YearMonth, SUM(payment) AS payment FROM SubscriptionPayments WHERE ReceivedStatus= 'true' GROUP BY YearMonth) AS p "
				+ "LEFT JOIN (SELECT SUBSTRING(ReleaseDate, 1, 7) AS ReleaseMonth, SUM(AdvertisementCount) AS Ads FROM PodcastEpisode GROUP BY ReleaseMonth) pe ON p.YearMonth = pe.ReleaseMonth "
				+ "LEFT JOIN (SELECT YearMonth, SUM(payment) AS payment FROM RoyaltyPayments WHERE PaidStatus= 'true' GROUP BY YearMonth) AS i1 ON p.YearMonth = i1.YearMonth "
				+ "LEFT JOIN (SELECT YearMonth, SUM(payment) AS payment FROM PodcastPayments WHERE PaidStatus= 'true' GROUP BY YearMonth) AS i2 "
				+ "ON p.YearMonth = i2.YearMonth;";
		showResults(sql);
	}
	
//  Total profit of the streaming service per year
	public static void calculateTotalProfitPerYear() {
		String sql = "SELECT p.Year, COALESCE(p.payment, 0) + COALESCE(pe.Ads*10) - COALESCE(i1.payment, 0) - COALESCE(i2.payment, 0) AS Revenue "
				+ "FROM (SELECT SUBSTRING(YearMonth, 1, 4) AS Year, SUM(payment) AS payment FROM SubscriptionPayments WHERE ReceivedStatus = 'true' GROUP BY Year) AS p "
				+ "LEFT JOIN (SELECT SUBSTRING(ReleaseDate, 1, 4) AS ReleaseYear, SUM(AdvertisementCount) AS Ads FROM PodcastEpisode GROUP BY ReleaseYear) pe ON p.Year = pe.ReleaseYear "
				+ "LEFT JOIN (SELECT SUBSTRING(YearMonth, 1, 4) AS Year, SUM(payment) AS payment FROM RoyaltyPayments WHERE PaidStatus = 'true' GROUP BY Year) AS i1 ON p.Year = i1.Year "
				+ "LEFT JOIN (SELECT SUBSTRING(YearMonth, 1, 4) AS Year, SUM(payment) AS payment FROM PodcastPayments WHERE PaidStatus = 'true' GROUP BY Year) AS i2 "
				+ "ON p.Year = i2.Year;";
		showResults(sql);
	}
	
//  Report all songs given an artist
	public static void findSongsWithArtist() {
		String sql = "SELECT * FROM Song WHERE SongID IN (SELECT SongID FROM PerformedBy WHERE ArtistID=%d);";
		
//		Take ArtistID as input
		System.out.print("Enter artist ID: ");
		int artistID = Main.input.nextInt();
		
		sql = String.format(sql, artistID);
		showResults(sql);
	}
	
//  Report all songs given an album
	public static void findSongsInAlbum() {
		String sql = "SELECT * FROM Song WHERE AlbumID=%d;";
		
//		Take AlbumID as input
		System.out.print("Enter album ID: ");
		int albumID = Main.input.nextInt();
		
		sql = String.format(sql, albumID);
		showResults(sql);
	}
	
//  Report all podcast episodes given a podcast
	public static void findEpisodesInPodcast() {
		String sql = "SELECT * FROM PodcastEpisode WHERE PodcastID=%d;";
		
//		Take AlbumID as input
		System.out.print("Enter podcast ID: ");
		int podcastID = Main.input.nextInt();
		
		sql = String.format(sql, podcastID);
		showResults(sql);
	}

//	Method to execute query and print results
	public static void showResults(String sql) {
		try {
			Statement stmt = DBConnect.statement;
			if (stmt != null) {
				ResultSet result = stmt.executeQuery(sql);
				DBTablePrinter.printResultSet(result);
				result.close();
			} else {
				throw new SQLException("Connection is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printHeader(ResultSet rs, char c, int width) throws Exception 
	{
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int nCols = rsmd.getColumnCount();
	    for(int i = 1; i <= nCols; ++i)
	          System.out.printf("%-10s ", rsmd.getColumnLabel(i));

	     System.out.printf("\n");
	     for(int i = 0; i < width; ++i)
	        System.out.printf("%c", c);
	     System.out.println("");
	}
	
	
	public static void printRows(ResultSet rs, char c, int width) throws Exception 
	{
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int nCols = rsmd.getColumnCount();
		while (rs.next()) {
			for(int i = 1; i <= nCols; ++i)
		          System.out.printf("%-10s ", rs.getString(i));

		     System.out.printf("\n");
		     for(int i = 0; i < width; ++i)
		        System.out.printf("%c", c);
	       System.out.println("");
	   }
	}
}
