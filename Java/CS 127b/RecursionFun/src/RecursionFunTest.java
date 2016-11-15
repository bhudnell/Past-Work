import static org.junit.Assert.*;

import org.junit.Test;

public class RecursionFunTest {
	private RecursionFun rf = new RecursionFun();

	@Test
	public void testCombinations() {
		assertEquals(6, rf.combinations(4, 2));
		assertEquals(5, rf.combinations(5, 1));
		assertEquals(1, rf.combinations(3, 3));
	}

	@Test
	public void testFactorial() {
		assertEquals(1, rf.factorial(0));
		assertEquals(1, rf.factorial(1));
		assertEquals(24, rf.factorial(4));
	}

	@Test
	public void testAddReciprocals() {
		assertEquals(1.0, rf.addReciprocals(1), 0.001);
		assertEquals(1.5, rf.addReciprocals(2), 0.001);
		assertEquals(1.83333, rf.addReciprocals(3), 0.001);
	}

	@Test
	public void testGCD() {
		assertEquals(3, rf.GCD(24, 9));
		assertEquals(10, rf.GCD(50, 20));
		assertEquals(1, rf.GCD(7, 20));
	}

	@Test
	public void testFibonacci() {
		assertEquals(1, rf.fibonacci(1));
		assertEquals(1, rf.fibonacci(2));
		assertEquals(8, rf.fibonacci(6));
	}

	@Test
	public void testUnderScore() {
		assertEquals("hel_lo", rf.underScore("hello"));
		assertEquals("x_xy_y", rf.underScore("xxyy"));
		assertEquals("", rf.underScore(""));
		assertEquals("test", rf.underScore("test"));
	}

	@Test
	public void testNestParen() {
		assertTrue(rf.nestParen("(())"));
		assertTrue(rf.nestParen("((()))"));
		assertFalse(rf.nestParen("((())"));
		assertFalse(rf.nestParen("("));
		assertFalse(rf.nestParen("(x"));
		assertFalse(rf.nestParen("x)"));
		assertFalse(rf.nestParen("(yy)"));
	}

	@Test
	public void testNoAdjacents() {
		assertEquals("yza", rf.noAdjacents("yyzzza"));
		assertEquals("abcd", rf.noAdjacents("abbbcdd"));
		assertEquals("Helo", rf.noAdjacents("Hello"));
		assertEquals("", rf.noAdjacents(""));
	}

	@Test
	public void testConvert() {
		assertEquals("1", rf.convert(1, 2));
		assertEquals("11", rf.convert(3, 2));
		assertEquals("101", rf.convert(5, 2));
		assertEquals("15", rf.convert(13, 8));
		assertEquals("10", rf.convert(8, 8));
		assertEquals("123", rf.convert(123, 10));
		assertEquals("0", rf.convert(0, 10));
	}

	@Test
	public void testIntWithCommas() {
		assertEquals("999", rf.intWithCommas(999));
		assertEquals("1,234", rf.intWithCommas(1234));
		assertEquals("1,007", rf.intWithCommas(1007));
		assertEquals("1,023,004,567", rf.intWithCommas(1023004567));
	}

	@Test
	public void testSumArrayWithinIndexes() {
		int[] x = { 1, 5, 7, 2, 3, 4 };
		assertEquals(0, rf.sumArray(x, 5, 0));
		assertEquals(22, rf.sumArray(x, 0, 5));
		assertEquals(13, rf.sumArray(x, 0, 2));
		assertEquals(9, rf.sumArray(x, 2, 3));
	}

	@Test
	public void testSumArrayAllArray() {
		int[] a = { 2, 4, 6 };
		assertEquals(12, rf.sumArray(a));
	}

	@Test
	public void testReverseArray() {
		int[] a = { 2, 4, 6 };
		rf.reverseArray(a);
		assertEquals(6, a[0]);
		assertEquals(4, a[1]);
		assertEquals(2, a[2]);
	}

	@Test
	public void testArrayRange() {
		assertEquals(2, rf.arrayRange(new int[] { 1, 2, 3 }));
		assertEquals(2, rf.arrayRange(new int[] { 3, 2, 1 }));
		assertEquals(0, rf.arrayRange(new int[] { 3 }));
		assertEquals(3, rf.arrayRange(new int[] { -3, -2, -5, -4 }));
	}

	@Test
	public void testIsSorted() {
		assertTrue(rf.isSorted(new int[] { 1, 2, 5, 5, 6, 6, 7 }));
		assertFalse(rf.isSorted(new int[] { 1, 2, 5, 5, 7, 6, 7 }));
	}

	@Test
	public void testFound() {
		String[] strs = { "Ttt", "Ccc", "Fff", "Ddd", "Hhh", "Aaa" };
		assertTrue(rf.found("Aaa", strs));
		assertTrue(rf.found("Hhh", strs));
		assertFalse(rf.found("Not Here", strs));
	}

	@Test
	public void testBinarySearch() {
		String[] strs = { "Aaa", "Ccc", "Ddd", "Fff", "Hhh", "Ttt" };
		assertEquals(0, rf.binarySearch("Aaa", strs));
		assertEquals(4, rf.binarySearch("Hhh", strs));
		assertEquals(-1, rf.binarySearch("Not here", strs));
	}

	@Test
	public void testAddlastAndGet() {
		LinkedList<String> list = new LinkedList<String>();
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");
		list.addLast("D");
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
		assertEquals("D", list.get(3));
	}

	@Test
	public void testMax() {
		LinkedList<String> list = new LinkedList<String>();
		assertNull(list.max());
		list.addLast("A");
		list.addLast("D");
		list.addLast("C");
		list.addLast("B");
		assertEquals("D", list.max());
	}

	@Test
	public void testOccurencesOf() {
		LinkedList<String> list = new LinkedList<String>();
		list.addLast("A");
		list.addLast("B");
		list.addLast("B");
		list.addLast("D");
		assertEquals(1, list.occurencesOf("A"));
		assertEquals(2, list.occurencesOf("B"));
		assertEquals(0, list.occurencesOf("C"));
		assertEquals(1, list.occurencesOf("D"));
	}

	@Test
	public void testDuplicateAll() {
		LinkedList<String> list = new LinkedList<String>();
		list.addLast("A");
		list.addLast("B");
		list.addLast("A");
		list.addLast("D");
		list.addLast("A");
		list.duplicateAll("A");
		assertEquals("A", list.get(0));
		assertEquals("A", list.get(1));
		assertEquals("B", list.get(2));
		assertEquals("A", list.get(3));
		assertEquals("A", list.get(4));
		assertEquals("D", list.get(5));
		assertEquals("A", list.get(6));
		assertEquals("A", list.get(7));
		assertEquals(8, list.size());
	}
}
