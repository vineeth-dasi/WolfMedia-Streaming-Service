import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InformationProcessing {

	public static void EnterSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Song ID as input
				System.out.print("Enter Song ID: ");
				int songID = Main.input.nextInt();

				// Take Song title as input
				System.out.print("Enter Song Title: ");
				Main.input.nextLine();
				String songTitle = Main.input.nextLine();

				// Take Song duration as input
				System.out.print("Enter Song Duration: ");
				String songduration = Main.input.next();

				// Take albumid as input
				System.out.print("Enter AlbumID: ");
				int AlbumID = Main.input.nextInt();

				// Take tracknumber as input
				System.out.print("Enter TrackNumber: ");
				int trackNumber = Main.input.nextInt();

				// Take releasedate as input
				System.out.print("Enter ReleaseDate: ");
				String releaseDate = Main.input.next();

				// Take releasecountry as input
				System.out.print("Enter Release Country: ");
				Main.input.nextLine();
				String releaseCountry = Main.input.nextLine();

				// Take language as input
				System.out.print("Enter Language: ");
				String language = Main.input.next();

				// Take royaltyrate as input
				System.out.print("Enter Royalty Rate: ");
				float royaltyrate = Main.input.nextFloat();

				String insQuery = "INSERT INTO Song (SongID, SongTitle, Duration, AlbumID, TrackNumber, ReleaseDate, ReleaseCountry, Language, RoyaltyRate) VALUES (%d, '%s', '%s', %d, %d, '%s', '%s', '%s', %f);";
				insQuery = String.format(insQuery, songID, songTitle, songduration, AlbumID, trackNumber, releaseDate,
						releaseCountry, language, royaltyrate);

				DBConnect.statement.executeUpdate(insQuery);

				System.out.println("Song added succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		}

		finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	
	
	
	
	
	
	
//	public static void UpdatePodcastHostInfo() throws Exception {
//		try {
//			if (DBConnect.connection != null) {
//				// Take Host ID as input
//				System.out.print("Enter Host ID: ");
//				int hostID = Main.input.nextInt();
//
//				String selectQuery = "SELECT * FROM PodcastHost WHERE HostID=%d;";
//				selectQuery = String.format(selectQuery, hostID);
//				ResultSet result = DBConnect.statement.executeQuery(selectQuery);
//			    ResultSetMetaData rsmd = result.getMetaData();
//				int nCols = rsmd.getColumnCount();
//				String[] attr = new String[nCols-1];
//				if (result.next()) {
//					for(int i = 2; i <= nCols; ++i)
//				          attr[i-2] = result.getString(i);
//				}
//				result.close();
//				
//				int choice;
//				String UpdateQuery = "UPDATE PodcastHost SET FirstName= '%s', LastName= '%s', Phone= '%s', Email= '%s', City= '%s' WHERE HostID=%d;";
//				boolean done = false;
//				while (!done) {
//					System.out.println("1. FirstName");
//					System.out.println("2. LastName");
//					System.out.println("3. Phone");
//					System.out.println("4. Email");
//					System.out.println("5. City");
//					System.out.println("6. Exit update");
//					System.out.print("Enter your Choice: ");
//					choice = Main.input.nextInt();
//					switch(choice) {
//					case 1:
//						System.out.print("Enter first name: ");
//						attr[choice-1] = Main.input.next();
//						break;
//					case 2:
//						System.out.print("Enter last name: ");
//						attr[choice-1] = Main.input.next();
//						break;
//					case 3:
//						System.out.print("Enter phone: ");
//						attr[choice-1] = Main.input.next();
//						break;
//					case 4:
//						System.out.print("Enter email: ");
//						attr[choice-1] = Main.input.next();
//						break;
//					case 5:
//						System.out.print("Enter city: ");
//						attr[choice-1] = Main.input.next();
//						break;
//					case 6:
//						done = true;
//						break;
//					default:
//						System.out.println("\nPlease enter a valid choice from 1 to 6");
//						break;
//					}
//				}
//
//				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], hostID);
//
//				DBConnect.statement.executeUpdate(UpdateQuery);
//
//				System.out.println("Podcast Host Updated succesfully");
//				
//			} else {
//				throw new Exception("Connection is null");
//			}
//		} catch (SQLException e) {
//			System.out.print("Failure: " + e);
//		} finally {
//			DBConnect.connection.setAutoCommit(true);
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void UpdateSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Song ID as input
				System.out.print("Enter Song ID to Update: ");
				int songID = Main.input.nextInt();
				
				System.out.println("Update ");
				
				
				ResultSet rs = DBConnect.statement.executeQuery("SELECT SongID, SongTitle, Duration, AlbumID, TrackNumber, ReleaseDate, ReleaseCountry, Language, RoyaltyRate FROM Song WHERE SongID="+songID+";");
				
				if (!rs.isBeforeFirst() ) {    
				    System.out.println("SongId does not Exist, please try again");
				    return;
				} 
				
					while(rs.next())
					{
				    int songID_org = rs.getInt("SongID");
				    String songTitle_org = rs.getString("SongTitle");
				    String duration_org = rs.getString("Duration");
				    int albumID_org = rs.getInt("AlbumID");
				    int trackNumber_org = rs.getInt("TrackNumber");
				    String releaseDate_org = rs.getString("ReleaseDate");
				    String releaseCountry_org = rs.getString("ReleaseCountry");
				    String language_org = rs.getString("Language");
				    float royaltyRate_org = rs.getFloat("RoyaltyRate");
				    
				    System.out.println("Song ID: " + songID_org);
				    System.out.println("Song title: " + songTitle_org);
				    System.out.println("Duration: " + duration_org);
				    System.out.println("Album ID: " + albumID_org);
				    System.out.println("Track number: " + trackNumber_org);
				    System.out.println("Release date: " + releaseDate_org);
				    System.out.println("Release country: " + releaseCountry_org);
				    System.out.println("Language: " + language_org);
				    System.out.println("Royalty rate: " + royaltyRate_org);
				    
				    System.out.println("\n\n");
				
				
				System.out.println("Enter updated value for Each Attribute else press Enter");
				
				Main.input.nextLine();
				
				// Take Song title as input
				System.out.print("Enter Song Title: "+"old Value:"+songTitle_org+": ");
				String songTitle = Main.input.nextLine();
				
				if(songTitle.isEmpty())
					songTitle=songTitle_org;

				// Take Song duration as input
				System.out.print("Enter Song Duration: "+"old Value:"+duration_org+": ");
				String songduration = Main.input.next();
				if(songTitle.isEmpty())
					songTitle=songTitle_org;

				String updateQuery = "UPDATE Song SET SongTitle='%s',Duration='%s' WHERE SongID=%d;";
				updateQuery = String.format(updateQuery, songTitle, songduration, songID);

				int res=DBConnect.statement.executeUpdate(updateQuery);
				
				if(res>=1)
					System.out.println("song Updated succesfully");
				else
					System.out.println("0 rows affected, Song ID does not exist");

					}
			} else {
				throw new Exception("Connection is null");
			}
		}catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		}  catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeleteSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Song ID as input
				System.out.print("Enter Song ID: ");
				int songID = Main.input.nextInt();

				String deleteQuery = "DELETE FROM Song WHERE SongID=%d;";
				deleteQuery = String.format(deleteQuery, songID);

				int res=DBConnect.statement.executeUpdate(deleteQuery);
				
				if(res>=1)
					System.out.println("song deleted succesfully");
				else
					System.out.println("0 rows affected, Song ID does not exist");
			} else {
				throw new Exception("Connection is null");
			}
		}catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void EnterArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = Main.input.nextInt();

				// Take Artist Name as input
				System.out.print("Enter Artist Name: ");
				String ArtistName = Main.input.next();

				// Take Status as input
				System.out.print("Enter Status: ");
				String Status = Main.input.next();

				// Take Type as input
				System.out.print("Enter Type: ");
				String type = Main.input.next();

				// Take country as input
				System.out.print("Enter Country: ");
				String country = Main.input.next();

				// Take Primary Genre as input
				System.out.print("Enter Primary Genre: ");
				String primaryGenre = Main.input.next();

				// Take monthly listeners as input
				System.out.print("Enter monthly listeners: ");
				int MonthlyListeners = Main.input.nextInt();

				String InsertQuery = "INSERT INTO Artist (ArtistID, ArtistName, Status, Type, Country, PrimaryGenre, MonthlyListeners) VALUES (%d, '%s', '%s', '%s', '%s', '%s', %d);";
				InsertQuery = String.format(InsertQuery, artistID, ArtistName, Status, type, country, primaryGenre,
						MonthlyListeners);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = Main.input.nextInt();

				// Take country as input
				System.out.print("Enter Country: ");
				String country = Main.input.next();

				String UpdateQuery = "UPDATE Artist SET Country= '%s' WHERE ArtistID=%d;";
				UpdateQuery = String.format(UpdateQuery, country, artistID);

				DBConnect.statement.executeUpdate(UpdateQuery);

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

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = Main.input.nextInt();

				String deleteQuery = "DELETE FROM Artist WHERE ArtistID=%d;";
				deleteQuery = String.format(deleteQuery, artistID);

				DBConnect.statement.executeUpdate(deleteQuery);

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

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = Main.input.nextInt();

				// Take first Name as input
				System.out.print("Enter First Name: ");
				String firstName = Main.input.next();

				// Take last name as input
				System.out.print("Enter Last Name: ");
				String lastName = Main.input.next();

				// Take phone as input
				System.out.print("Enter phone: ");
				String phone = Main.input.next();

				// Take email as input
				System.out.print("Enter Email: ");
				String email = Main.input.next();

				// Take city as input
				System.out.print("Enter city: ");
				String city = Main.input.next();

				String InsertQuery = "INSERT INTO PodcastHost (HostID, FirstName, LastName, Phone, Email, City) VALUES (%d, '%s', '%s', '%s', '%s', '%s');";
				InsertQuery = String.format(InsertQuery, hostID, firstName, lastName, phone, email, city);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = Main.input.nextInt();

				// Take city as input
				System.out.print("Enter city: ");
				String city = Main.input.next();

				String UpdateQuery = "UPDATE PodcastHost SET City= '%s' WHERE HostID=%d;";
				UpdateQuery = String.format(UpdateQuery, city, hostID);

				DBConnect.statement.executeUpdate(UpdateQuery);

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

				// Take Host ID as input
				System.out.print("Enter Host ID: ");
				int hostID = Main.input.nextInt();

				String DeleteQuery = "DELETE FROM PodcastHost WHERE HostID=%d;";
				DeleteQuery = String.format(DeleteQuery, hostID);

				DBConnect.statement.executeUpdate(DeleteQuery);

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

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = Main.input.nextInt();

				// Take Episode title Name as input
				System.out.print("Enter Title: ");
				String episodeTitle = Main.input.next();

				// Take PodcastID as input
				System.out.print("Enter PodcastId: ");
				int podcastID = Main.input.nextInt();

				// Take Duration as input
				System.out.print("Enter Duration: ");
				int Duration = Main.input.nextInt();

				// Take release date as input
				System.out.print("Enter Release Date: ");
				String releasedate = Main.input.next();

				// Take listening count as input
				System.out.print("Enter Listening Count: ");
				int listeningCount = Main.input.nextInt();

				// Take Advertisement count as input
				System.out.print("Enter Advertisement Count: ");
				int AdvertisementCount = Main.input.nextInt();

				String InsertQuery = "INSERT INTO PodcastEpisode (EpisodeID, EpisodeTitle, PodcastID, Duration, ReleaseDate, ListeningCount, AdvertisementCount) VALUES (%d, '%s', %d, %d, '%s', %d, %d);";
				InsertQuery = String.format(InsertQuery, episodeID, episodeTitle, podcastID, Duration, releasedate,
						listeningCount, AdvertisementCount);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = Main.input.nextInt();

				// Take Duration as input
				System.out.print("Enter Duration: ");
				int Duration = Main.input.nextInt();

				String UpdateQuery = "UPDATE PodcastEpisode SET Duration=%d WHERE EpisodeID=%d;";
				UpdateQuery = String.format(UpdateQuery, Duration, episodeID);

				DBConnect.statement.executeUpdate(UpdateQuery);

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

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = Main.input.nextInt();

				String DeleteQuery = "DELETE FROM PodcastEpisode WHERE EpisodeID=%d;";
				DeleteQuery = String.format(DeleteQuery, episodeID);

				DBConnect.statement.executeUpdate(DeleteQuery);

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

				// Take song ID as input
				System.out.print("Enter song ID: ");
				int songID = Main.input.nextInt();

				// Take Artist Id as input
				System.out.print("Enter Artist ID: ");
				int ArtistId = Main.input.nextInt();

				// Take main artist status as input
				System.out.print("Enter MainArtist Status: ");
				String mainArtistStatus = Main.input.next();

				String InsertQuery = "INSERT INTO PerformedBy (SongID, ArtistID, MainArtistStatus) VALUES (%d, %d, '%f');";
				InsertQuery = String.format(InsertQuery, songID, ArtistId, mainArtistStatus);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = Main.input.nextInt();

				// Take startdate as input
				System.out.print("Enter start date: ");
				String startdate = Main.input.next();

				// Take enddate as input
				System.out.print("Enter end date: ");
				String enddate = Main.input.next();

				// Take label name as input
				System.out.print("Enter label name: ");
				String labelname = Main.input.next();

				String InsertQuery = "INSERT INTO Contract (ArtistID, StartDate, EndDate, LabelName) VALUES (%d, '%s', '%s', '%s');";
				InsertQuery = String.format(InsertQuery, artistID, startdate, enddate, labelname);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take episode ID as input
				System.out.print("Enter episode ID: ");
				int episodeID = Main.input.nextInt();

				// Take episode title as input
				System.out.print("Enter Episode Title: ");
				String episodeTitle = Main.input.next();

				// Take podcast ID as input
				System.out.print("Enter Podcast ID: ");
				int podcastId = Main.input.nextInt();

				// Take Duration as input
				System.out.print("Enter Duration: ");
				String duration = Main.input.next();

				// Take release data as input
				System.out.print("Enter release date: ");
				String releasedate = Main.input.next();

				// Take listening count as input
				System.out.print("Enter listening count: ");
				int listeningCount = Main.input.nextInt();

				// Take Advertisement count as input
				System.out.print("Enter Adverstisement count: ");
				int AdvertisementCount = Main.input.nextInt();

				String InsertQuery = "INSERT INTO PodcastEpisode (EpisodeID, EpisodeTitle, PodcastID, Duration, ReleaseDate, ListeningCount, AdvertisementCount) VALUES (%d, '%s', %d, %d, '%s', %d, %d);";
				InsertQuery = String.format(InsertQuery, episodeID, episodeTitle, podcastId, duration, releasedate,
						listeningCount, AdvertisementCount);

				DBConnect.statement.executeUpdate(InsertQuery);

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

				// Take Podcast ID as input
				System.out.print("Enter Podcast ID: ");
				int podcastID = Main.input.nextInt();

				// Take HostId as input
				System.out.print("Enter HostId: ");
				int HostId = Main.input.nextInt();

				String InsertQuery = "INSERT INTO HostedBy (PodcastID, HostID) VALUES (%d, %d);";
				InsertQuery = String.format(InsertQuery, podcastID, HostId);

				DBConnect.statement.executeUpdate(InsertQuery);

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
