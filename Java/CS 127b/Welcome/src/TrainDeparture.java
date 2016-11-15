
//This program reads the current time and the train departure time
//and displays the difference between the two times in hours and minutes
//Programmer: Brendon Hudnell
import java.util.Scanner;

public class TrainDeparture {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		// Gets current and departure times from user
		System.out.print("Current time: ");
		int current = Integer.parseInt(keyboard.nextLine());
		System.out.print("Train departure time: ");
		int depart = Integer.parseInt(keyboard.nextLine());
		keyboard.close();

		// converts current and departure time to minutes and finds the
		// difference
		int currentInMinutes = current / 100 * 60 + current % 100;
		int departInMinutes = depart / 100 * 60 + depart % 100;
		int diffInMinutes = Math.abs(departInMinutes - currentInMinutes);

		// prints out the difference in hours and minutes
		System.out.println("Train departs in " + diffInMinutes / 60 + " hours and " + diffInMinutes % 60 + " minutes");
	}
}