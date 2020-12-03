package cs2321.DLL;
import net.datastructures.Position;
import net.datastructures.PositionalList;

import java.util.Iterator;

/**
 * An implementation of the Doubly Linked List that uses a
 * Positional List with a generic data type.
 * @param <E> the data type
 */
public class DoublyLinkedList<E> implements PositionalList<E> {
    private int size = 0;
    private final Node<E> header;
    private final Node<E> trailer;

    /**
     * Inner class that is responsible for implementing
     * the Position interface and for creating a node
     * to use for the linked list
     * @param <E>
     */
    private static class Node<E> implements Position<E> {
        private Node<E> next;
        private Node<E> prev;
        private E element;

        /**
         * Default constructor that sets the node's
         * element, previous, and next node.
         * @param element the element to be stored
         * @param prev the previous node
         * @param next the next node
         */
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns the element of the node.
         * @return the element of the node
         */
        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        /**
         * Gets the node's next node.
         * @return the next node
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Gets the node's previous node.
         * @return the previous node
         */
        public Node<E> getPrev() {
            return prev;
        }

        /**
         * Sets the node's element to the given element.
         * @param element the element to be stored
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * Sets the node's next node to the given node.
         * @param next the next node
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Sets the node's previous node to the given node.
         * @param prev the previous node
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    /**
     * Default constructor the initializes a header and trailer
     * node that point to each other.
     */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Returns a Position data type given a node. Returns null
     * if the node given is either the header or the trailer.
     * @param node the node to test
     * @return a Position if the node is neither the header nor the trailer, null otherwise.
     */
    private Position<E> position(Node<E> node) {
        // Tests if the node given is the header or trailer
        if (node == header || node == trailer) return null;
        return node;
    }

    /**
     * Tests if the Position is an instance of the current node type.
     * If true, returns node of the position.
     * @param p the position to test
     * @return a node of the position
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        // Tests if the Position is an instance of the current node type
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        // Tests if node is defunct
        if (node.getNext() == null) return null;
        return node;
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests if the list is empty.
     * @return true if there are no elements in the list, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the first Position in the list.
     * @return the first Position in the list (or null if empty)
     */
    @Override
    public Position<E> first() {
        return position(header.getNext());
    }


    /**
     * Returns the last Position in the list.
     * @return the last Position in the list (or null if empty)
     */
    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the Position immediately before a given Position.
     * @param p   a Position of the list
     * @return the Position immediately before (or null if p is first)
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true
        Node<E> curr = validate(p);

        return position(curr.getPrev());
    }

    /**
     * Returns the Position immediately after a given Position.
     * @param p   a Position of the list
     * @return the Position immediately after (or null if p is last)
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true
        Node<E> curr = validate(p);

        return position(curr.getNext());
    }

    /**
     * Inserts an element at the front of the list.
     * @param e the new element
     * @return the Position representing the location of the new element.
     */
    @Override
    public Position<E> addFirst(E e) {
        // Creates a new Node between the header and the original next element.
        Node<E> tmp = new Node<>(e, header, header.getNext());
        header.getNext().setPrev(tmp);
        header.setNext(tmp);

        // Increases size by one.
        size++;

        // Returns a Position representation of the node.
        return position(tmp);
    }

    /**
     * Inserts an element at the rear of the list.
     * @param e the new element
     * @return the Position representing the location of the new element.
     */
    @Override
    public Position<E> addLast(E e) {
        // Creates a new Node between the trailer's previous element and the trailer.
        Node<E> tmp = new Node<>(e, trailer.getPrev(), trailer);
        trailer.getPrev().setNext(tmp);
        trailer.setPrev(tmp);

        // Increases size by one.
        size++;

        // Returns a Position representation of the node.
        return position(tmp);
    }

