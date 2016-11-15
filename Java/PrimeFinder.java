/* class PrimeFinder
*
* CSc 127A Spring 16, Project 04
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* This program takes two command line arguments (start value, count) and returns a list 
* of prime numbers of length 'count', starting with the first prime after 'start value'
*/ 
public class PrimeFinder
{
    public static void main(String[] args)
    {
        //Prints an error message and terminates the program if the user inputs the wrong
        //number of command line arguments
        if (args.length != 2)
        {
            System.out.println("ERROR: Wrong number of command line arguments.");
            return;
        }
        
        //Converts both arguments to integers
        int start = Integer.parseInt(args[0]);
        int count = Integer.parseInt(args[1]);
        
        //Prints an error message and terminates the program if either argument
        //is out of range
        if (count == 0)
        {
            return;
        }
        if (count < 0)
        {
            System.out.println("ERROR: count must be greater than or equal to 0.");
            return;
        }
        if (count >= 1000000)
        {
            System.out.println("ERROR: count must be less than one million");
            return;
        }
        if (start < 2)
        {
            System.out.println("ERROR: the starting value must be greater than or equal to 2.");
            return;
        }
        if (start >= 1000000000)
        {
            System.out.println("ERROR: the starting value must be less than one billion");
            return;
        }
        
        //Allocates an array of size 'count' named primes
        int[] primes = new int[count];
        
        //Fills the primes array with prime numbers
        primes[0] = findNextPrime(start);        
        for (int i=1; i<primes.length; i++)
        {
            primes[i] = findNextPrime(primes[i-1]+1);
        }
        
        //Prints each element in the primes array
        for (int i=0; i<primes.length; i++)
        {
            System.out.println(primes[i]);
        }
    }
    
    public static int findNextPrime(int start)
    {
        int test_val = start;
        
        //Checks numbers greater than or equal to start until it finds one that 
        //is prime and returns it
        while (true)
        {
            if (isPrime(test_val))
            {
                return test_val;
            }
            else
            {
                test_val++;
            }
        }
    }
    
    public static boolean isPrime(int test_val)
    {
        //If the test value is divisible by 2 or 3, return false
        if (test_val%2 == 0)
        {
            return false;
        }
        else if (test_val%3 == 0)
        {
            return false;
        }
        
        //If the test value isnt divisible by 2 or 3, checks to see if it is divisible
        //by any other number. Returns false if it is
        else
        {
            for (int i=4; i<test_val; i++)
            {
                if (test_val%i == 0)
                {
                    return false;
                }
            }
        }
        //If test value isnt divisible by any other number, return true
        return true;
    }
}