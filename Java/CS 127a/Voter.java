/* class Voter
*
* CSc 127A Spring 16, Project 07
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* The base of the voter object containing the name and candidate preferences fields
* and the print and vote methods
*/
public class Voter
{
    public String name;
    public String[] preferences = new String[5];
    
    //prints the voter's name and their candidate preferences
    public void print()
    {
        System.out.print("Voter: name=" + name + " preferences:");
        for (int i=0; i<preferences.length; i++)
            System.out.print(" " + preferences[i]);
        System.out.println();
    }
    //prints the voter's name and the candidate they vote for
    //also returns the index of the candidate they vote for
    public int vote(String[] candidates)
    {
        for (int i=0; i<preferences.length; i++)
        {
            for (int j=0; j<candidates.length; j++)
            {
                if (preferences[i].equals(candidates[j]))
                {
                    System.out.println(name + " votes for " + candidates[j]);
                    return j;
                }
            }
        }
        System.out.println(name + " chooses not to vote.");
        return -1;
    }
}