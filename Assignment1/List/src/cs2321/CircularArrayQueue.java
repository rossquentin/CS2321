package cs2321;

import net.datastructures.Queue;

/**
 * An implementation of a circular array queue data structure
 * with a generic data type.
 * @param <E> the generic data type
 */
public class CircularArrayQueue<E> implements Queue<E> {
    private final E[] queue;
    private int f, r = 0;
    private int size = 0;
    int capacity;

    /**
     * Constructor that creates a new data array
     * with a given capacity.
     * @param capacity the capacity of the queue.
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int capacity) {
        // Creates a new array for the queue
        // with a given capacity.
        queue = (E[]) new Object[capacity];

        this.capacity = capacity;
    }

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests if the queue is empty
     * @return true if there are no elements in the queue, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Enqueues an element at the rear of the queue
     * @param e  the element to be inserted
     * @throws IllegalStateException if the queue cannot insert an element in the queue
     */
    @Override
    public void enqueue(E e) throws IllegalStateException {
        // Tests if the queue can add the element.
        if (size >= capacity) throw new IllegalStateException("Queue is full, cannot insert element.");

        // Moves the rear index to front index + the size mod capacity.
        r = (f + size) % capacity;

        // Set the element at the rear index to e.
        queue[r] = e;

        // Increase the size by one.
        size++;
    }

    /**
     * Returns, but does not remove, the element at the front of the queue.
     * @return the front element of the queue (or null if empty)
     */
    @Override
    public E first() {
        // Tests to see if the queue has been initialized.
        if (f == r) return null;
        return queue[f];
    }

    /**
     * Removes and returns the element at the front of the queue.
     * @return the element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        // Tests to see if the queue is empty.
        if (size == 0) return null;

        // Stores the value of the removed value to return later.
        E val = queue[f];

        // Sets the element at the front to null.
        queue[f] = null;

        // Moves the front index to the front index + 1 mod capacity.
        f = (f + 1) % capacity;

        // Reduce the size by one.
        size--;

        return val;
    }
}