    /**
     * Inserts an element between two elements.
     * @param e the element to be stored
     * @param pred the predecessor node
     * @param succ the successor node
     */
    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // Creates a new Node between the predecessor node and the successor node given.
        Node<E> curr = new Node<>(e, pred, succ);
        pred.setNext(curr);
        succ.setPrev(curr);

        // Increases size by one.
        size++;
    }

    /**
     * Inserts an element immediately before another Position.
     * @param p the Position before which the insertion takes place
     * @param e the new element
     * @return the Position representing the location of the new element.
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true.
        Node<E> tmp = validate(p);

        // Inserts the node immediately before the given Position.
        addBetween(e, tmp.getPrev(), tmp);
        return tmp;
    }

    /**
     * Inserts an element immediately after another Position
     * @param p the Position after which the insertion takes place
     * @param e the new element
     * @return the Position representing the location of the new element.
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true.z
        Node<E> tmp = validate(p);

        // Inserts the node immediately after the given Position.
        addBetween(e, tmp, tmp.getNext());
        return tmp;
    }

    /**
     * Replaces an element at a given Position
     * @param p the Position of the element to be replaced
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true.
        Node<E> tmp = validate(p);

        // Stores the replaced Position's stored element to return later.
        E replaced = tmp.getElement();

        // Replaces the original Position's element with the given element.
        tmp.setElement(e);

        return replaced;
    }

    /**
     * Removes an element at a given Position.
     * @param p the Position of the element to be removed
     * @return the removed element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        // Tests if p is a valid Position and gets a Node if true.
        Node<E> tmp = validate(p);

        // Stores the replaced Position's stored element to return later.
        E removed = tmp.getElement();

        // Sets the pointers between the given Position's previous and next
        // nodes to each other and removes the original pointers.
        tmp.getPrev().setNext(tmp.getNext());
        tmp.getNext().setPrev(tmp.getPrev());
        tmp.setPrev(null);
        tmp.setNext(null);
        tmp.setElement(null);

        // Decreases size by one.
        size--;

        return removed;
    }

    /**
     * Removes the first element of the list.
     * @return the removed element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    public E removeFirst() throws IllegalArgumentException {

        // Tests if there is a first Position to remove.
        Node<E> curr = validate(position(header.getNext()));

        // Stores the removed Position's element to return later.
        E removed = curr.getElement();

        // Removes the first Position from the list.
        remove(position(curr));

        return removed;
    }

    /**
     * Removes the last element of the list.
     * @return the removed element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    public E removeLast() throws IllegalArgumentException {

        // Tests if there is a last Position to remove.
        Node<E> curr = validate(position(trailer.getPrev()));

        // Stores the removed Position's element to return later.
        E removed = curr.getElement();

        // Removes the last Position from the list.
        remove(position(curr));

        return removed;
    }

    /**
     * Iterator class that allows iteration through all positions.
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> curr = first();

        /**
         * Tests if there is another Position immediately after the current one.
         * @return true if there is another Position immediately after, false otherwise
         */
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        /**
         * Returns the current Position and sets the current Position to the Position
         * immediately after.
         * @return the current Position
         */
        @Override
        public Position<E> next() {
            // Stores the current Position to return later.
            Position<E> tmp = curr;

            // Moves the current Position to the one immediately after it.
            curr = after(tmp);

            return tmp;
        }
    }

    /**
     * Iterable class that allows iteration through all positions.
     */
    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    /**
     * Iterator class that allows iteration through all positions and return it's element.
     */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();
        /**
         * Uses the PositionIterator hasNext() method to check if there is another Position
         * immediately after the current one.
         */
        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        /**
         * Uses the PositionIterator next() method to get the element of the Position,
         * return the element, and move the current Position to the Position
         * immediately after.
         * @return the element of the current Position
         */
        @Override
        public E next() {
            return posIterator.next().getElement();
        }
    }

    /**
     * Iterator for all Positions' elements.
     * @return a new ElementIterator object.
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * Iterator for all Positions
     * @return a new PositionIterator object.
     */
    @Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
}