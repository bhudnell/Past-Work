/* class Proj10GameState
*
* CSc 127A Spring 16, Project 10
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* The class for the Proj10GameState object used in the Robots game. Contains all instance methods
* called by Proj09Robots to run the Robots game.
*/ 
public class Proj10GameState
{
    private int[][] board;
    private int playerX, playerY, boardSize;
    
    //constructor that initializes the game state and sets the drawing scale
    public Proj10GameState(int board_size)
    {
        if (board_size < 5)
            throw new IllegalArgumentException();
        
        this.board = new int[board_size][board_size];
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
                board[i][j] = 0;
        }
        this.boardSize = board_size;
        this.playerX = board_size/2;
        this.playerY = board_size/2;
        
        StdDraw.setXscale(0,board_size);
        StdDraw.setYscale(0, board_size+1);
        StdDraw.show(0);
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
    
    //checks if all the robots are destroyed, and returns true if they are
    public boolean allRobotsDestroyed()
    {
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[i][j] == 1)
                    return false;
            }
        }
        return true;
    }
    
    //duplicates the game state and returns the copy
    public Proj10GameState dup()
    {
        Proj10GameState dup = new Proj10GameState(boardSize);
        
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
                dup.board[i][j] = board[i][j];
        }
        
        dup.boardSize = boardSize;
        dup.playerX = playerX;
        dup.playerY = playerY;
        
        return dup;
    }
    
    //teleports the player to a random location on the board
    public void doTeleport()
    {
        playerX = (int)(Math.random()*boardSize);
        playerY = (int)(Math.random()*boardSize);
    }
    
    //reads user input from the keyboard and moves the player if the key is valid
    //has logic for moving piles of rubble
    public boolean handleKeyTyped(char key)
    {
        if (key == 'Q' || key == 'q' || key == '7')
        {
            if (playerX > 1 && playerY < board.length-2)
            {
                if (board[playerX-1][playerY+1] != 2)
                {
                    playerX--;
                    playerY++;
                    return true;
                }
                else if (board[playerX-1][playerY+1] == 2 && board[playerX-2][playerY+2] != 2)
                {
                    board[playerX-1][playerY+1] = 0;
                    board[playerX-2][playerY+2] = 2;
                    playerX--;
                    playerY++;
                    return true;
                }
            }
            else if (playerX > 0 && playerY < board.length-1)
            {
                if (board[playerX-1][playerY+1] != 2)
                {
                    playerX--;
                    playerY++;
                    return true;
                }
            }
        }
        else if (key == 'W' || key == 'w' || key == '8')
        {
            if (playerY < board.length-2)
            {
                if (board[playerX][playerY+1] != 2)
                {
                    playerY++;
                    return true;
                }
                else if (board[playerX][playerY+1] == 2 && board[playerX][playerY+2] != 2)
                {
                    board[playerX][playerY+1] = 0;
                    board[playerX][playerY+2] = 2;
                    playerY++;
                    return true;
                }
            }
            else if (playerY < board.length-1)
            {
                if (board[playerX][playerY+1] != 2)
                {
                    playerY++;
                    return true;
                }
            }
        }
        else if (key == 'E' || key == 'e' || key == '9')
        {
            if (playerX < board.length-2 && playerY < board.length-2)
            {
                if (board[playerX+1][playerY+1] != 2)
                {
                    playerX++;
                    playerY++;
                    return true;
                }
                else if (board[playerX+1][playerY+1] == 2 && board[playerX+2][playerY+2] != 2)
                {
                    board[playerX+1][playerY+1] = 0;
                    board[playerX+2][playerY+2] = 2;
                    playerX++;
                    playerY++;
                    return true;
                }
            }
            else if (playerX < board.length-1 && playerY < board.length-1)
            {
                if (board[playerX+1][playerY+1] != 2)
                {
                    playerX++;
                    playerY++;
                    return true;
                }
            }
        }
        else if (key == 'A' || key == 'a' || key == '4')
        {
            if (playerX > 1)
            {
                if (board[playerX-1][playerY] != 2)
                {
                    playerX--;
                    return true;
                }
                else if (board[playerX-1][playerY] == 2 && board[playerX-2][playerY] != 2)
                {
                    board[playerX-1][playerY] = 0;
                    board[playerX-2][playerY] = 2;
                    playerX--;
                    return true;
                }
            }
            else if (playerX > 0)
            {
                if (board[playerX-1][playerY] != 2)
                {
                    playerX--;
                    return true;
                }
            }
        }
        else if (key == 'S' || key == 's' || key == '5')
        {
            return true;
        }
        else if (key == 'D' || key == 'd' || key == '6')
        {
            if (playerX < board.length-2)
            {
                if (board[playerX+1][playerY] != 2)
                {
                    playerX++;
                    return true;
                }
                else if (board[playerX+1][playerY] == 2 && board[playerX+2][playerY] != 2)
                {
                    board[playerX+1][playerY] = 0;
                    board[playerX+2][playerY] = 2;
                    playerX++;
                    return true;
                }
            }
            else if (playerX < board.length-1)
            {
                if (board[playerX+1][playerY] != 2)
                {
                    playerX++;
                    return true;
                }
            }
        }
        else if (key == 'Z' || key == 'z' || key == '1')
        {
            if (playerX > 1 && playerY > 1)
            {
                if (board[playerX-1][playerY-1] != 2)
                {
                    playerX--;
                    playerY--;
                    return true;
                }
                else if (board[playerX-1][playerY-1] == 2 && board[playerX-2][playerY-2] != 2)
                {
                    board[playerX-1][playerY-1] = 0;
                    board[playerX-2][playerY-2] = 2;
                    playerX--;
                    playerY--;
                    return true;
                }
            }
            else if (playerX > 0 && playerY > 0)
            {
                if (board[playerX-1][playerY-1] != 2)
                {
                    playerX--;
                    playerY--;
                    return true;
                }
            }
        }
        else if (key == 'X' || key == 'x' || key == '2')
        {
            if (playerY > 1)
            {
                if (board[playerX][playerY-1] != 2)
                {
                    playerY--;
                    return true;
                }
                else if (board[playerX][playerY-1] == 2 && board[playerX][playerY-2] != 2)
                {
                    board[playerX][playerY-1] = 0;
                    board[playerX][playerY-2] = 2;
                    playerY--;
                    return true;
                }
            }
            else if (playerY > 0)
            {
                if (board[playerX][playerY-1] != 2)
                {
                    playerY--;
                    return true;
                }
            }
        }
        else if (key == 'C' || key == 'c' || key == '3')
        {
            if (playerX < board.length-2 && playerY > 1)
            {
                if (board[playerX+1][playerY-1] != 2)
                {
                    playerX++;
                    playerY--;
                    return true;
                }
                else if (board[playerX+1][playerY-1] == 2 && board[playerX+2][playerY-2] != 2)
                {
                    board[playerX+1][playerY-1] = 0;
                    board[playerX+2][playerY-2] = 2;
                    playerX++;
                    playerY--;
                    return true;
                }
            }
            else if (playerX < board.length-1 && playerY > 0)
            {
                if (board[playerX+1][playerY-1] != 2)
                {
                    playerX++;
                    playerY--;
                    return true;
                }
            }
        }
        return false;
    }
    
    //moves the robots one square closer to the player if the user inputs a valid command
    public void moveRobots()
    {
        //makes a new array to put updated robot positions into
        int[][] newBoard = new int[boardSize][boardSize];
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
    
    //draws the board, the player, the robots, the piles of rubble, and the current game stats
    public void draw(int level, int safeTeleports)
    {
        StdDraw.clear();
        
        //count number of robots left on the board
        int numRobots = 0;
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[i][j] == 1)
                    numRobots++;
            }
        }
        
        //prints the current game stats
        StdDraw.text(boardSize/2, boardSize+0.4, "Level: " + level + "  Robots: " + numRobots + 
                                                   "  Safe Teleports: " + safeTeleports);
        
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