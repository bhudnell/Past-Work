
// Unit test for ArrayPriorityList<E> implements PriorityList<E>
//
//Author: Brendon Hudnell
//
import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayPriorityListTest {

	@Test
	public void testInsertToLeft() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		assertTrue(list.isEmpty());
		list.insertElementAt(0, "First");
		assertFalse(list.isEmpty());
		// Must shift array elements in this case
		list.insertElementAt(0, "New First");
		assertEquals("New First", list.getElementAt(0));
		assertEquals("First", list.getElementAt(1));
	}

	@Test
	public void testInsertAndGrowArray() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 20; i++)
			list.insertElementAt(0, "" + i);
		list.insertElementAt(20, "Grow");
		assertEquals("19", list.getElementAt(0));
		assertEquals("Grow", list.getElementAt(20));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionInsertElementAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.insertElementAt(1, "Test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionInsertElementAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.insertElementAt(-1, "Test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionInsertElementAtTwoWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.insertElementAt(2, "Test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionGetElementAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.getElementAt(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionGetElementAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.getElementAt(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionGetElementAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.getElementAt(-1);
	}

	@Test
	public void testRemoveElementAt() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);
		list.removeElementAt(0);
		assertEquals("1", list.getElementAt(0));
		assertEquals(4, list.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRemoveElementAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.removeElementAt(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRemoveElementAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.removeElementAt(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRemoveElementAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.removeElementAt(0);
	}

	@Test
	public void testLowerPriorityOf() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);
		list.lowerPriorityOf(4);
		assertEquals("4", list.getElementAt(4));
		list.lowerPriorityOf(0);
		assertEquals("1", list.getElementAt(0));
		assertEquals("0", list.getElementAt(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionLowerPriorityOfAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.lowerPriorityOf(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionLowerPriorityOfAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.lowerPriorityOf(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionLowerPriorityOfAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.lowerPriorityOf(-1);
	}

	@Test
	public void testRaisePriorityOf() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);
		list.raisePriorityOf(0);
		assertEquals("0", list.getElementAt(0));
		list.raisePriorityOf(3);
		assertEquals("3", list.getElementAt(2));
		assertEquals("2", list.getElementAt(3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRaisePriorityOfAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.raisePriorityOf(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRaisePriorityOfAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.raisePriorityOf(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionRaisePriorityOfAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.raisePriorityOf(-1);
	}

	@Test
	public void testToArray() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		Object[] ret = list.toArray();
		assertEquals(0, ret.length);
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);
		ret = list.toArray();
		assertEquals("0", ret[0]);
		assertEquals("4", ret[4]);
		assertEquals(5, ret.length);
	}

	@Test
	public void testMoveToLast() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);

		list.moveToLast(4);
		assertEquals("4", list.getElementAt(4));
		list.moveToLast(0);
		assertEquals("0", list.getElementAt(4));
		assertEquals("1", list.getElementAt(0));
		assertEquals("2", list.getElementAt(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToLastAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToLast(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToLastAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToLast(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToLastAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToLast(0);
	}

	@Test
	public void testMoveToTop() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		for (int i = 0; i < 5; i++)
			list.insertElementAt(i, "" + i);

		list.moveToTop(0);
		assertEquals("0", list.getElementAt(0));
		list.moveToTop(4);
		assertEquals("4", list.getElementAt(0));
		assertEquals("3", list.getElementAt(4));
		assertEquals("2", list.getElementAt(3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToTopAtOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToTop(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToTopAtNegativeOneWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToTop(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionMoveToTopAtZeroWhenSizeIsZero() {
		PriorityList<String> list = new ArrayPriorityList<String>();
		list.moveToTop(0);
	}
}