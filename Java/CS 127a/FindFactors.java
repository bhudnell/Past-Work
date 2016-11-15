/* class FindFactors
*
* CSc 127A Spring 16, Project 02
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* Takes an input number and returns: the factors of the number, the total
* number of factors, the sum of the factors, and if the number is a perfect number
*/
public class FindFactors {

	public static void main(String[] args) {
		// declaring and initializing variables used
		int input = Integer.parseInt(args[0]);
		int total = 0;
		int num_of_factors = 0;
		
		//returns an error message if the input is less than or equal to zero
	    if (input <= 0){
	    	System.out.println("ERROR: The input must be a positive integer.");
	    	return ;
	    }
	    
	    //cycles through all possible factors of the input to determine the true factors
	    //keeps a running total of the sum of factors and the number of factors
	    for (int i = 1; i <= input; i++){
	    	if (input % i == 0){
	    		System.out.println(i + " is a factor of " + input);
	    		total += i;
	    		num_of_factors += 1;
	    	}
	    }
	    //prints the number of factors and the sum of the factors
	    System.out.println(input + " has " + num_of_factors + " factors.");
	    System.out.println("The sum of all of the factors of " + input + " is " + total + ".");
	    
	    //prints that the input is a perfect number if the sum of factors is twice the input
	    if (input * 2 == total){
	    	System.out.println(input + " is a PERFECT NUMBER!");
	    }
	}

}
