public class Snake2 {
  public static void main(String[] args) {
    
    SnakeSegment[] snake = new SnakeSegment[10];
    int xStart = 20;
    int yStart = 20;
    for (int i=0; i<snake.length; i++) {
      snake[i] = new SnakeSegment();
      snake[i].x = xStart;
      snake[i].y = yStart;
      snake[i].direction = 'd';
      xStart--;
    }
    snake[0].isHead = true;
    
    char input;    
    StdDraw.setScale(-1, 40);
        
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        input = StdDraw.nextKeyTyped();
        snake[0].changeDirection(input);
      }
      //all the body segments are piling up on top of each other. Maybe reverse the for loop to start at snake.length      
      for (int i=9; i>0; i--)
        snake[i].follow(snake[i-1]);
      
      snake[0].move();
                        
      StdDraw.clear();
      
      for (int i=0; i<snake.length; i++)
        snake[i].draw();
        
      StdDraw.show(100);
      if (collision(snake)) {
          System.out.println("Game Over");
          return;
      }
    }
  }
  
  public static boolean collision(SnakeSegment[] snake) {
      for (int i=1; i<snake.length; i++) {
          if (snake[0].x == snake[i].x && snake[0].y == snake[i].y)
              return true;
      }
      return false;
  }
}