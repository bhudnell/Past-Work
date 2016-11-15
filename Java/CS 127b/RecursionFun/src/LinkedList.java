public class LinkedList<E extends Comparable<E>> {

	private class Node {
		private E data;
		private Node next;

		public Node(E element) {
			data = element;
			next = null;
		}

		public Node(E element, Node ref) {
			data = element;
			next = ref;
		}
	}

	private Node first;
	private int n;

	public LinkedList() {
		first = null;
		n = 0;
	}

	public int size() {
		return n;
	}

	public void addLast(E el) {
		if (first == null)
			first = new Node(el);
		else
			addLast(el, first);
		n++;
	}

	private void addLast(E el, Node ref) {
		if (ref.next == null)
			ref.next = new Node(el);
		else
			addLast(el, ref.next);
	}

	public E get(int index) {
		return get(index, first);
	}

	// Precondition: 0 <= index < size()
	private E get(int index, Node ref) {
		if (index == 0)
			return ref.data;
		return get(index - 1, ref.next);
	}

	public E max() {
		if (size() == 0)
			return null;

		return max(first, first.data);
	}

	private E max(Node ref, E max) {
		if (ref == null)
			return max;
		E newMax = max;
		if (ref.data.compareTo(max) > 0)
			newMax = ref.data;
		return max(ref.next, newMax);
	}

	public int occurencesOf(E el) {
		return occurencesOf(el, first);
	}

	private int occurencesOf(E el, Node ref) {
		if (ref == null)
			return 0;
		if (ref.data.equals(el))
			return 1 + occurencesOf(el, ref.next);
		return occurencesOf(el, ref.next);

	}

	public void duplicateAll(E el) {
		duplicateAll(el, first);
	}

	private void duplicateAll(E el, Node ref) {
		if (ref != null) {
			if (ref.data.equals(el)) {
				ref.next = new Node(el, ref.next);
				n++;
				duplicateAll(el, ref.next.next);
			} else
				duplicateAll(el, ref.next);
		}
	}
}