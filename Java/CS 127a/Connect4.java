import java.util.Scanner;

public class Connect4
{
  public static void main(String[] args)
  {
    int[][] board = new int[7][6];
    
    boolean redToMove = true;
    
    Scanner in = new Scanner(System.in);
    StdDraw.setScale(-1, 7);
    drawBoard(board);
        
    while (true)
    {
      if (redToMove)
        System.out.println("RED to move");
      else
        System.out.println("BLUE to move");
      
      //if character is pressed, next loop iteration
      if (in.hasNextInt() == false)
        break;
      
      int column = in.nextInt();
      if (column < 1 || column > 7)
      {
        System.out.println("ERROR: please choose a valid column");
        continue;
      }
      
      if (addDisk(board, column, redToMove))
        redToMove = (redToMove == false);
      
      drawBoard(board);
      
      if (checkForGameOver(board))
      {
        System.out.println("Game Over");
        break;
      }
    }
    in.close();
  }
  
  public static boolean addDisk(int[][] board, int column, boolean redToMove)
  {
    int col = column - 1;
    for (int i=0; i<board[col].length; i++)
    {
      if (board[col][i] == 0)
      {
        if (redToMove)
          board[col][i] = 1;
        else
          board[col][i] = 2;
        return true;
      }
    }
    
    System.out.println("ERROR: this column is already full");
    return false;
  }
  
  public static void drawBoard(int[][] board)
  {
    StdDraw.clear(StdDraw.GRAY);
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.text(3, 6.3, "CONNECT4");
    for (int i=0; i<board.length; i++)
      StdDraw.text(i+0.3, -0.5, i + 1 + "          ");
    
    for (int i=0; i<board.length; i++)
    {
      for (int j=0; j<board[i].length; j++)
      {
        if (board[i][j] == 0)
          StdDraw.setPenColor(StdDraw.WHITE);
        else if (board[i][j] == 1)
          StdDraw.setPenColor(StdDraw.RED);
        else if (board[i][j] == 2)
          StdDraw.setPenColor(StdDraw.BLUE);
        
        StdDraw.filledCircle(i, j+0.5, 0.5);
      }
    }
    
    StdDraw.show();
//    if (checkForGameOver(board))
  //  {
    //    StdDraw.setPenColor(StdDraw.BLACK);
      //  StdDraw.text(3, 5, "GAME OVER");
    //}
  }
  
  public static boolean checkForGameOver(int[][] board)
  {
    int gameOverCount;
    // checks columns for game over
    for (int i=0; i<board.length; i++)
    {
      for (int j=0; j<3; j++)
      {
        gameOverCount = 0;
        for (int comp=j+1; comp<j+4; comp++)
        {
          if (board[i][j] != 0 && board[i][j] == board[i][comp])
            gameOverCount++;
          if (gameOverCount == 3)
            return true;
        }
      }
    }
    
    // checks rows for game over
    for (int j=0; j<6; j++)
    {
      for (int i=0; i<4; i++)
      {
        gameOverCount = 0;
        for (int comp=i+1; comp<i+4; comp++)
        {
          if (board[i][j] != 0 && board[i][j] == board[comp][j])
            gameOverCount++;
          if (gameOverCount == 3)
            return true;
        }
      }
    }
    
    // checks positive diagonals for game over
    for (int i=0; i<4; i++)
    {
        for (int j=0; j<3; j++)
        {
            gameOverCount = 0;
            for (int comp=1; comp<4; comp++)
            {
                if (board[i][j] != 0 && board[i][j] == board[i+comp][j+comp])
                    gameOverCount++;
                if (gameOverCount == 3)
                    return true;
            }
        }
    }
    
    // checks negative diagonals for game over
    for (int i=0; i<4; i++)
    {
        for (int j=5; j>2; j--)
        {
            gameOverCount = 0;
            for (int comp=1; comp<4; comp++)
            {
                if (board[i][j] != 0 && board[i][j] == board[i+comp][j-comp])
                    gameOverCount++;
                if (gameOverCount == 3)
                    return true;
            }
        }
    }
    return false;
  }
  
  public static int compPlayer()
  {
    int retval = (int)(Math.random()*7)+1;
    System.out.println(retval);
    return retval;
  }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    