/* class Proj07_Voting
*
* CSc 127A Spring 16, Project 07
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* Opens a text file, reads the contents and then runs a simple election
* then an alternative vote election and reports the winners
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj07_Voting
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
        
        //runs a simple election
        System.out.println("STARTING A SIMPLE ELECTION:");
        doSimpleElection(candidates, voterList);
        
        //runs an alternative vote election
        System.out.println("STARTING AN ALTERNATIVE VOTE ELECTION:");
        doAlternativeVoteElection(candidates, voterList);
    }
    
    public static int[] doVoting(String[] candidates, Voter[] voters)
    {
        int[] numVotes = new int[candidates.length];
        for (int i=0; i<numVotes.length; i++)
            numVotes[i] = 0;
        
        for (int i=0; i<voters.length; i++)
        {
            int vote = voters[i].vote(candidates);
            if (vote != -1)
                numVotes[vote]++;
        }
        return numVotes;
    }
    
    public static void doSimpleElection(String[] candidates, Voter[] voters)
    {
        System.out.println("Beginning voting...");
        int[] numVotes = doVoting(candidates, voters);
        int max = numVotes[0];
        int index = 0;
        for (int i=0; i<numVotes.length; i++)
        {
            if (numVotes[i] > max)
            {
                max = numVotes[i];
                index = i;
            }
        }
        System.out.println("Voting has ended...");
        System.out.println(candidates[index] + " WON THE SIMPLE ELECTION.");
    }
    
    public static void doAlternativeVoteElection(String[] candidates, Voter[] voters)
    {
        while (true)
        {
            System.out.println("Beginning voting...");
            
            int[] numVotes = doVoting(candidates, voters);
            
            int sum = 0;
            for (int i=0; i<numVotes.length; i++)
                sum += numVotes[i];
            
            int max = numVotes[0], min = numVotes[0];
            int maxIndex = 0, minIndex = 0;
            for (int i=0; i<numVotes.length; i++)
            {
                if (numVotes[i] > max)
                {
                    max = numVotes[i];
                    maxIndex = i;
                }
                if (numVotes[i] < min)
                {
                    min = numVotes[i];
                    minIndex = i;
                }
            }
            
            System.out.println("Voting has ended...");
        
            if ( max > sum/2)
            {
                System.out.println(candidates[maxIndex] + " WON THE ALTERNATIVE VOTE ELECTION.");
                break;
            }
            System.out.println("No winner yet.  Removing the candidate " + candidates[minIndex]);
            candidates = arrayDel(candidates, minIndex);
        }
    }
        
    public static String[] arrayDel(String[] array, int index)
    {
        String[] retarray = new String[array.length-1];
        for (int i=0; i<index; i++)
            retarray[i] = array[i];
        for (int i=index+1; i<array.length; i++)
            retarray[i-1] = array[i];
        return retarray;
    }
}


























