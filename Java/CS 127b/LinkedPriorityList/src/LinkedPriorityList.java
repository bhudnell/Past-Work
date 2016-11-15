/**
 * This collection class implements an abstract data type to store elements
 * where indexes represent priorities that can change in several ways.
 * 
 * @author Brendon Hudnell
 * @param <E>
 *            The type of all elements stored in this collection
 */
public class LinkedPriorityList<E> implements PriorityList<E> {

	// This private inner class saves lots of typing and is hidden from
	// outsiders
	private class Node {

		// These instance variables can be accessed from the enclosing class
		private E data;
		private Node next;

		// If you don't use this constructor in this class, delete it before
		// submitting to WebCat to avoid losing points for code coverage.
		public Node(E element, Node link) {
			data = element;
			next = link;
		}
	}

	// These instance variables belong to the enclosing class
	private Node first;
	private int size;

	// Create an empty list with zero elements
	public LinkedPriorityList() {
		first = null;
		size = 0;
	}

	/**
	 * Return the number of elements currently in this PriorityList
	 * 
	 * @return The number of elements in this PriorityList
	 */
	public int size() {
		return size;
	}

	/**
	 * Return true if there are zero elements in this PriorityList *
	 * 
	 * @return true if size() == 0 or false if size() > 0
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * If possible, insert the element at the given index. If index is out of
	 * range, throw new IllegalArgumentException();. For example, when size is
	 * 3, the only possible values for index are 0, 1, 2, and 3.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @param el
	 *            The element to insert
	 * @throws IllegalArgumentException
	 */
	public void insertElementAt(int index, E el) throws IllegalArgumentException {
		if (index < 0 || index > size())
			throw new IllegalArgumentException("LinkedPriorityList.insertElementAt: Array index out of bounds");

		if (index == 0) {
			first = new Node(el, first);
			size++;
		} else {
			Node indexNode = first;
			for (int i = 0; i < index - 1; i++)
				indexNode = indexNode.next;
			Node next = indexNode.next;
			indexNode.next = new Node(el, next);
			size++;
		}
	}

	/**
	 * If possible, return a reference to the element at the given index. If
	 * index is out of range, throw new IllegalArgumentException(); When size is
	 * 3, the only possible values for index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @return A reference to to element at index index.
	 * @throws IllegalArgumentException
	 */
	public E getElementAt(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.getElementAt: Array index out of bounds");

		Node indexNode = first;
		for (int i = 0; i < index; i++)
			indexNode = indexNode.next;
		return indexNode.data;

	}

	/**
	 * If possible, remove the element at the given index. If index is out of
	 * range, throw new IllegalArgumentException();
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void removeElementAt(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.removeElementAt: Array index out of bounds");

		if (index == 0) {
			first = first.next;
			size--;
		} else {
			Node oneBeforeIndexNode = first;
			for (int i = 0; i < index - 1; i++)
				oneBeforeIndexNode = oneBeforeIndexNode.next;
			oneBeforeIndexNode.next = oneBeforeIndexNode.next.next;
			size--;
		}
	}

	/**
	 * If possible, swap the element located at index with the element at
	 * index+1. An attempt to lower the priority of the element at index
	 * size()-1 has no effect. If index is out of range, throw new
	 * IllegalArgumentException();
	 * 
	 * @param index
	 *            The index of the element to move
	 * @throws IllegalArgumentException
	 */
	public void lowerPriorityOf(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.lowerPriorityOf: Array index out of bounds");

		if (index != size() - 1) {
			Node indexNode = first;
			for (int i = 0; i < index; i++)
				indexNode = indexNode.next;
			Node nextNode = indexNode.next;
			E temp = indexNode.data;
			indexNode.data = nextNode.data;
			nextNode.data = temp;
		}
	}

	/**
	 * If possible, swap the element located at index with the element at
	 * index-1. An attempt to raise the priority at index 0 has no effect. If
	 * index is out of range, throw new IllegalArgumentException();
	 * 
	 * @param index
	 *            The index of the element to move
	 * @throws IllegalArgumentException
	 */
	public void raisePriorityOf(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.raisePriorityOf: Array index out of bounds");

		if (index != 0) {
			Node previousNode = first;
			for (int i = 0; i < index - 1; i++)
				previousNode = previousNode.next;
			Node indexNode = previousNode.next;
			E temp = previousNode.data;
			previousNode.data = indexNode.data;
			indexNode.data = temp;
		}
	}

	/**
	 * Return a copy of all elements as an array of Objects that is the size of
	 * this PriorityList and in the same order. If there are no elements in this
	 * list, return new Object[0];. A change to the return value must not affect
	 * this ArrayPriorityList object.
	 * 
	 * @return An array of Objects where capacity == size()
	 */
	public Object[] toArray() {
		Object[] returnArray = new Object[size()];
		Node ref = first;
		for (int i = 0; i < size(); i++) {
			returnArray[i] = ref.data;
			ref = ref.next;
		}

		return returnArray;
	}

	/**
	 * If possible, move the element at the given index to the end of this list.
	 * An attempt to move the last element to the last has no effect. If the
	 * index is out of range, throw new IllegalArgumentException();
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void moveToLast(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.moveToLast: Array index out of bounds");

		if (index != size() - 1) {
			if (index == 0) {
				Node indexNode = first;
				first = first.next;
				Node last = first;
				for (int i = index; i < size() - 2; i++)
					last = last.next;
				last.next = indexNode;
				indexNode.next = null;
			} else {
				Node previousNode = first;
				for (int i = 0; i < index - 1; i++)
					previousNode = previousNode.next;
				Node indexNode = previousNode.next;
				previousNode.next = indexNode.next;
				Node last = previousNode;
				for (int i = index; i < size() - 1; i++)
					last = last.next;
				last.next = indexNode;
				indexNode.next = null;
			}
		}
	}

	/**
	 * If possible, move the element at the given index to the front of this
	 * list. An attempt to move the top element to the top has no effect. If the
	 * index is out of range, throw new IllegalArgumentException();
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void moveToTop(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("LinkedPriorityList.moveToTop: Array index out of bounds");

		if (index != 0) {
			Node previousNode = first;
			for (int i = 0; i < index - 1; i++)
				previousNode = previousNode.next;
			Node indexNode = previousNode.next;
			previousNode.next = indexNode.next;
			indexNode.next = first;
			first = indexNode;
		}
	}
}