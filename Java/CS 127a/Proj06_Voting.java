/* class Proj06_Voting
*
* CSc 127A Spring 16, Project 06
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* Runs a mock election, pulling data from a text file containing a list of candidates
* and a list of voters with their candidate preferences. Prints the candidates, voters
* with preferences, and who each voter votes for in a dummy election.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj06_Voting
{
    //reads in the filename from the command line
    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length != 1)
        {
            System.out.println("ERROR: Please enter only one argument");
            return;
        }
        
        //creates a new scanner from the file object 
        File inFile = new File(args[0]);
        Scanner in = new Scanner(inFile);
        
        //Creates a candidates array and fills it from the file
        String[] candidates = new String[5];
        for (int i=0; i<candidates.length; i++)
            candidates[i] = in.next();
        
        //creates a voter list array and fills it from the file
        Voter[] voterList = new Voter[12];
        for (int i=0; i<voterList.length; i++)
        {
            voterList[i] = new Voter();
            voterList[i].name = in.next();
            for (int j=0; j<voterList[i].preferences.length; j++)
                voterList[i].preferences[j] = in.next();
        }
        
        in.close();
        
        //prints the list of voters and their candidate preferences
        System.out.println("THESE ARE THE VOTERS:");
        for (int i=0; i<voterList.length; i++)
            voterList[i].print();
        
        //prints the list of candidates
        System.out.println("THESE ARE THE CANDIDATES:");
        for (int i=0; i<candidates.length; i++)
            System.out.println(candidates[i]);
        
        //prints each voter and the candidate they vote for
        System.out.println("STARTING A DUMMY ELECTION:");
        for (int i=0; i<voterList.length; i++)
            voterList[i].vote(candidates);   
    }
}