/* class Wanderer
*
* CSc 127A Spring 16, Project 03
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* TODO: put a quick (2-3 sentence) description of the class here
*/
import java.util.Scanner;

public class Wanderer
{
    public static void main(String[] args)
    {
        
        StdDraw.setScale(-10,10);
        int TARGET_X = 0;
        int TARGET_Y = 0;
        int player_x = 0;
        int player_y = 0;
        
        //creation of target's random x- and y-coordinates (!=0)
        while (TARGET_X == 0 && TARGET_Y == 0)
        { 
            TARGET_X = -10 + (int)(Math.random() * 20);
            TARGET_Y = -10 + (int)(Math.random() * 20);
        }
        
        //initial draw of player
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.circle(player_x, player_y + 0.35, 0.15);
        StdDraw.line(player_x, player_y + 0.2, player_x, player_y - 0.2);
        StdDraw.line(player_x, player_y - 0.2, player_x + 0.25, player_y - 0.5);
        StdDraw.line(player_x, player_y - 0.2, player_x - 0.25, player_y - 0.5);
        StdDraw.line(player_x, player_y, player_x + 0.25, player_y + 0.2);
        StdDraw.line(player_x, player_y, player_x - 0.25, player_y + 0.2);
        
        //initial draw of target
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
        StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
        StdDraw.line(TARGET_X, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
        StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
        StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
        StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X, TARGET_Y);
        StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X, TARGET_Y);
        StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X - 0.25, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X - 0.25, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X + 0.25, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X + 0.25, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
        StdDraw.line(TARGET_X - 0.25, TARGET_Y + 0.25, TARGET_X + 0.25, TARGET_Y + 0.25);
        
        //Start of game loop
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            String word = in.next();
            
            //change player's location based on user input. 
            //Prints error message if not n, s, e, w, north, south, east, or west
            if (word.toLowerCase().equals("n") || word.toLowerCase().equals("north"))
            {
                player_y += 1;
            }
            else if (word.toLowerCase().equals("s") || word.toLowerCase().equals("south"))
            {
                player_y -= 1;
            }
            else if (word.toLowerCase().equals("e") || word.toLowerCase().equals("east"))
            {
                player_x += 1;
            }
            else if (word.toLowerCase().equals("w") || word.toLowerCase().equals("west"))
            {
                player_x -= 1;
            }
            else
            {
                System.out.println("ERROR: not a valid input. Please try again.");
            }
            
            //redraw screen with new player coordinates
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
            StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
            StdDraw.line(TARGET_X, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
            StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
            StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X, TARGET_Y - 0.5);
            StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X, TARGET_Y);
            StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X, TARGET_Y);
            StdDraw.line(TARGET_X - 0.5, TARGET_Y, TARGET_X - 0.25, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X - 0.25, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X - 0.25, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X + 0.5, TARGET_Y, TARGET_X + 0.25, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X + 0.25, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X + 0.25, TARGET_Y, TARGET_X, TARGET_Y + 0.25);
            StdDraw.line(TARGET_X - 0.25, TARGET_Y + 0.25, TARGET_X + 0.25, TARGET_Y + 0.25);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.circle(player_x, player_y + 0.35, 0.15);
            StdDraw.line(player_x, player_y + 0.2, player_x, player_y - 0.2);
            StdDraw.line(player_x, player_y - 0.2, player_x + 0.25, player_y - 0.5);
            StdDraw.line(player_x, player_y - 0.2, player_x - 0.25, player_y - 0.5);
            StdDraw.line(player_x, player_y, player_x + 0.25, player_y + 0.2);
            StdDraw.line(player_x, player_y, player_x - 0.25, player_y + 0.2);
            
            //check to see if player reached the target
            if (player_x == TARGET_X && player_y == TARGET_Y)
            {
                System.out.println("Congratulations! You won!");
                break;
            }
            
        }
        
        in.close();
        System.out.println("Game Over");
        
    }
}           