/* class Proj09GameState
*
* CSc 127A Spring 16, Project 09
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* The class for the Proj09GameState object used in the Robots game. Contains all instance methods
* called by Proj09Robots to run the Robots game.
*/ 
public class Proj09GameState
{
    public int[][] board = new int[21][21];
    public int playerX, playerY;
    
    //initializes the game state
    public void init()
    {
        for (int i=0; i< board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
                board[i][j] = 0;
        }
        
        playerX = 10;
        playerY = 10;
    }
    
    //adds robots to random locations on the board
    public void addRobots(int num)
    {
        for (int i=0; i<num; i++)
        {
            while (true)
            {
                int botX = (int)(Math.random()*board.length);
                int botY = (int)(Math.random()*board.length);
                if (board[botX][botY] == 0 && botX != playerX && botY != playerY)
                {
                    board[botX][botY] = 1;
                    break;
                }
            }
        }
    }
    
    //checks if the player hit a robot or a pile of rubble, and returns true if it has
    public boolean isGameOver()
    {
        if (board[playerX][playerY] == 1 || board[playerX][playerY] == 2)
            return true;
        return false;
    }
    
    //reads user input from the keyboard and moves the player if the key is valid
    public boolean handleKeyTyped(char key)
    {
        if (key == 'Q' || key == 'q' || key == '7')
        {
            if (playerX > 0 && playerY < board.length-1)
            {
                playerX--;
                playerY++;
                System.out.println("Moving up and left");
                return true;
            }
        }
        else if (key == 'W' || key == 'w' || key == '8')
        {
            if (playerY < board.length-1)
            {
                playerY++;
                System.out.println("Moving up");
                return true;
            }
        }
        else if (key == 'E' || key == 'e' || key == '9')
        {
            if (playerX < board.length-1 && playerY < board.length-1)
            {
                playerX++;
                playerY++;
                System.out.println("Moving up and right");
                return true;
            }
        }
        else if (key == 'A' || key == 'a' || key == '4')
        {
            if (playerX > 0)
            {
                playerX--;
                System.out.println("Moving left");
                return true;
            }
        }
        else if (key == 'S' || key == 's' || key == '5')
        {
            System.out.println("No movement");
            return true;
        }
        else if (key == 'D' || key == 'd' || key == '6')
        {
            if (playerX < board.length-1)
            {
                playerX++;
                System.out.println("Moving right");
                return true;
            }
        }
        else if (key == 'Z' || key == 'z' || key == '1')
        {
            if (playerX > 0 && playerY > 0)
            {
                playerX--;
                playerY--;
                System.out.println("Moving down and left");
                return true;
            }
        }
        else if (key == 'X' || key == 'x' || key == '2')
        {
            if (playerY > 0)
            {
                playerY--;
                System.out.println("Moving down");
                return true;
            }
        }
        else if (key == 'C' || key == 'c' || key == '3')
        {
            if (playerX < board.length-1 && playerY > 0)
            {
                playerX++;
                playerY--;
                System.out.println("Moving down and right");
                return true;
            }
        }
        return false;
    }
    
