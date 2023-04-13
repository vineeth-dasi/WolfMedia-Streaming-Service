import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
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

	public static void UpdateSongInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Song ID as input
				System.out.print("Enter Song ID to Update: ");
				int songID = Main.input.nextInt();

				String selectQuery = "SELECT SongID, SongTitle, Duration, AlbumID, TrackNumber, ReleaseDate, ReleaseCountry, Language, RoyaltyRate FROM Song WHERE SongID="
						+ songID + ";";
				selectQuery = String.format(selectQuery, songID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No song id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE Song SET SongTitle='%s',Duration='%s',AlbumID='%s', TrackNumber='%s', ReleaseDate='%s', ReleaseCountry='%s', Language='%s', RoyaltyRate='%s' WHERE SongID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. songTitle" + "[" + orignal[0] + "]");
					System.out.println("2. Duration" + "[" + orignal[1] + "]");
					System.out.println("3. AlbumId" + "[" + orignal[2] + "]");
					System.out.println("4. TrackNumber" + "[" + orignal[3] + "]");
					System.out.println("5. Release date" + "[" + orignal[4] + "]");
					System.out.println("6. Release country" + "[" + orignal[5] + "]");
					System.out.println("7. Language" + "[" + orignal[6] + "]");
					System.out.println("8. Royalty rate" + "[" + orignal[7] + "]");
					System.out.println("9. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter song title : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter duration: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter albumid: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 4:
						System.out.print("Enter TrackNumber: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 5:
						System.out.print("Enter release date: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 6:
						System.out.print("Enter release country: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 7:
						System.out.print("Enter Language: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 8:
						System.out.print("Enter royalty rate: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 9:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 9");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to song");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6],
						attr[7], songID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				System.out.println("Song Updated succesfully");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
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

				int res = DBConnect.statement.executeUpdate(deleteQuery);

				if (res >= 1)
					System.out.println("song deleted succesfully");
				else
					System.out.println("0 rows affected, Song ID does not exist");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	///////////////////////////////////////////////////////////////////////////////

	public static void EnterAlbumInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take album ID as input
				System.out.print("Enter Album ID: ");
				int albumID = Main.input.nextInt();

				// Take album Name as input
				System.out.print("Enter album Name: ");
				Main.input.nextLine();
				String albumName = Main.input.nextLine();

				// Take release year as input
				System.out.print("Enter release year: ");
				int releaseyear = Main.input.nextInt();

				// Take edition as input
				System.out.print("Enter edition: ");
				Main.input.nextLine();
				String edition = Main.input.nextLine();

				String InsertQuery = "INSERT INTO Album (AlbumID, AlbumName, ReleaseYear, Edition) VALUES (%d, '%s', %d, '%s');";
				InsertQuery = String.format(InsertQuery, albumID, albumName, releaseyear, edition);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("Album Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdateAlbumInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take album ID as input
				System.out.print("Enter album ID to Update: ");
				int albumID = Main.input.nextInt();

				String selectQuery = "SELECT AlbumID, AlbumName, ReleaseYear, Edition FROM Album WHERE AlbumID="
						+ albumID + ";";
				selectQuery = String.format(selectQuery, albumID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No album id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE Album SET AlbumName='%s',ReleaseYear='%s',Edition='%s' WHERE AlbumID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. AlbumName" + "[" + orignal[0] + "]");
					System.out.println("2. Release Year" + "[" + orignal[1].substring(0, 4) + "]");
					System.out.println("3. Edition" + "[" + orignal[2] + "]");
					System.out.println("4. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter album name : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter release year: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter Edition: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 4:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 4");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to Album");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1].substring(0, 4), attr[2], albumID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				System.out.println("Album Updated succesfully");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeleteAlbumInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Episode ID as input
				System.out.print("Enter album ID: ");
				int albumnID = Main.input.nextInt();

				String DeleteQuery = "DELETE FROM Album WHERE AlbumID=%d;";
				DeleteQuery = String.format(DeleteQuery, albumnID);

				int res = DBConnect.statement.executeUpdate(DeleteQuery);

				if (res >= 1)
					System.out.println("album deleted succesfully");
				else
					System.out.println("0 rows affected, album ID does not exist");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public static void EnterArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Artist ID as input
				System.out.print("Enter Artist ID: ");
				int artistID = Main.input.nextInt();

				// Take Artist Name as input
				System.out.print("Enter Artist Name: ");
				Main.input.nextLine();
				String ArtistName = Main.input.nextLine();

				// Take Status as input
				System.out.print("Enter Status: ");
				String Status = Main.input.next();

				// Take Type as input
				System.out.print("Enter Type: ");
				Main.input.nextLine();
				String type = Main.input.nextLine();

				// Take country as input
				System.out.print("Enter Country: ");
				String country = Main.input.nextLine();

				// Take Primary Genre as input
				System.out.print("Enter Primary Genre: ");
				int primaryGenre = Main.input.nextInt();

				// Take monthly listeners as input
				System.out.print("Enter monthly listeners: ");
				int MonthlyListeners = Main.input.nextInt();

				String InsertQuery = "INSERT INTO Artist (ArtistID, ArtistName, Status, Type, Country, PrimaryGenre, MonthlyListeners) VALUES (%d, '%s', '%s', '%s', '%s', %d, %d);";
				InsertQuery = String.format(InsertQuery, artistID, ArtistName, Status, type, country, primaryGenre,
						MonthlyListeners);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("Artist Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}

	}

	/// isuue all 5 columns not coming
	public static void UpdateArtistInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take artist ID as input
				System.out.print("Enter artist ID to Update: ");
				int artistID = Main.input.nextInt();

				String selectQuery = "SELECT ArtistID,ArtistName,Status,Type,Country,PrimaryGenre, MonthlyListeners FROM Artist WHERE ArtistID="
						+ artistID + ";";
				selectQuery = String.format(selectQuery, artistID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No artist id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE Artist SET ArtistName='%s',Status='%s',Type='%s',Country='%s',PrimaryGenre='%s',MonthlyListeners='%s' WHERE ArtistID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. ArtistName" + "[" + orignal[0] + "]");
					System.out.println("2. Status" + "[" + orignal[1] + "]");
					System.out.println("3. Type" + "[" + orignal[2] + "]");
					System.out.println("4. Country" + "[" + orignal[3] + "]");
					System.out.println("5. PrimaryGenre" + "[" + orignal[4] + "]");
					System.out.println("6. MonthlyListeners" + "[" + orignal[5] + "]");
					System.out.println("7. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter Artist name : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter status : ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter Type: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 4:
						System.out.print("Enter Country: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 5:
						System.out.print("Enter PrimaryGenre: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 6:
						System.out.print("Enter Monthly listeners: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 7:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 7");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to Artist");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5],
						artistID);

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

				int res = DBConnect.statement.executeUpdate(deleteQuery);

				if (res >= 1)
					System.out.println("Artist deleted succesfully");
				else
					System.out.println("0 rows affected, Artist ID does not exist");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	public static void EnterPodcastInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take podcast ID as input
				System.out.print("Enter podcast ID: ");
				int podcastID = Main.input.nextInt();

				// Take podcast Name as input
				System.out.print("Enter podcast Name: ");
				Main.input.nextLine();
				String podcastName = Main.input.nextLine();

				// Take language as input
				System.out.print("Enter Language: ");
				String language = Main.input.next();

				// Take country as input
				System.out.print("Enter country: ");
				String country = Main.input.next();

				// Take episodecount
				System.out.print("Enter episodecount: ");
				int episodecount = Main.input.nextInt();

				// Take rating input
				System.out.print("Enter rating: ");
				Float rating = Main.input.nextFloat();

				// Take total sub input
				System.out.print("Enter Total subscribers: ");
				int totalsubscribers = Main.input.nextInt();

				// take fee per episode
				System.out.print("Enter fee per episode: ");
				Float feeperepisode = Main.input.nextFloat();

				String InsertQuery = "INSERT INTO Podcast (PodcastID, PodcastName, Language, Country, EpisodeCount, Rating, TotalSubscribers, FeePerEpisode) VALUES (%d, '%s', '%s', '%s', %d, %.1f,%d, %.2f);";
				InsertQuery = String.format(InsertQuery, podcastID, podcastName, language, country, episodecount,
						rating, totalsubscribers, feeperepisode);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("Podcast Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void UpdatePodcastInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Podcast ID as input
				System.out.print("Enter Podcast ID to Update: ");
				int podcastID = Main.input.nextInt();

				String selectQuery = "SELECT PodcastID,PodcastName, Language, Country, EpisodeCount, Rating, TotalSubscribers, FeePerEpisode FROM Podcast WHERE PodcastID="
						+ podcastID + ";";
				selectQuery = String.format(selectQuery, podcastID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No podcast id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE Podcast SET PodcastName='%s', Language='%s', Country='%s', EpisodeCount='%s', Rating='%s', TotalSubscribers='%s', FeePerEpisode='%s' WHERE PodcastID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. PodcastName" + "[" + orignal[0] + "]");
					System.out.println("2. Language" + "[" + orignal[1] + "]");
					System.out.println("3. country" + "[" + orignal[2] + "]");
					System.out.println("4. Episode count" + "[" + orignal[3] + "]");
					System.out.println("5. Rating" + "[" + orignal[4] + "]");
					System.out.println("6. Total Subscribers" + "[" + orignal[5] + "]");
					System.out.println("7. Fee per Episode" + "[" + orignal[6] + "]");
					System.out.println("8. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter podcast name : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter Language : ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter Country: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 4:
						System.out.print("Enter Episode count: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 5:
						System.out.print("Enter rating: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 6:
						System.out.print("Enter Total Subscribers: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 7:
						System.out.print("Enter Fee Per Episode: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 8:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 8");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to Podcast");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6],
						podcastID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				System.out.println("podcast Updated succesfully");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeletePodcastInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take podcast ID as input
				System.out.print("Enter podcast ID: ");
				int podcastID = Main.input.nextInt();

				String DeleteQuery = "DELETE FROM Podcast WHERE PodcastID=%d;";
				DeleteQuery = String.format(DeleteQuery, podcastID);

				int res = DBConnect.statement.executeUpdate(DeleteQuery);

				if (res >= 1)
					System.out.println("podcast deleted succesfully");
				else
					System.out.println("0 rows affected, podcast ID does not exist");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	///////////////////////////////////////////////////////////////////

	public static void EnterPodcastEpisodeInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take Episode ID as input
				System.out.print("Enter Episode ID: ");
				int episodeID = Main.input.nextInt();

				// Take Episode title Name as input
				System.out.print("Enter Title: ");
				Main.input.nextLine();
				String episodeTitle = Main.input.nextLine();

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

				// Take Podcast episode ID as input
				System.out.print("Enter Podcast Episode ID to Update: ");
				int podcastEpisodeID = Main.input.nextInt();

				String selectQuery = "SELECT EpisodeID, EpisodeTitle, PodcastID, Duration, ReleaseDate, ListeningCount, AdvertisementCount FROM PodcastEpisode WHERE EpisodeID="
						+ podcastEpisodeID + ";";
				selectQuery = String.format(selectQuery, podcastEpisodeID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No podcast episode id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE PodcastEpisode SET EpisodeTitle='%s', PodcastID='%s', Duration='%s', ReleaseDate='%s', ListeningCount='%s', AdvertisementCount='%s' WHERE EpisodeID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. Episode Title" + "[" + orignal[0] + "]");
					System.out.println("2. PodcastID" + "[" + orignal[1] + "]");
					System.out.println("3. Duration" + "[" + orignal[2] + "]");
					System.out.println("4. Release Date" + "[" + orignal[3] + "]");
					System.out.println("5. Listening Count" + "[" + orignal[4] + "]");
					System.out.println("6. AdvertisementCount" + "[" + orignal[5] + "]");
					System.out.println("7. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter podcast episode title : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter PodcastID : ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter Duration: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 4:
						System.out.print("Enter ReleaseDate: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 5:
						System.out.print("Enter Listening count: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 6:
						System.out.print("Enter Adverstisment count: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 7:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 7");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to Podcast Episode");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5],
						podcastEpisodeID);

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

				int res = DBConnect.statement.executeUpdate(DeleteQuery);

				if (res >= 1)
					System.out.println("podcast deleted succesfully");
				else
					System.out.println("0 rows affected, podcast ID does not exist");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////

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
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
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

				String selectQuery = "SELECT * FROM PodcastHost WHERE HostID=%d;";
				selectQuery = String.format(selectQuery, hostID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No podcast host found");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE PodcastHost SET FirstName= '%s', LastName= '%s', Phone= '%s', Email= '%s', City= '%s' WHERE HostID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. FirstName" + "[" + orignal[0] + "]");
					System.out.println("2. LastName" + "[" + orignal[1] + "]");
					System.out.println("3. Phone" + "[" + orignal[2] + "]");
					System.out.println("4. Email" + "[" + orignal[3] + "]");
					System.out.println("5. City" + "[" + orignal[4] + "]");
					System.out.println("6. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					switch (choice) {
					case 1:
						System.out.print("Enter first name: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 2:
						System.out.print("Enter last name: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter phone: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 4:
						System.out.print("Enter email: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 5:
						System.out.print("Enter city: ");
						attr[choice - 1] = Main.input.next();
						break;
					case 6:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 6");
						break;
					}
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], hostID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				System.out.println("Podcast Host Updated succesfully");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
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

				int res = DBConnect.statement.executeUpdate(DeleteQuery);

				if (res >= 1)
					System.out.println("podcast host deleted succesfully");
				else
					System.out.println("0 rows affected, podcast host ID does not exist");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	public static void EnterUserInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take User ID as input
				System.out.print("Enter User ID: ");
				int userID = Main.input.nextInt();

				// Take user first name as input
				System.out.print("Enter User first name: ");
				Main.input.nextLine();
				String userFirstName = Main.input.nextLine();
				
				// Take user last name as input
				System.out.print("Enter User last name: ");
				Main.input.nextLine();
				String userlastName = Main.input.nextLine();

				// Take phone as input
				System.out.print("Enter Phone: ");
				String phone = Main.input.next();

				// Take email as input
				System.out.print("Enter email: ");
				Main.input.nextLine();
				String email = Main.input.nextLine();

				// Take registration date as input
				System.out.print("Enter Registration date: ");
				String registrationDate = Main.input.next();

				// Take StatusOfSubscription as input
				System.out.print("Enter Status Of Subscription: ");
				String statusOfSubscription = Main.input.next();

				// Take monthly subscription fee as input
				System.out.print("Enter monthly subscription fee: ");
				float monthlySubscriptionFee = Main.input.nextFloat();

				String InsertQuery = "INSERT INTO User (UserID, FirstName, LastName, Phone, Email, RegistrationDate, StatusOfSubscription, MonthlySubscriptionFee) VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', %f);";
				InsertQuery = String.format(InsertQuery, userID, userFirstName, userlastName, phone, email, registrationDate,
						statusOfSubscription, monthlySubscriptionFee);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("User Inserted succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}
	
	public static void UpdateUserInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take User ID as input
				System.out.print("Enter User ID to Update: ");
				int userID = Main.input.nextInt();

				String selectQuery = "SELECT UserID, FirstName, LastName, Phone, Email, RegistrationDate, StatusOfSubscription, MonthlySubscriptionFee FROM User WHERE UserID="
						+ userID + ";";
				selectQuery = String.format(selectQuery, userID);
				ResultSet result = DBConnect.statement.executeQuery(selectQuery);

				if (!result.isBeforeFirst()) {
					System.out.println("No user id found \n");
					return;
				}

				ResultSetMetaData rsmd = result.getMetaData();
				int nCols = rsmd.getColumnCount();
				String[] attr = new String[nCols - 1];
				if (result.next()) {
					for (int i = 2; i <= nCols; ++i)
						attr[i - 2] = result.getString(i);
				}
				result.close();

				String[] orignal = Arrays.copyOf(attr, nCols - 1);

				int choice;
				String UpdateQuery = "UPDATE Podcast SET FirstName='%s', LastName='%s', Phone='%s', Email='%s', RegistrationDate='%s', StatusOfSubscription='%s', MonthlySubscriptionFee='%f' WHERE UserID=%d;";
				boolean done = false;
				while (!done) {
					System.out.println("1. First Name" + "[" + orignal[0] + "]");
					System.out.println("2. Last Name" + "[" + orignal[1] + "]");
					System.out.println("3. Phone" + "[" + orignal[2] + "]");
					System.out.println("4. Email" + "[" + orignal[3] + "]");
					System.out.println("5. Registration Date" + "[" + orignal[4] + "]");
					System.out.println("6. Status of subscription" + "[" + orignal[5] + "]");
					System.out.println("7. Monthly Subscription Fee" + "[" + orignal[6] + "]");
					System.out.println("8. Exit update");
					System.out.print("Enter your Choice: ");
					choice = Main.input.nextInt();
					Main.input.nextLine();
					switch (choice) {
					case 1:
						System.out.print("Enter first name : ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 2:
						System.out.print("Enter last name : ");
						attr[choice - 1] = Main.input.next();
						break;
					case 3:
						System.out.print("Enter phone: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 4:
						System.out.print("Enter email: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 5:
						System.out.print("Enter registration date: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 6:
						System.out.print("Enter status of subscription: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 7:
						System.out.print("Enter monthly subscription fee: ");
						attr[choice - 1] = Main.input.nextLine();
						break;
					case 8:
						done = true;
						break;
					default:
						System.out.println("\nPlease enter a valid choice from 1 to 8");
						break;
					}
				}

				if (Arrays.equals(attr, orignal)) {
					System.out.println("no updates made to User");
					return;
				}

				UpdateQuery = String.format(UpdateQuery, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6],
						userID);

				DBConnect.statement.executeUpdate(UpdateQuery);

				System.out.println("User Updated succesfully");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	public static void DeleteUserInfo() throws Exception {
		try {
			if (DBConnect.connection != null) {

				// Take user ID as input
				System.out.print("Enter user ID: ");
				int userID = Main.input.nextInt();

				String DeleteQuery = "DELETE FROM User WHERE UserID=%d;";
				DeleteQuery = String.format(DeleteQuery, userID);

				int res = DBConnect.statement.executeUpdate(DeleteQuery);

				if (res >= 1)
					System.out.println("User deleted succesfully");
				else
					System.out.println("0 rows affected, User ID does not exist");

			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	///////////////////////////////////////////////////////////////////////////

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

				String InsertQuery = "INSERT INTO PerformedBy (SongID, ArtistID, MainArtistStatus) VALUES (%d, %d, '%s');";
				InsertQuery = String.format(InsertQuery, songID, ArtistId, mainArtistStatus);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("Song Assigned to Artist succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	//////////////////////////////////////////////////////////////////////

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
				Main.input.nextLine();
				String labelname = Main.input.nextLine();

				String InsertQuery = "INSERT INTO Contract (ArtistID, StartDate, EndDate, LabelName) VALUES (%d, '%s', '%s', '%s');";
				InsertQuery = String.format(InsertQuery, artistID, startdate, enddate, labelname);

				DBConnect.statement.executeUpdate(InsertQuery);

				System.out.println("artist assigned to record label succesfully");
			} else {
				throw new Exception("Connection is null");
			}
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

	/////////////////////////////////////////////////////////////////

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
		} catch (InputMismatchException e) {
			System.out.println("Failure: " + e);
			System.out.println("Please Try Again");
			Main.input.nextLine();
		} catch (SQLException e) {
			System.out.print("Failure: " + e);
		} finally {
			DBConnect.connection.setAutoCommit(true);
		}
	}

}
