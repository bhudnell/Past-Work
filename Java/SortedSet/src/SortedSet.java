/**
 * The SortedSet class
 * 
 * @author Brendon Hudnell
 *
 *         Contains the methods for the SortedSet class. This includes
 *         functionality to determine if the array is empty, finding the size of
 *         the array, getting a string at a specific element, inserting an
 *         element in order, determining if an element is in the array, removing
 *         an element, and finding the union of two SortedSets
 */
public class SortedSet {

	private String[] data;
	private int size;

	// Construct an empty SortedSet with the capacity to store initialCapacity
	// elements.
	// insertInOrder will grow the array whenever needed.
	public SortedSet(int initialCapacity) {
		data = new String[initialCapacity];
		size = 0;
	}

	// Return true if there are no elements in this Bag.
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	// Return the number of elements in this SortedSet.
	public int size() {
		return size;
	}

	// Return the string at the given index
	public String get(int index) {
		return data[index];
	}

	// Insert the new element in the correct position according to compareTo if
	// element does not exist in this set, then return true. If element exists,
	// return false.
	// If the array does not have the capacity to add element, grow the array
	public boolean insertInOrder(String element) {

		if (contains(element))
			return false;

		// grows array if at full capacity
		if (data.length == size) {
			String[] temp = new String[size + 5];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		int spotToInsert = 0;
		while (spotToInsert < size && element.compareTo(data[spotToInsert]) > 0)
			spotToInsert++;

		// shifts elements to the right one space
		for (int i = size; i > spotToInsert; i--)
			data[i] = data[i - 1];

		data[spotToInsert] = element;
		size++;

		return true;
	}

	// Return a textual version of this set where elements are separate by ", ".
	// For example: '[Element1, Element2, Third, 4th]' or '[]' if empty
	public String toString() {
		String ret = "[";
		for (int i = 0; i < size; i++) {
			if (i != 0)
				ret += ", ";
			ret += data[i];
		}
		ret += "]";
		return ret;
	}

	// Return true if element is found in this SortedSet, false if element does
	// not exist
	public boolean contains(String element) {
		for (int i = 0; i < size; i++) {
			if (element.equals(data[i]))
				return true;
		}
		return false;
	}

	// Remove the element if found and return true.
	// Return false if this set does not contains element
	public boolean remove(String element) {

		// finds the index of the element to be removed
		int spotToDelete = 0;
		while (spotToDelete < size && element.compareTo(data[spotToDelete]) != 0)
			spotToDelete++;

		if (spotToDelete == size)
			return false;

		// shifts elements left one space
		for (int i = spotToDelete; i < size; i++)
			data[i] = data[i + 1];

		data[size - 1] = null;
		size--;
		return true;
	}

	// Return a SortedSet that is the union of this SortedSet and the other
	// SortedSet.
	// For example, if A = ["A", "C", "E", "G"] and B = ["A", "B", "C", "G"]
	// then A.union(B) = ["A", "B", "C", "E", "G"].
	public SortedSet union(SortedSet other) {
		SortedSet ret = new SortedSet(this.size + other.size);

		for (int i = 0; i < this.size; i++)
			ret.insertInOrder(this.data[i]);
		for (int i = 0; i < other.size; i++)
			ret.insertInOrder(other.data[i]);

		return ret;
	}
}
