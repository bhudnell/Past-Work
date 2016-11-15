public class LightGrid
{
    public static void main(String[] args)
    {
        double[][] board = new double[4][4];
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
                board[i][j] = 0;
        }
        
        StdDraw.setScale(0,4);
        
        drawBoard(board);
        
        while (true)
        {
            int mouseX = (int)StdDraw.mouseX();
            int mouseY = (int)StdDraw.mouseY();
            if (mouseX > 3)
                mouseX = 3;
            if (mouseY > 3)
                mouseY = 3;
            
            for (int i=0; i<board.length; i++)
            {
                for (int j=0; j<board[i].length; j++)
                {
                    if (i == mouseX && j == mouseY)
                        board[i][j] = 255;
                    else
                        board[i][j] = 0.99 * board[i][j];
                }
            }   
            
            drawBoard(board);
        }
    }
    
    public static void drawBoard(double[][] board)
    {
        StdDraw.clear();
        
        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                StdDraw.setPenColor(0, 0, (int)board[i][j]);
                StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(i+0.5, j+0.5, ""+(int)(board[i][j]));
            }
        }
        
        StdDraw.show(10);
    }
}
