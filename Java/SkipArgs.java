/* class SkipArgs
*
* CSc 127A Spring 16, Project 04
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* This program takes any number of command line arguments and returns multiple duplicates
* of the same arguments, replacing one argument at a time with a '-'. It then returns
* multiples of the arguments in reverse, again replacing one argument at a time with a '-'.
*/ 
public class SkipArgs
{
    public static void main(String[] args)
    {
        //Calls the printExceptOne method for each argument in args[]
        for (int i=0; i<args.length; i++)
        {
            printExceptOne(args, i);
        }
        
        //Creates the reverse array of args[]
        String[] reverse = new String[args.length];
        int j = args.length-1;        
        for (int i=0; i<reverse.length; i++)
        {
            reverse[i] = args[j];
            j--;
        }
        
        //Calls the printExceptOne method for each argument in reverse[]
        for (int i=0; i<reverse.length; i++)
        {
            printExceptOne(reverse, i);
        }        
    }
    
    public static void printExceptOne(String[] words, int skip)
    {
        //Throws an illegal argument exception if skip is not within the size of words[]
        if (skip >= words.length || skip < 0)
        {
            throw new IllegalArgumentException();
        }
        
        //Create the result string to be printed
        String result = "";
        
        //Add arguments to result up to the arguemnt that is skipped
        for (int i=0; i<skip; i++)
        {
            result += words[i] + " ";
        }
        
        //Adds the dash at the skipped argument to result
        result += "- ";
        
        //Adds the arguments after the skipped argument to result
        for (int i=skip+1; i<words.length; i++)
        {
            result += words[i] + " ";
        }
        
        //Remove the space at the end of the result string, adds newline
        //character and then print it
        result = result.substring(0,result.length()-1);
        result += "\n";
        System.out.println(result.length());
        System.out.print(result);
    }
}