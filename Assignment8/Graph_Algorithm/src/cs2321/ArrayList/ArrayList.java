package cs2321.ArrayList;

import net.datastructures.List;


import java.util.Iterator;

/**
 * An implementation of the Array List data structure with a
 * generic data type.
 * @param <E> the data type
 */
public class ArrayList<E> implements List<E> {
    private int capacity;
    private int size = 0;
    private E[] arr;

    /**
     * Default constructor when no capacity is given.
     * Creates a new data array with a capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        // Creates a new array with size 16.
        arr = (E[]) new Object[16];
        this.capacity = 16;
    }

    /**
     * Overloaded constructor when a capacity is given.
     * Sets global capacity to the given capacity and creates a
     * new data array with a given capacity.
     * @param capacity the initial capacity of the list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        // Creates a new array with a given capacity.
        arr = (E[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * Checks if a given index is in the interval [0,size).
     * @param i the index to check
     * @param size the maximum size of the array
     * @throws IndexOutOfBoundsException when i is not in the interval [0,size)
     */
    private void checkIndex(int i, int size) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests if the list is empty.
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns, but does not remove, the element at index i.
     * @param  i   the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        // Checks if the index is in the interval [0,size)
        checkIndex(i, size);

        return arr[i];
    }

    /**
     * Replaces the element at index i and returns the replaced element.
     * @param  i   the index of the element to replace
     * @param  e   the new element to be stored
     * @return the replaced element
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        // Checks if the index is in the interval [0,size)
        checkIndex(i, size);

        // Holds on to the values that is going to be replaced to return later.
        E tmp = arr[i];

        // Replaces the element at index i with the given element e.
        arr[i] = e;

        return tmp;
    }

    /**
     * Inserts an element at index i and shifts all subsequent
     * items to the right by one.
     * @param  i   the index at which the new element should be stored
     * @param  e   the new element to be stored
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        // Checks if the index is in the interval [0,size)
        checkIndex(i, size+1);

        // Checks if adding a new element with result in the
        // array list being over capacity.
        checkCapacity();

        // Moves all elements from index i to the end of the list
        // over by one.
        for (int j = size-1; j >= i; j--) {
            arr[j+1] = arr[j];
        }

        // Sets the element at index i to e.
        arr[i] = e;

        // Increases the size by one.
        size++;
    }

    /**
     * Removes the element at index i and shifts all subsequent
     * items to the left by one. Returns the removed element.
     * @param  i   the index of the element to be removed
     * @return the removed element.
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        // Checks if the index is in the interval [0,size)
        checkIndex(i, size);

        // Holds on to the value that is being removed
        // in order to return it later.
        E val = arr[i];

        // Shifts all elements from index i+1 to the end of the
        // array list to the left by one.
        for (int j = i; j < size-1; j++) {
            arr[j] = arr[j+1];
        }

        // Set end of array to null. This element does not exist.
        arr[size-1] = null;

        // Reduces the size by one.
        size--;

        return val;
    }

    /**
     * Returns a new iterator object for the array list.
     * @return an array list iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new arrayIterator();
    }

    /**
     * Implementation of an iterator for the
     * array list data structure.
     */
    private class arrayIterator implements Iterator<E> {
        int cursor = 0;

        /**
         * Tests if the cursor is less than or equal to size()-1
         * @return true if the cursor is less than or equal to size()-1, false otherwise
         */
        @Override
        public boolean hasNext() {
            return cursor <= size - 1;
        }

        /**
         * Returns the element at the cursor's index, then shifts the
         * cursor to the right by one.
         * @return the element at the cursor
         */
        @Override
        public E next() {
            return arr[cursor++];
        }
    }

    /**
     * Inserts an element at the beginning of the array list.
     * @param e the element to be stored
     */
    public void addFirst(E e)  {
        add(0,e);
    }

    /**
     * Inserts an element at the end of the array list.
     * @param e the element to be stored
     */
    public void addLast(E e)  {
        add(size, e);
    }

    /**
     * Removes the first element of the array list and returns it's element.
     * @return the removed element
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    public E removeFirst() throws IndexOutOfBoundsException {
        return remove(0);
    }

    /**
     * Removes the last element of the array list and returns it's element.
     * @return the removed element
     * @throws IndexOutOfBoundsException if i is not in the interval [0,size)
     */
    public E removeLast() throws IndexOutOfBoundsException {
        return remove(size-1);
    }

    /**
     * Returns the capacity of the array list.
     * @return the capacity of the array list.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Tests if the size is greater than or equal to the capacity.
     * If true, copy the data to a new array double the capacity.
     */
    @SuppressWarnings("unchecked")
    private void checkCapacity() {

        // Checks if the size is larger or equal to the capacity.
        if(size >= capacity) {
            // Doubles the capacity.
            capacity *= 2;

            // Creates a new array to copy the
            // original data to.
            E[] tmp = (E[]) new Object[capacity];

            // Copies the data from array arr to array tmp.
            System.arraycopy(arr, 0, tmp, 0, arr.length);

            // Sets the global array arr to array tmp.
            arr = tmp;
        }
    }

    /**
     * Converts the arraylist to an array.
     * @return the data array
     */
    public E[] toArray() {
        return arr;
    }
}