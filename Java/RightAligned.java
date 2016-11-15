/* class RightAligned
 *
 * CSc 127A Spring 16, Project 01
 *
 * Author:  Brendon Hudnell
 * Section: G
 *
 * ---
 *
 * Takes three command line inputs in the range -9 to 99 inclusive and prints them out right aligned.
 * Returns an error if the inputs are out of range.
 */
public class RightAligned 
{  
  public static void main(String[] args) 
  {
    // Declare and initialize the three inputs from the command line
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    int c = Integer.parseInt(args[2]);
    
    // Checks that the inputs are in range. Returns an error message if they are not.
    if (a > 99 || a < -9) {
      System.out.println("ERROR: Variable 'a' is out of range");
      return;
    }
    if (b > 99 || b < -9) {
      System.out.println("ERROR: Variable 'b' is out of range");
      return;
    }
    if (c > 99 || c < -9) {
      System.out.println("ERROR: Variable 'c' is out of range");
      return;
    }
    
    // Sets the default input length to 2 and changes it to 1 if applicable
    int aLen = 2, bLen = 2, cLen = 2;
    if (a >= 0 && a <=9)
      aLen = 1;
    if (b >= 0 && b <=9)
      bLen = 1;
    if (c >= 0 && c <=9)
      cLen = 1;
    
    // Checks the input lengths, finds the applicable length conditions, and prints out the right aligned inputs
    if (aLen == bLen && aLen == cLen) {
      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
    } else {
      if (aLen == 1)
        System.out.println(" "+a);
      else
        System.out.println(a);
      if (bLen == 1)
        System.out.println(" "+b);
      else
        System.out.println(b);
      if (cLen == 1)
        System.out.println(" "+c);
      else
        System.out.println(c);
    }        
  }
}
            
      
      