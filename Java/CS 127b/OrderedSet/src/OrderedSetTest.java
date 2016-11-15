
/**
 * A unit test for the OrderedSet class.
 * 
 * @author Brendon Hudnell
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class OrderedSetTest {

	@Test
	public void testInsertSizeAndTostringinorder() {
		OrderedSet<String> bst = new OrderedSet<String>();
		assertEquals("", bst.toStringInorder());
		assertTrue(bst.insert("d"));
		assertTrue(bst.insert("b"));
		assertTrue(bst.insert("e"));
		assertFalse(bst.insert("e"));
		assertTrue(bst.insert("a"));
		assertEquals(4, bst.size());
		assertEquals("a b d e", bst.toStringInorder());
	}

	@Test
	public void testContains() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertFalse(bst.contains(5));
		bst.insert(5);
		bst.insert(3);
		bst.insert(1);
		bst.insert(4);
		bst.insert(2);
		assertTrue(bst.contains(5));
		assertTrue(bst.contains(4));
		assertTrue(bst.contains(3));
		assertTrue(bst.contains(2));
		assertTrue(bst.contains(1));
		assertFalse(bst.contains(6));
		assertFalse(bst.contains(7));
	}

	@Test
	public void testMax() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertNull(bst.max());
		bst.insert(5);
		bst.insert(12);
		assertEquals(12, (int) bst.max());
		bst.insert(1);
		bst.insert(7);
		bst.insert(30);
		bst.insert(-8);
		assertEquals(30, (int) bst.max());
	}

	@Test
	public void testNodesAtLevel() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertEquals(0, bst.nodesAtLevel(0));
		bst.insert(50);
		assertEquals(1, bst.nodesAtLevel(0));
		assertEquals(0, bst.nodesAtLevel(1));
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		assertEquals(2, bst.nodesAtLevel(1));
		bst.insert(65);
		assertEquals(2, bst.nodesAtLevel(2));
		bst.insert(90);
		assertEquals(3, bst.nodesAtLevel(2));
		assertEquals(0, bst.nodesAtLevel(3));
	}

	@Test
	public void testIntersection() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst2 = new OrderedSet<Integer>();
		bst1.insert(4);
		bst1.insert(2);
		bst1.insert(6);
		bst1.insert(5);
		bst2.insert(2);
		bst2.insert(5);
		bst2.insert(6);
		bst2.insert(9);
		assertEquals("2 5 6", bst1.intersection(bst2).toStringInorder());
	}

	@Test
	public void testUnion() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst2 = new OrderedSet<Integer>();
		bst1.insert(4);
		bst1.insert(2);
		bst1.insert(6);
		bst2.insert(2);
		bst2.insert(5);
		bst2.insert(9);
		assertEquals("2 4 5 6 9", bst1.union(bst2).toStringInorder());
	}

	@Test
	public void testSubSet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		assertEquals("12 25", bst.subset(1, 49).toStringInorder());
		assertEquals("25 50 65", bst.subset(25, 75).toStringInorder());
		assertEquals("", bst.subset(12, 12).toStringInorder());
	}

	@Test
	public void testSameStructureWhenTrue() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst2 = new OrderedSet<Integer>();
		assertTrue(bst1.sameStructure(bst2));
		bst1.insert(5);
		bst1.insert(10);
		bst1.insert(8);
		bst1.insert(77);
		bst2.insert(55);
		bst2.insert(89);
		bst2.insert(79);
		bst2.insert(99);
		assertTrue(bst1.sameStructure(bst2));
	}

	@Test
	public void testSameStructureWhenFalse() {
		OrderedSet<String> bst1 = new OrderedSet<String>();
		OrderedSet<String> bst2 = new OrderedSet<String>();
		bst1.insert("M");
		bst1.insert("B");
		bst1.insert("F");
		bst1.insert("R");
		bst1.insert("Z");
		bst2.insert("M");
		bst2.insert("B");
		bst2.insert("F");
		bst2.insert("Z");
		bst2.insert("R");
		assertFalse(bst1.sameStructure(bst2));

		bst1 = new OrderedSet<String>();
		bst2 = new OrderedSet<String>();
		bst1.insert("M");
		bst1.insert("L");
		bst1.insert("A");
		bst2.insert("M");
		bst2.insert("A");
		bst2.insert("L");
		assertFalse(bst1.sameStructure(bst2));

		OrderedSet<Integer> bst3 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst4 = new OrderedSet<Integer>();
		bst3.insert(5);
		bst3.insert(10);
		bst3.insert(8);
		bst3.insert(12);
		bst4.insert(5);
		bst4.insert(12);
		bst4.insert(10);
		bst4.insert(8);
		assertFalse(bst3.sameStructure(bst4));

		bst3 = new OrderedSet<Integer>();
		bst4 = new OrderedSet<Integer>();
		bst3.insert(3);
		bst3.insert(2);
		bst3.insert(1);
		bst4.insert(2);
		bst4.insert(1);
		bst4.insert(3);
		assertFalse(bst3.sameStructure(bst4));
	}

	@Test
	public void testRemoveNotFoundAndRemoveRoot() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst2 = new OrderedSet<Integer>();
		assertFalse(bst2.remove(0));
		bst1.insert(50);
		bst1.insert(40);
		bst1.insert(60);
		bst2.insert(50);
		bst2.insert(75);
		bst2.insert(60);
		bst2.insert(90);
		assertTrue(bst1.remove(50));
		assertEquals("40 60", bst1.toStringInorder());
		bst1.insert(30);
		bst1.insert(20);
		bst1.insert(10);
		bst1.insert(25);
		bst1.insert(35);
		bst1.insert(32);
		bst1.insert(38);
		assertTrue(bst1.remove(40));
		assertEquals("10 20 25 30 32 35 38 60", bst1.toStringInorder());
		assertTrue(bst2.remove(50));
		assertEquals(3, bst2.size());
		assertEquals("60 75 90", bst2.toStringInorder());
	}
	
	@Test
	public void testRemoveRoot1() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(10);
		bst1.insert(5);
		bst1.insert(20);
		assertTrue(bst1.remove(10));
	}
	
	@Test
	public void testRemoveRoot2() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(90);
		bst1.insert(70);
		bst1.insert(99);
		bst1.insert(60);
		bst1.insert(85);
		assertTrue(bst1.remove(50));
		assertEquals("25 60 70 85 90 99", bst1.toStringInorder());
	}

	@Test
	public void testRemoveNoLeftChild1() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(30);
		bst1.insert(75);
		bst1.insert(80);
		bst1.insert(95);
		bst1.insert(99);
		bst1.insert(85);
		bst1.insert(82);
		bst1.insert(90);
		bst1.insert(87);
		assertTrue(bst1.remove(95));
		assertEquals(10, bst1.size());
		assertEquals("25 30 50 75 80 82 85 87 90 99", bst1.toStringInorder());
		assertTrue(bst1.remove(50));
		assertEquals(9, bst1.size());
		assertEquals("25 30 75 80 82 85 87 90 99", bst1.toStringInorder());
	}
	
	@Test
	public void testRemoveNoLeftChild3() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(30);
		bst1.insert(75);
		bst1.insert(90);
		bst1.insert(80);
		bst1.insert(95);
		assertTrue(bst1.remove(75));
		assertEquals("25 30 50 80 90 95", bst1.toStringInorder());
	}
	
	@Test
	public void testRemoveWithLeftChild1() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(90);
		bst1.insert(70);
		bst1.insert(60);
		bst1.insert(85);
		bst1.insert(99);
		assertTrue(bst1.remove(90));
		assertEquals("25 50 60 70 85 99", bst1.toStringInorder());
	}

	@Test
	public void testRemoveWithLeftChild2() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(30);
		bst1.insert(75);
		bst1.insert(80);
		assertTrue(bst1.remove(25));
		assertEquals("30 50 75 80", bst1.toStringInorder());
	}
	
	@Test
	public void testRemoveWithLeftChild3() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(25);
		bst1.insert(90);
		bst1.insert(70);
		bst1.insert(99);
		assertTrue(bst1.remove(90));
		assertEquals("25 50 70 99", bst1.toStringInorder());
	}
	
	@Test
	public void testRemoveNoLeftChild2() {
		OrderedSet<Integer> bst1 = new OrderedSet<Integer>();
		OrderedSet<Integer> bst2 = new OrderedSet<Integer>();
		bst1.insert(50);
		bst1.insert(75);
		bst1.insert(80);
		bst1.insert(85);
		bst2.insert(50);
		bst2.insert(25);
		bst2.insert(30);
		bst2.insert(40);
		assertTrue(bst1.remove(75));
		assertEquals("50 80 85", bst1.toStringInorder());
		assertFalse(bst1.remove(75));
		assertTrue(bst1.remove(80));
		assertEquals("50 85", bst1.toStringInorder());
		assertTrue(bst2.remove(25));
		assertEquals("30 40 50", bst2.toStringInorder());
		assertTrue(bst2.remove(30));
		assertEquals("40 50", bst2.toStringInorder());
	}
}
