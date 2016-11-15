
/**
  * A unit test for SortedSet
  *
  * @author Brendon Hudnell
  */
import static org.junit.Assert.*;
import org.junit.Test;

public class SortedSetTest {

	@Test
	public void testGettersWhenEmpty() {
		SortedSet names = new SortedSet(10);
		assertTrue(names.isEmpty());
		assertEquals(0, names.size());
		assertEquals("[]", names.toString());
	}

	@Test
	public void testIsEmpty() {
		SortedSet names = new SortedSet(10);
		assertTrue(names.insertInOrder("First"));
		assertEquals("First", names.get(0));
		assertEquals(1, names.size());
		assertFalse(names.isEmpty());
	}

	@Test
	public void testInsertInOrderWithoutGrowingArray() {
		SortedSet names = new SortedSet(10);
		names.insertInOrder("Steve");
		names.insertInOrder("David");
		names.insertInOrder("Frank");
		names.insertInOrder("Amy");
		names.toString();

		assertFalse(names.insertInOrder("Amy"));

		assertEquals("Amy", names.get(0));
		assertEquals("David", names.get(1));
		assertEquals("Frank", names.get(2));
		assertEquals("Steve", names.get(3));
	}

	@Test
	public void testInsertInOrderWithGrowingArray() {
		SortedSet names = new SortedSet(3);
		names.insertInOrder("Steve");
		names.insertInOrder("David");
		names.insertInOrder("Frank");
		names.insertInOrder("Amy");

		assertEquals("Amy", names.get(0));
		assertEquals("David", names.get(1));
		assertEquals("Frank", names.get(2));
		assertEquals("Steve", names.get(3));
	}

	@Test
	public void testRemove() {
		SortedSet names = new SortedSet(3);
		names.insertInOrder("Steve");
		names.insertInOrder("David");
		names.insertInOrder("Frank");
		names.insertInOrder("Amy");

		assertFalse(names.remove("Paul"));
		assertTrue(names.remove("Frank"));
		assertFalse(names.contains("Frank"));
		assertEquals("Steve", names.get(2));
	}

	@Test
	public void testUnionNoOverlap() {
		SortedSet first = new SortedSet(4);
		first.insertInOrder("A");
		first.insertInOrder("B");
		first.insertInOrder("C");
		first.insertInOrder("D");
		SortedSet second = new SortedSet(4);
		second.insertInOrder("E");
		second.insertInOrder("F");
		second.insertInOrder("G");
		second.insertInOrder("H");
		SortedSet test = new SortedSet(8);
		test.insertInOrder("A");
		test.insertInOrder("B");
		test.insertInOrder("C");
		test.insertInOrder("D");
		test.insertInOrder("E");
		test.insertInOrder("F");
		test.insertInOrder("G");
		test.insertInOrder("H");

		SortedSet ret = first.union(second);

		assertEquals(test.toString(), ret.toString());
	}

	@Test
	public void testUnionOverlap() {
		SortedSet first = new SortedSet(4);
		first.insertInOrder("A");
		first.insertInOrder("E");
		first.insertInOrder("C");
		first.insertInOrder("G");
		SortedSet second = new SortedSet(4);
		second.insertInOrder("A");
		second.insertInOrder("B");
		second.insertInOrder("G");
		second.insertInOrder("C");
		SortedSet test = new SortedSet(5);
		test.insertInOrder("A");
		test.insertInOrder("B");
		test.insertInOrder("C");
		test.insertInOrder("E");
		test.insertInOrder("G");

		SortedSet ret = first.union(second);

		assertEquals(test.toString(), ret.toString());
	}
}