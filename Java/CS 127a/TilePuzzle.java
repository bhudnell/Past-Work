/* class TilePuzzle
*
* CSc 127A Spring 16, Project 08
*
* Author: Brendon Hudnell
* Section: G
*
* ---
*
* A simple tile slide puzzle. Shifts tiles into the hole square if possible.
*/ 
public class TilePuzzle
{
    public static void main(String[] args)
    {
        //makes an empty 4x4 board of 0's
        int[][] board = new int[4][4];
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
                board[i][j] = 0;
        }
        
        //randomly fills the board with tiles 1-15
        for (int i=1; i<16; i++)
        {
            while (true)
            {
                int x = (int)(Math.random()*4);
                int y = (int)(Math.random()*4);
                if (board[x][y] == 0)
                {
                    board[x][y] = i;
                    break;
                }
            }
        }
        
        //initially draws the board
        StdDraw.setScale(0,4);
        drawBoard(board);
        
        //game loop
        while (true)
        {
            if (StdDraw.mousePressed())
            {
                int mouseX = (int)StdDraw.mouseX();
                int mouseY = (int)StdDraw.mouseY();
                if (mouseX > 3)
                    mouseX = 3;
                if (mouseY > 3)
                    mouseY = 3;
                
                if (slideTiles(board, mouseX, mouseY))
                    drawBoard(board);
            }
            if (checkForWin(board))
            {
                System.out.println("Congrats! You win!");
                break;
            }
        }
    }
    
    public static boolean checkForWin(int[][] board)
    {
        int count = 1;
        for (int i=board.length-1; i>=0; i--)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[j][i] != count && count < 16)
                    return false;
                count++;
            }
        }
        return true;
    }
    
    public static boolean slideTiles(int[][] board, int mouseX, int mouseY)
    {
        //finds the hole and stores its coordinates
        int holeX = 0;
        int holeY = 0;
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[i][j] == 0)
                {
                    holeX = i;
                    holeY = j;
                }
            }
        }
        //checks if mouse click slides horizontally, and slides all legal tiles
        if (mouseY == holeY)
        {
            //slide left
            if (mouseX > holeX)
            {
                for (int i=holeX+1; i<=mouseX; i++)
                    board[i-1][mouseY] = board[i][mouseY];
                board[mouseX][mouseY] = 0;
                return true;
            }
            //slides right
            if (mouseX < holeX)
            {
                for (int i=holeX-1; i>=mouseX; i--)
                    board[i+1][mouseY] = board[i][mouseY];
                board[mouseX][mouseY] = 0;
                return true;
            }
        }
        //checks if mouse click slides vertically, and slides all legal tiles
        else if (mouseX == holeX)
        {
            //slides down
            if (mouseY > holeY)
            {
                for (int i=holeY+1; i<=mouseY; i++)
                    board[mouseX][i-1] = board[mouseX][i];
                board[mouseX][mouseY] = 0;
                return true;
            }
            //slides up
            if (mouseY < holeY)
            {
                for (int i=holeY-1; i>=mouseY; i--)
                    board[mouseX][i+1] = board[mouseX][i];
                board[mouseX][mouseY] = 0;
                return true;
            }
        }
        return false;
    }
    
    //draws board
    public static void drawBoard(int[][] board)
    {
        StdDraw.clear(StdDraw.GRAY);
        
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (board[i][j] != 0)
                {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(i+0.5, j+0.5, 0.47);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(i+0.5, j+0.5, ""+(int)(board[i][j]));
                }
            }
        }
        StdDraw.show();
    }
}