
// A unit test for PostfixExpression.valueOf(String)

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PostfixExpressionTest {
	PostfixExpression expression = new PostfixExpression();

	@Test
	public void testValueOf1() {
		assertEquals(4, expression.valueOf("4 9 %"));
		assertEquals(14, expression.valueOf("3 4 - 5 3 * +"));
	}

	@Test
	public void testValueOf2() {
		assertEquals(3, expression.valueOf("7 4 -"));
	}

	@Test
	public void testValueOf3() {
		assertEquals(11, expression.valueOf("7 4 +"));
	}

	@Test
	public void testValueOf4() {
		assertEquals(1, expression.valueOf("7 4 /"));
	}
	
	@Test
	public void testValueOf5() {
		assertEquals(28, expression.valueOf("7 4 *"));
	}
}