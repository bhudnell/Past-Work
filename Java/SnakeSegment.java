public class SnakeSegment {
  public int x;
  public int y;
  public char direction;
  public boolean isHead = false;
  
  public void changeDirection(char input) {
    if (isHead == false)
      throw new IllegalArgumentException("Can't call changeDirection on a body segment");
    
    if (input == 'w' && direction != 's')
      direction = input;
    if (input == 's' && direction != 'w')
      direction = input;
    if (input == 'a' && direction != 'd')
      direction = input;
    if (input == 'd' && direction != 'a')
      direction = input;
  }
  
  public void move(){
    if (isHead == false)
      throw new IllegalArgumentException("Can't call move on a body segment");
    
    if (direction == 'w')
      y++;
    if (direction == 's')
      y--;
    if (direction == 'a')
      x--;
    if (direction == 'd')
      x++;
    
    x = (x + 40) % 40;
    y = (y + 40) % 40;
  }
  
  public void draw(){
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledSquare(x, y, 0.5);
    
    if (isHead) {
      if (direction == 'w')
        StdDraw.line(x, y, x, y+1);
      if (direction == 's')
        StdDraw.line(x, y, x, y-1);
      if (direction == 'a')
        StdDraw.line(x, y, x-1, y);
      if (direction == 'd')
        StdDraw.line(x, y, x+1, y);
    }
  }
  
  public void follow(SnakeSegment previous) {
    if (isHead == true)
      throw new IllegalArgumentException("Can't call follow on the head segment");
    
    x = previous.x;
    y = previous.y;
  }
}






























