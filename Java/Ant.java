/* class Ant
*
* CSc 127A Spring 16, Project 05
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* This program creates an ant that attempts to reach the mouse over a 2D grid of squares. 
* The grid square color changes based on what their color was previously.
*/
import java.util.Random;

public class Ant
{
    public static final int MAX_SIZE = 50; //Global variable used to set the scale of the grid
    
    public static void main(String[] args)
    {
        // creates a square matrix of size MAX_SIZE and fills it with 0's
        int[][] grid = new int[MAX_SIZE][MAX_SIZE];
        for (int i=0; i<MAX_SIZE; i++)
        {
            for (int j=0; j<MAX_SIZE; j++)
            {
                grid[i][j] = 0;
            }
        }
        
        //The ant's initial position
        int oldAntXpos = MAX_SIZE/2;
        int oldAntYpos = MAX_SIZE/2;
        int newAntXpos, newAntYpos; //declare ant position temporary variables
        
        //initial drawing of the board and ant
        StdDraw.setScale(0, MAX_SIZE);
        drawBoard(grid);
        drawAnt(oldAntXpos, oldAntYpos);
        StdDraw.show(100);
        
        //begin the interactive part of the program
        while (true)
        {
            //update ant's postion
            newAntXpos = moveAnt(grid[oldAntXpos][oldAntYpos], oldAntXpos, (int)StdDraw.mouseX()+1);
            newAntYpos = moveAnt(grid[oldAntXpos][oldAntYpos], oldAntYpos, (int)StdDraw.mouseY()+1);
            
            //update the grid's state and redraw the board and ant
            changeGridState(grid, oldAntXpos, oldAntYpos);
            StdDraw.clear();
            drawBoard(grid);
            drawAnt(newAntXpos, newAntYpos);
            
            //shows the new state of the program for 100 clock ticks while changing squares to red
            //where the user clicks the mouse
            int i = 0;
            while (i < 10)
            {
                //makes sure the mouse click is within the bounds of the board
                if (StdDraw.mousePressed() && (StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= MAX_SIZE-1
                && StdDraw.mouseY() >= 0 && StdDraw.mouseY() <= MAX_SIZE-1))
                   grid[(int)StdDraw.mouseX()][(int)StdDraw.mouseY()] = 3;
                StdDraw.show(10);
                i++;
            }
            
            //updates the ant position coordinates
            oldAntXpos = newAntXpos;
            oldAntYpos = newAntYpos;
        }        
    }
    
    //moves the ant in either the x- or y- plane depending on the state of the grid square
    public static int moveAnt(int gridState, int antCoord, int mouseCoord)
    {
        int updatedPos = antCoord;
        Random ant = new Random();
        //if white move toward the mouse
        if (gridState == 0)
        {
            if (mouseCoord > antCoord)
                updatedPos += 1;
            if (mouseCoord < antCoord)
                updatedPos -= 1;
        }
        //if blue move away from the mouse
        if (gridState == 1)
        {
            if (mouseCoord > antCoord)
                updatedPos -= 1;
            if (mouseCoord < antCoord)
                updatedPos += 1;
        }
        //if green move in a random direction
        if (gridState == 2)
        {
            updatedPos += ant.nextInt(3) - 1;
        }
        //if red dont move
        if (gridState == 3)
        {
            updatedPos = antCoord;
        }
        
        //if the ant would move off the board, have it wrap around to the other size
        if (updatedPos >= MAX_SIZE)
            updatedPos = 0;
        if (updatedPos < 0)
            updatedPos = MAX_SIZE - 1;
        
        return updatedPos;
    }
    
    //changes the grid state in the order:
    //white-->blue
    //blue-->green
    //green-->white
    //red-->white
    public static void changeGridState(int[][] grid, int x, int y)
    {
        if (grid[x][y] == 0)
            grid[x][y] = 1;            
        else if (grid[x][y] == 1)
            grid[x][y] = 2;
        else if (grid[x][y] == 2)
            grid[x][y] = 0;
        else if (grid[x][y] == 3)
            grid[x][y] = 0;        
    }
    
    //draws the board
    public static void drawBoard(int[][] grid)
    {
        for (int i=0; i<MAX_SIZE; i++)
        {
            for (int j=0; j<MAX_SIZE; j++)
            {
                if (grid[i][j] == 0)
                {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(i, j, 0.5);
                }
                if (grid[i][j] == 1)
                {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledSquare(i, j, 0.5);
                }
                if (grid[i][j] == 2)
                {
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.filledSquare(i, j, 0.5);
                }
                if (grid[i][j] == 3)
                {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledSquare(i, j, 0.5);
                }
            }
        }
    }
    
    //draws the ant
    public static void drawAnt(int x, int y)
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x, y, 0.25);
    }
}

    
    