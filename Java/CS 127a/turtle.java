public class turtle {
    private int turtleX, turtleY;
    private int facing;
    
    public turtle() {
        turtleX = 0;
        turtleY = 0;
        facing = 1;
    }
    
    public void turn() {
        facing = (facing+5)%4;
    }
    
    public void move() {
        if (facing == 1)
            turtleY++;
        if (facing == 2)
            turtleX++;
        if (facing == 3)
            turtleY--;
        if (facing == 4)
            turtleX--;
    }
    
    public int getX() {
        return turtleX;
    }
    
    public int getY() {
        return turtleY;
    }
}