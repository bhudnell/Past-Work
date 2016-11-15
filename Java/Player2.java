public class Player2
{
    public int x;
    public int y;
    public char direction;
    public boolean isHead;
    
    public int SIZE;
  
    public void changeDirection(char input)
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
  
    public void move()
    {
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
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledSquare(x, y, 0.5);
    }
    
    public void follow(SnakeSegment previous)
    {
    x = previous.x;
    y = previous.y;
    }
}
  