/* Proj09Robots
 *
 * CSc 127a Spring 16 - Project 9
 *
 * Author: Russ Lewis
 * TA:     n/a
 *
 * This is the main() method for Robots.  It has the main game loop, and very
 * basic game logic; most of the logic for this program is buried in the
 * GameState1 class.
 */

import java.awt.Font;

public class Proj09Robots
{
        public static void main(String[] args)
        {
                // this creates the game state object.  init() should set up
                // the game state with an empty board; allocate the array which
                // will hold the board state, and also initialize the player
                // position, and any other variables you choose to define.
                //
                // If you want to make the first pass of drawing the board faster,
                // init() can also include a call to StdDraw.show(0).  But that
                // isn't required.
                //
                // addRobots() will add robots to the game.  Make sure to use a
                // loop, so that we can change the number of robots we use later.
                
                GameState1 game = new GameState1();
                game.init();
                game.addRobots(10);
                
                // before the game begins, draw the board once; this shows the
                // player the initial state.                
                game.draw();
                
                // the game object is responsible for detecting when the game
                // has ended
                while (game.isGameOver() == false)
                {
                        // spin until the user has typed something
                        while (StdDraw.hasNextKeyTyped() == false)
                                StdDraw.show(10);

                        // now that the user has typed something, read it, and
                        // pass it to the game object.
                        //
                        // handleKeyTyped() should return 'true' if the player
                        // moved (or choose not to move), so that the robots will
                        // move and the board will be redrawn.
                        //
                        // It should return 'false' if the key wasn't recognized,
                        // or if no move was possible.
                        if (game.handleKeyTyped(StdDraw.nextKeyTyped()))
                        {
                                // something happened; move all the robots, and
                                // redraw the board.
                                game.moveRobots();
                                game.draw();
                        }
                }
                
                // draw the text "GAME OVER" over the top of the last board
                // which was drawn.
                StdDraw.setScale(0,1);
                
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
                StdDraw.text(.5,.5, "Game Over!");
                StdDraw.show(0);
                
                // program ends
        }
}

