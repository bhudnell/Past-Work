
/**
 * A unit test for class DoublyLinkedList.
 * 
 * @author Brendon Hudnell
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void testConstructorAndAddFirst() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		assertEquals(0, list.size());
		list.addFirst("A");
		list.addFirst("B");
		list.addFirst("C");
		assertEquals(3, list.size());
		assertEquals("C B A", list.toString());
	}

	@Test
	public void testConstructorAndAddLast() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		assertEquals(0, list.size());
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");
		list.addLast("D");
		assertEquals(4, list.size());
		assertEquals("A B C D", list.toString());
	}

	@Test
	public void testInsertInOrder() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addFirst("B");
		list.addLast("D");
		list.insertInorder("A");
		list.insertInorder("C");
		list.insertInorder("E");
		assertEquals(5, list.size());
		assertEquals("A B C D E", list.toString());
	}

	@Test
	public void testRemove() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addFirst("B");
		list.addLast("D");
		list.insertInorder("A");
		list.insertInorder("C");
		list.insertInorder("E");
		assertEquals(false, list.remove("F"));
		assertEquals(true, list.remove("A"));
		assertEquals("B C D E", list.toString());
		list.remove("E");
		assertEquals("B C D", list.toString());
		list.remove("C");
		assertEquals("B D", list.toString());
	}

	@Test
	public void testForwardIterator() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		ForwardIterator<String> itr = list.forwardIterator();
		assertFalse(itr.hasNext());
		list.addFirst("B");
		list.addLast("D");
		list.insertInorder("A");
		list.insertInorder("C");
		list.insertInorder("E");
		itr = list.forwardIterator();
		assertTrue(itr.hasNext());
		assertEquals("A", itr.next());
		assertEquals("B", itr.next());
		assertEquals("C", itr.next());
		assertEquals("D", itr.next());
		assertEquals("E", itr.next());
		assertFalse(itr.hasNext());
	}

	@Test
	public void testReverseIterator() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		ReverseIterator<String> itr = list.reverseIterator();
		assertFalse(itr.hasPrev());
		list.addFirst("B");
		list.addLast("D");
		list.insertInorder("A");
		list.insertInorder("C");
		list.insertInorder("E");
		itr = list.reverseIterator();
		assertTrue(itr.hasPrev());
		assertEquals("E", itr.prev());
		assertEquals("D", itr.prev());
		assertEquals("C", itr.prev());
		assertEquals("B", itr.prev());
		assertEquals("A", itr.prev());
		assertFalse(itr.hasPrev());
	}
}
