import java.sql.SQLException;
import java.util.Scanner;

public class InformationProcessing {

	public static void EnterSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Song ID as input
				System.out.print("Enter Song ID: ");
				int songID = input.nextInt();

				// Take Song title as input
				System.out.print("Enter Song Title: ");
				String songTitle = input.next();

				// Take Song duration as input
				System.out.print("Enter Song Duration: ");
				String songduration = input.next();

				// Take albumid as input
				System.out.print("Enter AlbumID: ");
				int AlbumID = input.nextInt();

				// Take tracknumber as input
				System.out.print("Enter TrackNumber: ");
				int trackNumber = input.nextInt();

				// Take releasedate as input
				System.out.print("Enter ReleaseDate: ");
				String releaseDate = input.next();

				// Take releasecountry as input
				System.out.print("Enter Release Country: ");
				String releaseCountry = input.next();

				// Take language as input
				System.out.print("Enter Language: ");
				String language = input.next();

				// Take royaltyrate as input
				System.out.print("Enter Royalty Rate: ");
				float royaltyrate = input.nextFloat();


				String insQuery = "INSERT INTO Song (SongID, SongTitle, Duration, AlbumID, TrackNumber, ReleaseDate, ReleaseCountry, Language, RoyaltyRate) VALUES (%d, '%s', '%s', %d, %d, '%s', '%s', '%s', %f);";
				insQuery = String.format(insQuery, songID, songTitle,songduration,AlbumID,trackNumber,releaseDate,releaseCountry,language,royaltyrate);
				
				DBConnect.statement.executeUpdate(insQuery);

