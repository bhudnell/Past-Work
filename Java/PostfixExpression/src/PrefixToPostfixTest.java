import static org.junit.Assert.*;

import org.junit.Test;

public class PrefixToPostfixTest {
	PrefixToPostfix expression = new PrefixToPostfix();

	@Test
	public void testValueOf() {
		assertEquals("1 2 3 4 + 5 / 6 * % + 7 -", expression.valueOf("1 + 2 % ( ( 3 + 4 ) / 5 * 6 ) - 7"));
	}

}
