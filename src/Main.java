import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		try {

			DBConnect.EstablishConnection();

			int choice = 0;
			int subchoice = 0;

			while (choice != 5) {
				System.out.println(
						"Enter a choice to login as :\n1.Content Manager\n2.Data Manager\n3.Finance Manager\n4.Account Manager\n5.Exit");
				choice = new Scanner(System.in).nextInt();

				if (choice == 1) {

				} else if (choice == 2) {

				} else if (choice == 3) {
					

					System.out.println("");

				} else if (choice == 4) {

				} else if (choice == 5) {
					break;
				} else {
					System.out.println("Enter Valid choice");
				}

			}

		} finally {
			DBConnect.close();
		}

	}

}
