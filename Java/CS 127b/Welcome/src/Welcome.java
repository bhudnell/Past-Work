
//This program asks for your name and prints a nice message
//Programmer: Brendon Hudnell
import java.util.Scanner;

public class Welcome {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Welcome. What is your name? ");
		String name = keyboard.nextLine();
		keyboard.close();

		System.out.println("Hello " + name + ". I hope you are feeling well :-)");
	}
}