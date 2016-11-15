/* class ScanString
*
* CSc 127A Spring 16, Project 02
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* Scans through an input string, returns the index of each instance of x, y, or z, and
* returns the total number of x, y, and z characters in the string
*/
public class ScanString {

	public static void main(String[] args) {
		//declaring and initializing the variables used
		String input = args[0];
		int x_count = 0;
		int y_count = 0;
		int z_count = 0;
		
		//prints out the input string
		System.out.println("Original String: " + input);
		
		//cycles through the input string, prints the location of x, y, or z, and
		//increases their count
		for (int i = 0; i < input.length(); i++){
			if (input.charAt(i) == 'x'){
				System.out.println("Found x at index " + i + ".");
				x_count ++;
			}
			if (input.charAt(i) == 'y'){
				System.out.println("Found y at index " + i + ".");
				y_count ++;
			}
			if (input.charAt(i) == 'z'){
				System.out.println("Found z at index " + i + ".");
				z_count ++;
			}
		}
		//prints out the total number of x, y, and z characters
	    System.out.println("x count: " + x_count);
	    System.out.println("y count: " + y_count);
	    System.out.println("z count: " + z_count);
	}

}
