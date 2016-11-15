
/**
 * A unit test for class LinkedSet<E> implements Set<E>
 * 
 * @author Rick Mercer
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinkedSetTest {
	@Test
	public void testGettersWhenEmpty() {
		LinkedSet<String> set = new LinkedSet<String>();
		assertEquals(0, set.size());
		assertTrue(set.isEmpty());
	}

	@Test
	public void testSizeAndAdd() {
		LinkedSet<String> names = new LinkedSet<String>();
		assertEquals(0, names.size());
		assertTrue(names.add("A"));
		assertEquals(1, names.size());
		assertTrue(names.add("B"));
		assertEquals(2, names.size());
		assertTrue(names.add("C"));
		assertEquals(3, names.size());
	}

	@Test
	public void testContainsWhenElementsAreNotAlreadyContained() {
		LinkedSet<String> names = new LinkedSet<String>();
		assertTrue(names.add("A"));
		assertTrue(names.add("B"));
		assertTrue(names.add("C"));
		assertTrue(names.contains("A"));
		assertTrue(names.contains("B"));
		assertTrue(names.contains("C"));
	}

	@Test
	public void testaddAndContains() {
		LinkedSet<Integer> ints = new LinkedSet<Integer>();
		assertFalse(ints.contains(1));
		assertFalse(ints.contains(2));
		assertFalse(ints.contains(3));
		assertTrue(ints.add(1));
		assertTrue(ints.contains(1));
		assertTrue(ints.add(2));
		assertTrue(ints.contains(2));
		assertTrue(ints.add(3));
		assertTrue(ints.contains(3));
		// Can not add 1 since it is already in this set
		assertFalse(ints.add(1));
		assertTrue(ints.add(-54));
		assertTrue(ints.contains(-54));
	}

	@Test
	public void testContainsAgain() {
		LinkedSet<Integer> ints = new LinkedSet<Integer>();
		assertTrue(ints.add(5));
		assertTrue(ints.add(2));
		assertTrue(ints.add(4));
		assertTrue(ints.add(1));
		assertTrue(ints.contains(1));
		assertTrue(ints.contains(2));
		assertFalse(ints.contains(3));
		assertTrue(ints.contains(4));
		assertTrue(ints.contains(5));
	}

	@Test
	public void testTostring() {
		LinkedSet<Integer> ints = new LinkedSet<Integer>();
		assertEquals("[]", ints.toString());
		ints.add(5);
		assertEquals("[5]", ints.toString());
		ints.add(2);
		assertEquals("[5, 2]", ints.toString());
		ints.add(4);
		assertEquals("[5, 2, 4]", ints.toString());
		ints.add(1);
		assertEquals("[5, 2, 4, 1]", ints.toString());
	}

}