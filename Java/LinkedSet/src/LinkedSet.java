/**
 * Implement the Set ADT using a singly-linked data structure. Elements must be
 * stored in Node objects along with a reference to the next Node, or null
 * indicating the last Node in the collection
 * 
 * first -> "G"->"A"->"C"->"T "/
 * 
 * @author Brendon Hudnell
 * @param <E>
 *            The type of element to be stored in the set.
 */
public class LinkedSet<E> {

	// This private inner class saves lots of typing and is hidden from
	// outsiders.
	// Both instance variables can be accessed from the enclosing class.
	private class Node {
		private E data;
		private Node next;

		public Node(E element) {
			data = element;
			next = null;
		}
	}

	// These instance variables belong to the enclosing class LinkedSet<E>
	private Node first;
	private int n;

	/**
	 * Construct an empty ArraySet that can store any type of element.
	 */
	public LinkedSet() {
		n = 0;
	}

	// Return the number of elements in this set
	public int size() {
		return n;
	}

	// Return true if this set is empty
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Add element at the "end" (as the last Node) of this set if element does
	 * not equal another element already in this Set. Return false if element is
	 * in this Set, in which case, this set does not change.
	 * 
	 * Precondition: The type E overrides equals to compare state, nor
	 * references.
	 */
	public boolean add(E element) {
		if (size() == 0) {
			first = new Node(element);
			n++;
			return true;
		}

		Node temp = first;
		for (int i = 1; i < size(); i++) {
			if (temp.data == element)
				return false;
			temp = temp.next;
		}
		temp.next = new Node(element);
		n++;

		return true;
	}

	/**
	 * Return true if element is in this Set
	 * 
	 * Precondition: The type E overrides equals to compare state, nor
	 * references.
	 * 
	 * @param element
	 *            The element to search for
	 * 
	 * @return True if element exist or false if element is not in this set.
	 */
	public boolean contains(E element) {
		Node temp = first;
		for (int i = 0; i < size(); i++) {
			if (temp.data == element)
				return true;
			temp = temp.next;
		}
		return false;
	}

	/**
	 * Return a textual representation of this LinkedSet<E>. After add(5);
	 * add(3); add(7); toString should return the string "[5, 3, 7]"
	 */
	@Override
	public String toString() {
		String returnString = "[";
		Node temp = first;
		for (int i = 0; i < size(); i++) {
			if (i != 0)
				returnString += ", ";
			returnString += "" + temp.data;
			temp = temp.next;
		}
		returnString += "]";
		return returnString;
	}
}
