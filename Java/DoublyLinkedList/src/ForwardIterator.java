/**
 * An interface that must be implemented by class DoublyLinkedList
 * 
 * @author Brendon Hudnell
 *
 * @param <E>
 */
public interface ForwardIterator<E> {

	public boolean hasNext();

	public E next();
}
