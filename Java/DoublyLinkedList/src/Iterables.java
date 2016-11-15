/**
 * An interface that must be implemented by class DoublyLinkedList. Both methods
 * forwardIterator and reverseIteratorrequire an inner class inside
 * DoublyLinkedList.
 * 
 * @author Brendon Hudnell
 *
 * @param <E>
 */

public interface Iterables<E> {
	public ForwardIterator<E> forwardIterator();

	public ReverseIterator<E> reverseIterator();
}