    //moves the robots one square closer to the player if the user inputs a valid command
    public void moveRobots()
    {
        //makes a new array to put updated robot positions into
        int[][] newBoard = new int[21][21];
        for (int i=0; i<newBoard.length; i++)
        {
            for (int j=0; j<newBoard[i].length; j++)
                newBoard[i][j] = 0;
        }
        
        //for each square, if theres a robot move it closer, if theres a pile of rubble copy it
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[i][j] == 2)
                    newBoard[i][j] = 2;
                else if (board[i][j] == 1)
                {
                    if (i > playerX)
                    {
                        if (j > playerY)
                        {
                            if (newBoard[i-1][j-1] == 0)
                                newBoard[i-1][j-1] = 1;
                            else 
                                newBoard[i-1][j-1] = 2;
                        }
                        else if (j < playerY)
                        {
                            if (newBoard[i-1][j+1] == 0)
                                newBoard[i-1][j+1] = 1;
                            else 
                                newBoard[i-1][j+1] = 2;
                        }
                        else
                        {
                            if (newBoard[i-1][j] == 0)
                                newBoard[i-1][j] = 1;
                            else 
                                newBoard[i-1][j] = 2;
                        }
                    }
                    else if (i < playerX)
                    {
                        if (j > playerY)
                        {
                            if (newBoard[i+1][j-1] == 0)
                                newBoard[i+1][j-1] = 1;
                            else 
                                newBoard[i+1][j-1] = 2;
                        }
                        else if (j < playerY)
                        {
                            if (newBoard[i+1][j+1] == 0)
                                newBoard[i+1][j+1] = 1;
                            else 
                                newBoard[i+1][j+1] = 2;
                        }
                        else
                        {
                            if (newBoard[i+1][j] == 0)
                                newBoard[i+1][j] = 1;
                            else 
                                newBoard[i+1][j] = 2;
                        }
                    }
                    else
                    {
                        if (j > playerY)
                        {
                            if (newBoard[i][j-1] == 0)
                                newBoard[i][j-1] = 1;
                            else 
                                newBoard[i][j-1] = 2;
                        }
                        else if (j < playerY)
                        {
                            if (newBoard[i][j+1] == 0)
                                newBoard[i][j+1] = 1;
                            else 
                                newBoard[i][j+1] = 2;
                        }
                        else
                        {
                            if (newBoard[i][j] == 0)
                                newBoard[i][j] = 1;
                            else 
                                newBoard[i][j] = 2;
                        }
                    }
                }
            }
        }
        
        //puts the new array contents back into the old array
        for (int i=0; i<newBoard.length; i++)
        {
            for (int j=0; j<newBoard[i].length; j++)
                board[i][j] = newBoard[i][j];
        }
    }
    
    //draws the board, the player, the robots, and the piles of rubble
    public void draw()
    {
        StdDraw.setScale(0,21);
        StdDraw.clear();
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledSquare(i+0.5, j+0.5, 0.47);
                if (board[i][j] == 1)
                {
                    //draws robots
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledRectangle(i+0.5, j+0.4, 0.4, 0.3);
                    StdDraw.filledSquare(i+0.175, j+0.75, 0.075);
                    StdDraw.filledSquare(i+0.5, j+0.75, 0.075);
                    StdDraw.filledSquare(i+0.825, j+0.75, 0.075);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledRectangle(i+0.5, j+0.25, 0.325, 0.075);
                    StdDraw.filledRectangle(i+0.25, j+0.55, 0.075, 0.075);
                    StdDraw.filledRectangle(i+0.75, j+0.55, 0.075, 0.075);
                }
                else if (board[i][j] == 2)
                {
                    //draws piles of rubble
                    StdDraw.setPenColor(StdDraw.DARK_GRAY);
                    StdDraw.filledCircle(i+0.2, j+0.3, 0.15);
                    StdDraw.filledCircle(i+0.5, j+0.3, 0.15);
                    StdDraw.filledCircle(i+0.8, j+0.3, 0.15);
                    StdDraw.filledCircle(i+0.35, j+0.45, 0.15);
                    StdDraw.filledCircle(i+0.65, j+0.45, 0.15);
                    StdDraw.filledCircle(i+0.5, j+0.6, 0.15);
                }
            }
        }
        //draws player
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(playerX+0.5, playerY+0.77, 0.2);
        StdDraw.line(playerX+0.5, playerY+0.82, playerX+0.5, playerY+0.33);
        StdDraw.line(playerX+0.5, playerY+0.5, playerX+0.3, playerY+0.45);
        StdDraw.line(playerX+0.5, playerY+0.5, playerX+0.7, playerY+0.45);
        StdDraw.line(playerX+0.5, playerY+0.33, playerX+0.3, playerY+0.03);
        StdDraw.line(playerX+0.5, playerY+0.33, playerX+0.7, playerY+0.03);
    }
}       