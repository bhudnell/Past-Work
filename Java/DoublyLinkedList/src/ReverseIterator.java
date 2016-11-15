/**
 * An interface that must be implemented by class DoublyLinkedList
 * 
 * @author Brendon Hudnell
 *
 * @param <E>
 */
public interface ReverseIterator<E> {

	public boolean hasPrev();

	public E prev();
}
