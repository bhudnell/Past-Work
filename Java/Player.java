public class Player
{
    public int x;
    public int y;
    public char direction;
    public boolean isHead;
    public int red, green, blue;
    public boolean player1 = false;
    public boolean player2 = false;
    
    public int SIZE;
  
    public void changeDirection(char input)
    {
        //player 1
        if (player1)
        {
            if (input == 'w' && direction != 's')
                direction = input;
            if (input == 's' && direction != 'w')
                direction = input;
            if (input == 'a' && direction != 'd')
                direction = input;
            if (input == 'd' && direction != 'a')
                direction = input;
        }
        
        //player 2
        if (player2)
        {
            if (input == 'i' && direction != 'k')
                direction = input;
            if (input == 'k' && direction != 'i')
                direction = input;
            if (input == 'j' && direction != 'l')
                direction = input;
            if (input == 'l' && direction != 'j')
                direction = input;
        }
    }
  
    public void move()
    {
        //player 1
        if (direction == 'w')
            y++;
        if (direction == 's')
            y--;
        if (direction == 'a')
            x--;
        if (direction == 'd')
            x++;
        
        //player 2
        if (direction == 'i')
            y++;
        if (direction == 'k')
            y--;
        if (direction == 'j')
            x--;
        if (direction == 'l')
            x++;
    
        x = (x + SIZE) % SIZE;
        y = (y + SIZE) % SIZE;
    }
  
    public void draw()
    {
        StdDraw.setPenColor(red, green, blue);
        StdDraw.filledSquare(x, y, 0.5);
    }
    
    public void follow(Player previous)
    {
    x = previous.x;
    y = previous.y;
    }
}
  