				input.close();
				System.out.println("Song added succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdateSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Song ID as input
				System.out.print("Enter Song ID: ");
				int songID = input.nextInt();

				// Take Song title as input
				System.out.print("Enter Song Title: ");
				String songTitle = input.next();

				// Take Song duration as input
				System.out.print("Enter Song Duration: ");
				String songduration = input.next();

				String updateQuery = "UPDATE Song SET SongTitle='%s',Duration='%s' WHERE SongID=%d;";
				updateQuery = String.format(updateQuery, songTitle, songduration, songID);

				DBConnect.statement.executeUpdate(updateQuery);

				input.close();
				System.out.println("SongInfo updated succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeleteSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Song ID as input
				System.out.print("Enter Song ID: ");
				int songID = input.nextInt();

				String deleteQuery = "DELETE FROM Song WHERE SongID=%d;";
				deleteQuery = String.format(deleteQuery, songID);

				DBConnect.statement.executeUpdate(deleteQuery);

				input.close();
				System.out.println("song deleted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void EnterArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = input.nextInt();

				// Take Artist Name as input
				System.out.print("Enter Artist Name: ");
				String ArtistName = input.next();

				// Take Status as input
				System.out.print("Enter Status: ");
				String Status = input.next();

				// Take Type as input
				System.out.print("Enter Type: ");
				String type = input.next();

				// Take country as input
				System.out.print("Enter Country: ");
				String country = input.next();

				// Take Primary Genre as input
				System.out.print("Enter Primary Genre: ");
				String primaryGenre = input.next();

				// Take monthly listeners as input
				System.out.print("Enter monthly listeners: ");
				int MonthlyListeners = input.nextInt();

				String InsertQuery = "INSERT INTO Artist (ArtistID, ArtistName, Status, Type, Country, PrimaryGenre, MonthlyListeners) VALUES (%d, '%s', '%s', '%s', '%s', '%s', %d);";
				InsertQuery = String.format(InsertQuery, artistID, ArtistName, Status, type, country, primaryGenre,
						MonthlyListeners);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("Artist Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdateArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = input.nextInt();

				// Take country as input
				System.out.print("Enter Country: ");
				String country = input.next();

				String UpdateQuery = "UPDATE Artist SET Country= '%s' WHERE ArtistID=%d;";
				UpdateQuery = String.format(UpdateQuery, country, artistID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				input.close();
				System.out.println("Artist Updated succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeleteArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = input.nextInt();

				String deleteQuery = "DELETE FROM Artist WHERE ArtistID=%d;";
				deleteQuery = String.format(deleteQuery, artistID);

				DBConnect.statement.executeUpdate(deleteQuery);

				input.close();
				System.out.println("Artist Deleted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void EnterPodcastHostInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = input.nextInt();

				// Take first Name as input
				System.out.print("Enter First Name: ");
				String firstName = input.next();

				// Take last name as input
				System.out.print("Enter Last Name: ");
				String lastName = input.next();

				// Take phone as input
				System.out.print("Enter phone: ");
				String phone = input.next();

				// Take email as input
				System.out.print("Enter Email: ");
				String email = input.next();

				// Take city as input
				System.out.print("Enter city: ");
				String city = input.next();

				String InsertQuery = "INSERT INTO PodcastHost (HostID, FirstName, LastName, Phone, Email, City) VALUES (%d, '%s', '%s', '%s', '%s', '%s');";
				InsertQuery = String.format(InsertQuery, hostID, firstName, lastName, phone, email, city);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("Podcast Host Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdatePodcastHostInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = input.nextInt();

				// Take city as input
				System.out.print("Enter city: ");
				String city = input.next();

				String UpdateQuery = "UPDATE PodcastHost SET City= '%s' WHERE HostID=%d;";
				UpdateQuery = String.format(UpdateQuery, city, hostID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				input.close();
				System.out.println("Podcast Host Updated succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeletePodcastHostInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = input.nextInt();

				String DeleteQuery = "DELETE FROM PodcastHost WHERE HostID=%d;";
				DeleteQuery = String.format(DeleteQuery, hostID);

				DBConnect.statement.executeUpdate(DeleteQuery);

				input.close();
				System.out.println("Podcast Host Deleted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void EnterPodcastEpisodeInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = input.nextInt();

				// Take Episode title Name as input
				System.out.print("Enter Title: ");
				String episodeTitle = input.next();

				// Take PodcastID as input
				System.out.print("Enter PodcastId: ");
				int podcastID = input.nextInt();

				// Take Duration as input
				System.out.print("Enter Duration: ");
				int Duration = input.nextInt();

				// Take release date as input
				System.out.print("Enter Release Date: ");
				String releasedate = input.next();

				// Take listening count as input
				System.out.print("Enter Listening Count: ");
				int listeningCount = input.nextInt();

				// Take Advertisement count as input
				System.out.print("Enter Advertisement Count: ");
				int AdvertisementCount = input.nextInt();

				String InsertQuery = "INSERT INTO PodcastEpisode (EpisodeID, EpisodeTitle, PodcastID, Duration, ReleaseDate, ListeningCount, AdvertisementCount) VALUES (%d, '%s', %d, %d, '%s', %d, %d);";
				InsertQuery = String.format(InsertQuery, episodeID, episodeTitle, podcastID, Duration, releasedate,listeningCount,
						AdvertisementCount);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("Podcast Episode Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdatePodcastEpisodeInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = input.nextInt();

				// Take Duration as input
				System.out.print("Enter Duration: ");
				int Duration = input.nextInt();

				String UpdateQuery = "UPDATE PodcastEpisode SET Duration=%d WHERE EpisodeID=%d;";
				UpdateQuery = String.format(UpdateQuery, Duration, episodeID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				input.close();
				System.out.println("Podcast Episode Updated succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeletePodcastEpisodeInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = input.nextInt();

				String DeleteQuery = "DELETE FROM PodcastEpisode WHERE EpisodeID=%d;";
				DeleteQuery = String.format(DeleteQuery, episodeID);

				DBConnect.statement.executeUpdate(DeleteQuery);

				input.close();
				System.out.println("Podcast Episode Deleted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	
	public static void AssignSongsToArtists() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take song ID as input
				System.out.print("Enter song ID: ");
				int songID = input.nextInt();
				
				// Take Artist Id as input
				System.out.print("Enter Artist ID: ");
				int ArtistId = input.nextInt();
				
				
				// Take main artist status as input
				System.out.print("Enter MainArtist Status: ");
				String mainArtistStatus = input.next();
				

				String InsertQuery = "INSERT INTO PerformedBy (SongID, ArtistID, MainArtistStatus) VALUES (%d, %d, '%f');";
				InsertQuery = String.format(InsertQuery, songID,ArtistId,mainArtistStatus);
				
				
				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("song assigned to album succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	
	public static void AssignArtistToRecordLabel() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = input.nextInt();
				
				// Take startdate as input
				System.out.print("Enter start date: ");
				String startdate = input.next();
				
				// Take enddate as input
				System.out.print("Enter end date: ");
				String enddate = input.next();
				
				// Take label name as input
				System.out.print("Enter label name: ");
				String labelname = input.next();
				

				String InsertQuery = "INSERT INTO Contract (ArtistID, StartDate, EndDate, LabelName) VALUES (%d, '%s', '%s', '%s');";
				InsertQuery = String.format(InsertQuery, artistID,startdate,enddate,labelname);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("artist assigned to record label succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	
	public static void AssignPodcastEpisodeToPodcast() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take episode ID as input
				System.out.print("Enter episode ID: ");
				int episodeID = input.nextInt();
				
				// Take episode title as input
				System.out.print("Enter Episode Title: ");
				String episodeTitle = input.next();
				
				// Take podcast ID as input
				System.out.print("Enter Podcast ID: ");
				int podcastId = input.nextInt();
				
				// Take Duration as input
				System.out.print("Enter Duration: ");
				String duration = input.next();
				
				// Take release data as input
				System.out.print("Enter release date: ");
				String releasedate = input.next();
				
				// Take listening count as input
				System.out.print("Enter listening count: ");
				int listeningCount = input.nextInt();
				
				// Take Advertisement count as input
				System.out.print("Enter Adverstisement count: ");
				int AdvertisementCount = input.nextInt();
				
				String InsertQuery = "INSERT INTO PodcastEpisode (EpisodeID, EpisodeTitle, PodcastID, Duration, ReleaseDate, ListeningCount, AdvertisementCount) VALUES (%d, '%s', %d, %d, '%s', %d, %d);";
				InsertQuery = String.format(InsertQuery,episodeID,episodeTitle,podcastId,duration,releasedate,listeningCount,AdvertisementCount);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("Assigned podcast Episode to podacast succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	
	public static void AssignPodcastHostToPodcast() throws Exception {
		try {
			if (DBConnect.connection != null) {
				Scanner input = new Scanner(System.in);

				// Take Podcast ID as input
				System.out.print("Enter Podcast ID: ");
				int podcastID = input.nextInt();
				
				// Take HostId as input
				System.out.print("Enter HostId: ");
				int HostId = input.nextInt();
				
				
				String InsertQuery = "INSERT INTO HostedBy (PodcastID, HostID) VALUES (%d, %d);";
				InsertQuery = String.format(InsertQuery,podcastID,HostId);

				DBConnect.statement.executeUpdate(InsertQuery);

				input.close();
				System.out.println("Assigned podcast host to podacast succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	

}
