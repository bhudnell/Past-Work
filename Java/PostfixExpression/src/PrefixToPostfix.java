
/**
  * A class with one method only, no instance variables.
  * valueOf(String prefix) returns a String representation of a postfix expressions.
  * These operators must work:  + - / * and %
  * 
  * @author Brendon Hudnell
  **/

import java.util.Scanner;
import java.util.Stack;

public class PrefixToPostfix {
	public String valueOf(String prefix) {
		String postfix = "";
		Stack<String> s = new Stack<String>();
		Scanner in = new Scanner(prefix);

		while (in.hasNext()) {
			String temp = in.next();
			try {
				Integer.parseInt(temp);
				postfix += temp + " ";
			} catch (NumberFormatException e) {
				if (temp.equals("("))
					s.push(temp);
				else if (temp.equals(")")) {
					while (!s.peek().equals("("))
						postfix += s.pop() + " ";
					s.pop();
				} else {
					while (!s.isEmpty() && !s.peek().equals("(")) {
						if ("+-".contains(temp))
							postfix += s.pop() + " ";
						else if ("*/%".contains(temp) && "*/%".contains(s.peek()))
							postfix += s.pop() + " ";
						else
							break;
					}
					s.push(temp);
				}
			}
		}
		in.close();

		while (!s.isEmpty())
			postfix += s.pop() + " ";

		return postfix.trim();
	}
}
