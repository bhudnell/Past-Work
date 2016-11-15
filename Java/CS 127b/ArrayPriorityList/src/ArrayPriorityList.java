/**
 * This class implements a generic collection to store elements where indexes
 * represent priorities and the priorities can change in several ways.
 * 
 * @author Brendon Hudnell
 * @param <E>
 *            The type of all elements stored in this collection
 */
public class ArrayPriorityList<E> implements PriorityList<E> {

	private Object[] data; // The data structure storing elements
	private int n; // The number of meaningful elements

	// Create an empty list with zero elements
	public ArrayPriorityList() {
		data = new Object[20]; // Do NOT increase the initial capacity
		n = 0;
	}

	/**
	 * Return the number of elements currently in this PriorityList
	 * 
	 * @return The number of elements in this PriorityList
	 */
	public int size() {
		return n;
	}

	/**
	 * Return true if there are zero elements in this PriorityList
	 * 
	 * @return true if size() == 0 or false if size() > 0
	 */
	public boolean isEmpty() {
		return (n == 0);
	}

	/**
	 * If possible, insert the element at the given index. If index is out of
	 * the valid range of 0..size(), throw new IllegalArgumentException(); When
	 * size is 3, the only possible values for index are 0, 1, 2, AND 3 because
	 * you can add an element as the new last.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @param el
	 *            The element to insert
	 * @throws IllegalArgumentException
	 */
	public void insertElementAt(int index, E el) throws IllegalArgumentException {
		if (index < 0 || index > size())
			throw new IllegalArgumentException("ArrayPriorityList.insertElementAt: Array index out of bounds");

		if (size() == data.length) {
			Object[] temp = new Object[size() + 20];
			for (int i = 0; i < size(); i++)
				temp[i] = data[i];
			data = temp;
		} else {
			for (int i = size(); i > index; i--)
				data[i] = data[i - 1];
		}
		data[index] = el;
		n++;
	}

	/**
	 * If possible, return a reference to the element at the given index. If
	 * index is out of the valid range of 0..size()-1, throw new
	 * IllegalArgumentException(); When size is 3, the only possible values for
	 * index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @return A reference to the element at index index.
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public E getElementAt(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.getElementAt: Array index out of bounds");

		return (E) data[index];
	}

	/**
	 * If possible, remove the element at the given index. If index is out of
	 * the valid range of 0..size()-1, throw new IllegalArgumentException();
	 * When size is 3, the only possible values for index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void removeElementAt(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.removeElementAt: Array index out of bounds");

		for (int i = index; i < size() - 1; i++)
			data[i] = data[i + 1];
		n--;
	}

	/**
	 * If possible, swap the element located at index with the element at index
	 * + 1. An attempt to lower the priority of the element at index size()-1
	 * has no effect. If index is out of the valid range of 0..size()-1, throw
	 * new IllegalArgumentException(); When size is 3, the only possible values
	 * for index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move
	 * @throws IllegalArgumentException
	 */
	public void lowerPriorityOf(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.lowerPriorityOf: Array index out of bounds");

		if (index != size() - 1) {
			Object temp = data[index];
			data[index] = data[index + 1];
			data[index + 1] = temp;
		}
	}

	/**
	 * If possible, swap the element located at index with the element at
	 * index-1. An attempt to raise the priority at index 0 has no effect. If
	 * index is out of the valid range of 0..size()-1, throw new
	 * IllegalArgumentException(); When size is 3, the only possible values for
	 * index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move
	 * @throws IllegalArgumentException
	 */
	public void raisePriorityOf(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.raisePriorityOf: Array index out of bounds");

		if (index != 0) {
			Object temp = data[index];
			data[index] = data[index - 1];
			data[index - 1] = temp;
		}
	}

	/**
	 * Return a copy of all elements as an array of Objects that is the size of
	 * this PriorityList and in the same order. If there are no elements in this
	 * list, return new Object[0]; A change to the return value must not affect
	 * this PriorityList object.
	 * 
	 * @return An array of Objects where capacity == size()
	 */
	public Object[] toArray() {
		Object[] returnArray = new Object[size()];
		for (int i = 0; i < size(); i++)
			returnArray[i] = data[i];

		return returnArray;
	}

	/**
	 * If possible, move the element at the given index to the end of this list.
	 * An attempt to move the last element to the last has no effect. If the
	 * index is out of the valid range 0..size()-1 throw new
	 * IllegalArgumentException(); When size is 3, the only possible values for
	 * index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void moveToLast(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.moveToLast: Array index out of bounds");

		if (index < size() - 1) {
			Object temp = data[index];
			for (int i = index; i < size() - 1; i++)
				data[i] = data[i + 1];
			data[size() - 1] = temp;
		}
	}

	/**
	 * If possible, move the element at the given index to the front of this
	 * list An attempt to move the top element to the top has no effect. If the
	 * index is out of the valid range of 0..size()-1, throw new
	 * IllegalArgumentException(); When size is 3, the only possible values for
	 * index are 0, 1, and 2.
	 * 
	 * @param index
	 *            The index of the element to move.
	 * @throws IllegalArgumentException
	 */
	public void moveToTop(int index) throws IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new IllegalArgumentException("ArrayPriorityList.moveToTop: Array index out of bounds");

		if (index > 0) {
			Object temp = data[index];
			for (int i = index; i > 0; i--)
				data[i] = data[i - 1];
			data[0] = temp;
		}
	}
}