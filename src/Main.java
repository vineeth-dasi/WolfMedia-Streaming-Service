import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static Scanner input = null;

	public static void main(String args[]) {
		try {
			input = new Scanner(System.in);
			DBConnect.EstablishConnection();

			int choice = 0;

			while (true) {
				System.out.println("\n----------------------------------------------");
				System.out.println("\t\tLogin as");
				System.out.println("----------------------------------------------");
				System.out.println("1. Content Manager-Information Processing");
				System.out.println("2. Data Manager-Maintaining metadata and records");
				System.out.println("3. Finance Manager-Maintaining payments");
				System.out.println("4. Account Manager-Reports");
				System.out.println("5. Generate Payments");
				System.out.println("6. Exit menu");
				System.out.print("Enter your Choice: ");
				choice = input.nextInt();

				if (choice == 1) {
					while (true) {
						System.out.println("\n----------------------------------------------");
						System.out.println("\t   Information Processing");
						System.out.println("----------------------------------------------");
						System.out.println("1. Insert/Update/Delete a Song");
						System.out.println("2. Insert/Update/Delete an Album");
						System.out.println("3. Insert/Update/Delete an Artist");
						System.out.println("4. Insert/Update/Delete a Podcast");
						System.out.println("5. Insert/Update/Delete a PodcastEpisode");
						System.out.println("6. Insert/Update/Delete a PodcastHost");
						System.out.println("7. Insert/Update/Delete a User");
						System.out.println("8. Assign Song to Artist");
						System.out.println("9. Assign Artist to RecordLabel");
						System.out.println("10. Assign PodcastHost to Podcast");
						System.out.println("11. Return to main menu");
						System.out.print("Enter your Choice: ");
						choice = input.nextInt();

						if (choice == 1) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\t   Operations on Song");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert a Song");
								System.out.println("2. Update a Song");
								System.out.println("3. Delete a Song");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterSongInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdateSongInfo();
								} else if (choice == 3) {
									InformationProcessing.DeleteSongInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						} else if (choice == 2) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\t   Operations on Album");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert an Album");
								System.out.println("2. Update an Album");
								System.out.println("3. Delete an Album");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterAlbumInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdateAlbumInfo();
								} else if (choice == 3) {
									InformationProcessing.DeleteAlbumInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						} else if (choice == 3) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\t   Operations on Artist");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert an Artist");
								System.out.println("2. Update an Artist");
								System.out.println("3. Delete an Artist");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterArtistInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdateArtistInfo();
								} else if (choice == 3) {
									InformationProcessing.DeleteArtistInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						} else if (choice == 4) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\t   Operations on Podcast");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert a Podcast");
								System.out.println("2. Update a Podcast");
								System.out.println("3. Delete a Podcast");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterPodcastInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdatePodcastInfo();
								} else if (choice == 3) {
									InformationProcessing.DeletePodcastInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						} else if (choice == 5) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\tOperations on PodcastEpisode");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert a PodcastEpisode");
								System.out.println("2. Update a PodcastEpisode");
								System.out.println("3. Delete a PodcastEpisode");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterPodcastEpisodeInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdatePodcastEpisodeInfo();
								} else if (choice == 3) {
									InformationProcessing.DeletePodcastEpisodeInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						} else if (choice == 6) {
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\tOperations on PodcastHost");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert a PodcastHost");
								System.out.println("2. Update a PodcastHost");
								System.out.println("3. Delete a PodcastHost");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterPodcastHostInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdatePodcastHostInfo();
								} else if (choice == 3) {
									InformationProcessing.DeletePodcastHostInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						}else if (choice == 7)
						{
							while (true) {
								System.out.println("\n----------------------------------------------");
								System.out.println("\tOperations on User");
								System.out.println("----------------------------------------------");
								System.out.println("1. Insert a User");
								System.out.println("2. Update a User");
								System.out.println("3. Delete a User");
								System.out.println("4. Return to Information Processing menu");
								System.out.print("Enter your Choice: ");
								choice = input.nextInt();
								if (choice == 1) {
									InformationProcessing.EnterUserInfo();
								} else if (choice == 2) {
									InformationProcessing.UpdateUserInfo();
								} else if (choice == 3) {
									InformationProcessing.DeleteUserInfo();
								} else if (choice == 4) {
									break;
								} else {
									System.out.println("\nPlease enter a valid choice from 1 to 4");
								}
							}
						}
						else if (choice == 8) {
							InformationProcessing.AssignSongsToArtists();
						} else if (choice == 9) {
							InformationProcessing.AssignArtistToRecordLabel();
						} else if (choice == 10) {
							InformationProcessing.AssignPodcastHostToPodcast();
						} else if (choice == 11) {
							break;
						} else {
							System.out.println("\nPlease enter a valid choice from 1 to 11");
						}
					}
				} else if (choice == 2) {
					while (true) {
						System.out.println("\n----------------------------------------------");
						System.out.println("\tMaintaining metadata and records");
						System.out.println("----------------------------------------------");
						System.out.println("1. Enter/update current month play count for songs");
						System.out.println("2. Enter/update the count of monthly listeners for artists");
						System.out.println("3. Enter/update the total count of subscribers for podcasts");
						System.out.println("4. Enter/update ratings for podcasts");
						System.out.println("5. Enter/Update the listening count for podcast episodes");
						System.out.println("6. Find songs given artist");
						System.out.println("7. Find songs given album");
						System.out.println("8. Find podcast episodes given podcast");
						System.out.println("9. Return to main menu");
						System.out.print("Enter your Choice: ");
						choice = input.nextInt();
						if (choice == 1) {
							MaintainingMetadataAndRecords.enterOrUpdatePlayCountInfo();
						} else if (choice == 2) {
							MaintainingMetadataAndRecords.enterOrUpdateMonthlyListenersInfo();
						} else if (choice == 3) {
							MaintainingMetadataAndRecords.enterOrUpdateTotalSubscribesForPodcastInfo();
						} else if (choice == 4) {
							MaintainingMetadataAndRecords.enterOrUpdateRatingForPodcastInfo();
						} else if (choice == 5) {
							MaintainingMetadataAndRecords.enterOrUpdateListeningCountForPodcastEpisodeInfo();
						} else if (choice == 6) {
							MaintainingMetadataAndRecords.findSongsWithArtist();
						} else if (choice == 7) {
							MaintainingMetadataAndRecords.findSongsInAlbum();
						} else if (choice == 8) {
							MaintainingMetadataAndRecords.findEpisodesInPodcast();
						} else if (choice == 9) {
							break;
						} else {
							System.out.println("\nPlease enter a valid choice from 1 to 9");
						}
					}

				} else if (choice == 3) {
					while (true) {
						System.out.println("\n----------------------------------------------");
						System.out.println("\t   Maintaining payments");
						System.out.println("----------------------------------------------");
						System.out.println("1. Make royalty payments for a given song");
						System.out.println("2. Make payment to podcast hosts");
						System.out.println("3. Receive payment from subscribers.");
						System.out.println("4. Return to main menu");
						System.out.print("Enter your Choice: ");
						choice = input.nextInt();
						if (choice == 1) {
							MaintainingPayments.makeRoyaltyPayment();
						} else if (choice == 2) {
							MaintainingPayments.makePodcastPayment();
						} else if (choice == 3) {
							MaintainingPayments.receiveSubscribersPayment();
						} else if (choice == 4) {
							break;
						} else {
							System.out.println("\nPlease enter a valid choice from 1 to 4");
						}
					}

				} else if (choice == 4) {
					while (true) {
						System.out.println("\n----------------------------------------------");
						System.out.println("\t\tReports");
						System.out.println("----------------------------------------------");
						System.out.println("1. Monthly play count per song");
						System.out.println("2. Monthly play count per album");
						System.out.println("3. Monthly play count per artist");
						System.out.println("4. Total payments made out to host per a given time period");
						System.out.println("5. Total payments made out to artist per a given time period");
						System.out.println("6. Total payments made out to record labels per a given time period");
						System.out.println("7. Payments made out to host per month for a given time period");
						System.out.println("8. Payments made out to artist per month for a given time period");
						System.out.println("9. Payments made out to record labels per month for a given time period");
						System.out.println("10. Total revenue(income) of the streaming service per month");
						System.out.println("11. Total revenue(income) of the streaming service per year");
						System.out.println("12. Total profit(net revenue) of the streaming service per month");
						System.out.println("13. Total profit(net revenue) of the streaming service per year");
						System.out.println("14. Report all songs given an artist");
						System.out.println("15. Report all songs given an album");
						System.out.println("16. Report all podcast episodes given a podcast");
						System.out.println("17. Return to main menu");
						System.out.print("Enter your Choice: ");
						choice = input.nextInt();
						if (choice == 1) {
							ReportGeneration.calculateMonthlyPlayCountPerSong();
						} else if (choice == 2) {
							ReportGeneration.calculateMonthlyPlayCountPerAlbum();
						} else if (choice == 3) {
							ReportGeneration.calculateMonthlyPlayCountPerArtist();
						} else if (choice == 4) {
							ReportGeneration.calculateTotalPaymentsToHost();
						} else if (choice == 5) {
							ReportGeneration.calculateTotalPaymentsToArtist();
						} else if (choice == 6) {
							ReportGeneration.calculateTotalPaymentsToRecordLabel();
						} else if (choice == 7) {
							ReportGeneration.calculatePaymentsToHostPerMonth();
						} else if (choice == 8) {
							ReportGeneration.calculatePaymentsToArtistPerMonth();
						} else if (choice == 9) {
							ReportGeneration.calculatePaymentsToRecordLabelPerMonth();
						} else if (choice == 10) {
							ReportGeneration.calculateTotalRevenuePerMonth();
						} else if (choice == 11) {
							ReportGeneration.calculateTotalRevenuePerYear();
						} else if (choice == 12) {
							ReportGeneration.calculateTotalProfitPerMonth();
						} else if (choice == 13) {
							ReportGeneration.calculateTotalProfitPerYear();
						} else if (choice == 14) {
							ReportGeneration.findSongsWithArtist();
						} else if (choice == 15) {
							ReportGeneration.findSongsInAlbum();
						} else if (choice == 16) {
							ReportGeneration.findEpisodesInPodcast();
						} else if (choice == 17) {
							break;
						} else {
							System.out.println("\nPlease enter a valid choice from 1 to 12");
						}
					}

				} else if (choice == 5) {
					MaintainingPayments.generatePayments();
				} else if (choice == 6) {
					break;
				} else {
					System.out.println("\nPlease enter a valid choice from 1 to 5");
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {

			try {
				DBConnect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			input.close();
		}

	}

}
