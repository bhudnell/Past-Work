/**
 * A collection class for storing a list of any type elements using a
 * doubly-linked data structure. This class has a method to return a
 * ForwarIterator and a ReverseIterator
 * 
 * @author Brendon Hudnell
 */
public class DoublyLinkedList<E extends Comparable<E>> implements Iterables<E> {

	private class Node {

		Node prev;
		E data;
		Node next;

		public Node(Node prevRef, E element, Node nextRef) {
			prev = prevRef;
			this.data = element;
			next = nextRef;
		}
	}

	private int n;
	private Node header, trailer;

	// Construct an empty list
	public DoublyLinkedList() {
		header = new Node(null, null, null);
		trailer = new Node(null, null, null);
		header.next = trailer;
		trailer.prev = header;
		n = 0;
	}

	// Return the number of elements in this list
	public int size() {
		return n;
	}

	// Returns the toString of the list
	// Precondition: index > 0 && index < size
	public String toString() {
		String result = "";
		for (Node ref = header.next; ref != trailer; ref = ref.next) {
			result = result + ref.data.toString() + " ";
		}
		return result.trim();
	}

	// Inserts the element at the beginning of the list
	public void addFirst(E el) {
		Node temp = header.next;
		header.next = new Node(header, el, header.next);
		temp.prev = header.next;
		n++;
	}

	// Inserts the element at the end of the list
	public void addLast(E el) {
		Node temp = trailer.prev;
		trailer.prev = new Node(trailer.prev, el, temp.next);
		temp.next = trailer.prev;
		n++;
	}

	// Insert the element to this list so the elements of this
	// list are always in their natural ordering according to compareTo.
	public void insertInorder(E element) {
		Node ref = header.next;
		while (ref.next != null && element.compareTo(ref.data) > 0)
			ref = ref.next;

		Node temp = ref.prev;
		ref.prev = new Node(ref.prev, element, ref);
		temp.next = ref.prev;
		n++;
	}

	// Removes the element from the list
	public boolean remove(E element) {
		for (Node ref = header.next; ref != trailer; ref = ref.next) {
			if (ref.data == element) {
				ref.prev.next = ref.next;
				ref.next.prev = ref.prev;
				n--;
				return true;
			}
		}
		return false;
	}

	// Implements the forwardIterator private inner class
	@Override
	public ForwardIterator<E> forwardIterator() {
		return new MyForwardIter<E>();
	}

	// Implements the reverseIterator private inner class
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new MyReverseIter<E>();
	}

	@SuppressWarnings("hiding")
	private class MyForwardIter<E> implements ForwardIterator<E> {

		private Node currentRef;

		private MyForwardIter() {
			currentRef = header.next;
		}

		@Override
		public boolean hasNext() {
			return currentRef != trailer;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			E temp = (E) currentRef.data;
			currentRef = currentRef.next;
			return temp;
		}
	}

	@SuppressWarnings("hiding")
	private class MyReverseIter<E> implements ReverseIterator<E> {

		private Node currentRef;

		private MyReverseIter() {
			currentRef = trailer.prev;
		}

		@Override
		public boolean hasPrev() {
			return currentRef != header;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E prev() {
			E temp = (E) currentRef.data;
			currentRef = currentRef.prev;
			return temp;
		}
	}
}