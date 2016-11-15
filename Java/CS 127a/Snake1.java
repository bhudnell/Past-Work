public class Snake1 {
  public static void main(String[] args) {
    
    char input;
    SnakeSegment s = new SnakeSegment();
    s.x = 12;
    s.y = 12;
    s.direction = 'd';
    s.isHead = true;
        
    StdDraw.setScale(0, 25);
    s.draw();
    StdDraw.show(100);
    
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        input = StdDraw.nextKeyTyped();
        s.changeDirection(input);
      }
      s.move();
      StdDraw.clear();
      s.draw();
      StdDraw.show(100);
    }
  }
}