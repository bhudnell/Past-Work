
/**
  * A class with one method only, no instance variables.
  * valueOf(String postfix) returns the integer value of postfix expressions.
  * These operators must work:  + - / * and %
  * @author Brendon Hudnell
  **/

import java.util.Scanner;
import java.util.Stack;

public class PostfixExpression {

	// Precondition: postfFix is valid, has only int operands ,...
	public int valueOf(String postfix) {
		Stack<Integer> s = new Stack<Integer>();
		Scanner in = new Scanner(postfix);
		int right, left;
		while (in.hasNext()) {
			if (in.hasNextInt())
				s.push(in.nextInt());
			else {
				String op = in.next();
				right = s.pop();
				left = s.pop();
				if (op.equals("+"))
					s.push(left + right);
				else if (op.equals("-"))
					s.push(left - right);
				else if (op.equals("*"))
					s.push(left * right);
				else if (op.equals("/"))
					s.push(left / right);
				else if (op.equals("%"))
					s.push(left % right);
			}
		}
		in.close();
		return s.pop();
	}
}