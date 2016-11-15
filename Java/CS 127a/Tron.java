public class Tron
{
    public static final int SIZE = 100;
    
    public static void main(String[] args)
    {
        
        //create player 1
        Player[] p1 = new Player[2];
        int p1xStart = SIZE/4;
        int p1yStart = SIZE/2;
        for (int i=0; i<p1.length; i++) {
            p1[i] = new Player();
            p1[i].player1 = true;
            p1[i].x = p1xStart;
            p1[i].y = p1yStart;
            p1[i].direction = 'w';
            p1[i].SIZE = SIZE;
            p1[i].isHead = (i == 0);
            p1[i].red = 0;
            p1[i].green = 0;
            p1[i].blue = 0;
            p1yStart--;
        }
        
        //create player 2
        Player[] p2 = new Player[2];
        int p2xStart = SIZE/4*3;
        int p2yStart = SIZE/2;
        for (int i=0; i<p2.length; i++) {
            p2[i] = new Player();
            p2[i].player2 = true;
            p2[i].x = p2xStart;
            p2[i].y = p2yStart;
            p2[i].direction = 'k';
            p2[i].SIZE = SIZE;
            p2[i].isHead = (i == 0);
            p1[i].red = 255;
            p1[i].green = 0;
            p1[i].blue = 0;
            p2yStart++;
        }
        
        char input;    
        StdDraw.setScale(-1, SIZE);
        
        while (StdDraw.isKeyPressed(' ') == false)
        {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(SIZE/2, SIZE/2, "Hit SPACE to begin");
            
            StdDraw.show(10);
        }
        
        for (int i=0; i<p1.length; i++)
            p1[i].draw();
            
        for (int i=0; i<p2.length; i++)
            p2[i].draw();
            
        StdDraw.show(50);
        StdDraw.clear();
        
        
        while (true)
        {
            //player 1 movement
            if (StdDraw.hasNextKeyTyped())
            {
                input = StdDraw.nextKeyTyped();
                p1[0].changeDirection(input);
                p2[0].changeDirection(input);
            }
            p1 = resizePlayer(p1, p1.length+1);
            
            for (int i=p1.length-1; i>0; i--)
                p1[i].follow(p1[i-1]);
            
            p1[0].move();
            
            //player 2 movement
            if (StdDraw.hasNextKeyTyped())
            {
                input = StdDraw.nextKeyTyped();
                p1[0].changeDirection(input);
                p2[0].changeDirection(input);
            }
            p2 = resizePlayer(p2, p2.length+1);
            
            for (int i=p2.length-1; i>0; i--)
                p2[i].follow(p2[i-1]);
            
            p2[0].move();
            
            //draw board
            StdDraw.clear();
      
            for (int i=0; i<p1.length; i++)
                p1[i].draw();
            
            for (int i=0; i<p2.length; i++)
                p2[i].draw();
            
            StdDraw.show(50);
            
            
            
            if (collision(p1, p2))
            {
                System.out.println("Game Over");
                System.out.println("Player 2 wins!");
                return;
            }
            if (collision(p2, p1))
            {
                System.out.println("Game Over");
                System.out.println("Player 1 wins!");
                return;
            }
        }
    }
  
    public static boolean collision(Player[] hitter, Player[] hittee)
    {
        for (int i=1; i<hitter.length; i++) {
            if (hitter[0].x == hitter[i].x && hitter[0].y == hitter[i].y)
                return true;
            if (hitter[0].x == hittee[i].x && hitter[0].y == hittee[i].y)
                return true;
        }
        return false;
    }
    
    public static Player[] resizePlayer(Player[] snake, int newSize)
    {
        Player[] copy = new Player[newSize];
        
        for (int i=0; i<snake.length; i++)
            copy[i] = snake[i];
        
        int end = newSize-1;
        copy[end] = new Player();
        copy[end].player1 = snake[snake.length-1].player1;
        copy[end].player2 = snake[snake.length-1].player2;
        copy[end].direction = snake[snake.length-1].direction;
        copy[end].SIZE = snake[snake.length-1].SIZE;
        copy[end].isHead = false;
        copy[end].red = snake[snake.length-1].red;
        copy[end].green = snake[snake.length-1].green;
        copy[end].blue = snake[snake.length-1].blue;
        
        if (copy[end].direction == 'w' || copy[end].direction == 'i')
        {
            copy[end].x = snake[snake.length-1].x;
            copy[end].y = snake[snake.length-1].y-1;
        }
        if (copy[end].direction == 's' || copy[end].direction == 'k')
        {
            copy[end].x = snake[snake.length-1].x;
            copy[end].y = snake[snake.length-1].y+1;
        }
        if (copy[end].direction == 'a' || copy[end].direction == 'j')
        {
            copy[end].x = snake[snake.length-1].x+1;
            copy[end].y = snake[snake.length-1].y;
        }
        if (copy[end].direction == 'd' || copy[end].direction == 'l')
        {
            copy[end].x = snake[snake.length-1].x-1;
            copy[end].y = snake[snake.length-1].y;
        }
        
        return copy;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    