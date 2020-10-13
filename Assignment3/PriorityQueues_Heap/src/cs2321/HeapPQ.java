package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on a minimum heap.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Quentin Ross
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	
	private final ArrayList<Entry<K,V>> heap = new ArrayList<>();
    private final Comparator<K> comparator;

	/* use default comparator, see DefaultComparator.java */
	public HeapPQ() {
        comparator = new DefaultComparator<>();
	}
	
	/* use specified comparator */
	public HeapPQ(Comparator<K> c) {
        comparator = c;
	}

    /**
     * Inner PQEntry class which implements Entry
     * @param <K>
     * @param <V>
     */
	private static class PQEntry<K,V> implements Entry<K,V> {
	    private K key;
        private V value;
	    private int index;

        /**
         * Default constructor for PQEntry
         * @param key   the key of the entry
         * @param value the value of the entry
         * @param index the location index of the entry
         */
	    public PQEntry(K key, V value, int index) {
	        this.key = key;
	        this.value = value;
	        this.index = index;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public int getIndex() {
	        return index;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setIndex(int j) {
	        index = j;
        }
    }

    /**
     * Returns the parent index of index i.
     * @param i     the index to test
     * @return      the parent index of index i
     */
    @TimeComplexity("O(1)")
	private int parent(int i) {
	    return (i-1)/2;
    }

    /**
     * Returns the left child index of index i.
     * @param i     the index to test
     * @return      the left child index of index i
     */
    @TimeComplexity("O(1)")
    private int leftChild(int i) {
	    return 2*i + 1;
    }

    /**
     * Returns the right child index of index i.
     * @param i     the index to test
     * @return      the right child index of index i.
     */
    @TimeComplexity("O(1)")
    private int rightChild(int i) {
	    return 2*i + 2;
    }

    /**
     * Tests if index i has a left child.
     * @param i     the index to test
     * @return      true if index i has a left child, false otherwise
     */
    @TimeComplexity("O(1)")
    private boolean hasLeft(int i) {
	    return leftChild(i) < heap.size();
    }

    /**
     * Tests if index i has a right child.
     * @param i     the index to test
     * @return      true if index i has a right child, false otherwise
     */
    @TimeComplexity("O(1)")
    private boolean hasRight(int i) {
	    return rightChild(i) < heap.size();
    }
	
	/* 
	 * Return the data array that is used to store entries  
	 * This method is purely for testing purpose of auto-grader
	 */
    @TimeComplexity("O(1)")
	Object[] data() {
        return heap.toArray();
	}

    /**
     * Swaps two entries in the heap given two indices.
     * @param i     the first entry to swap
     * @param j     the second entry to swap
     */
    @TimeComplexity("O(1)")
	private void swap(int i, int j) {
	    Entry<K,V> temp = heap.get(i);
	    heap.set(i, heap.get(j));
	    heap.set(j, temp);
        ((PQEntry<K,V>) heap.get(i)).setIndex(i);
        ((PQEntry<K,V>) heap.get(j)).setIndex(j);
    }

    /**
     * Tests if a given entry is a valid entry.
     * @param entry     the entry to test
     * @return          a PQEntry if the entry is valid
     * @throws IllegalArgumentException if the entry is not valid
     */
    @TimeComplexity("O(1)")
    private PQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException {
        // Tests if the entry is an instance of a PQEntry.
	    if (!(entry instanceof PQEntry)) throw new IllegalArgumentException("Invalid entry");

	    PQEntry<K,V> locator = (PQEntry<K,V>) entry;
	    int locatorIndex = locator.getIndex();

	    // Tests if the entry at the locator index is the same as locator.
	    if (locatorIndex >= size() || heap.get(locatorIndex) != locator) throw new IllegalArgumentException("Invalid entry");

	    // Returns the valid entry
	    return locator;
    }

    /**
     * Tests if a key is a valid key.
     * @param key   the key to test
     * @return      true if the key is valid, false otherwise
     * @throws IllegalArgumentException if the key is not valid
     */
    @TimeComplexity("O(1)")
    private boolean checkKey(K key) throws IllegalArgumentException {
        try {
            // Tests if the key can be compared to itself.
            return (comparator.compare(key,key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    /**
     * Compares to entries' keys.
     * @param a     the first key to test
     * @param b     the second key to test
     * @return      a positive integer if a > b, 0 if a = b, and a negative integer if a < b
     */
    @TimeComplexity("O(1)")
    private int compare(Entry<K,V> a, Entry<K,V> b) {
	    return comparator.compare(a.getKey(), b.getKey());
    }

    /**
     * Maintains the heap property at a given index.
     * @param j the index to start maintaining at
     */
    @TimeComplexity("O(lg n)")
    private void maintainHeap(int j) {
        upheap(j);
        downheap(j);
    }
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param j move the entry at index j higher if necessary, to restore the heap property
	 */
	@TimeComplexity("O(lg n)")
	public void upheap(int j) {
        /* TCJ
         * If the entry at index j violates the heap property, the entry must be pushed up the heap
         * until the heap property is restored.
         * At worst case when j=size-1 and the entry at j is the smallest entry, there will be log(size) swaps.
         * The method calls parent(), compare(), and swap() are all O(1).
         * The worst case time complexity of the upheap method is O(logn) where n is the size of the heap.
         */
        while (j > 0) {
            int p = parent(j);

            // If the parent is less than the child, the heap property is restored.
            if(compare(heap.get(j), heap.get(p)) >= 0) break;

            // Otherwise swap the two.
            swap(j, p);
            j = p;
        }
	}
	
	 /** The entry should be bubbled down to its appropriate position 
	 * @param j move the entry at index j lower if necessary, to restore the heap property
	 */
	@TimeComplexity("O(lg n)")
	public void downheap(int j) {
        /* TCJ
         * If the entry at index j violates the heap property, the entry must be pushed down the heap
         * until the heap property is restored.
         * At worst case when j=0 and the entry at j is the largest entry, there will be log(size) swaps.
         * The method calls hasLeft(), leftChild(), rightChild(), compare(), and swap() are all O(1).
         * The worst case time complexity of the downheap method is O(lg n) where n is the size of the heap.
         */
        while (hasLeft(j)) {
            // Get the left child's index and assume its the smallest child.
            int leftIndex = leftChild(j);
            int smallestChildIndex = leftIndex;

            // If it has a right child
            if (hasRight(j)) {
                // Get the right child's index, then set the smallest child index to the smaller of the two.
                int rightIndex = rightChild(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallestChildIndex = rightIndex;
                }
            }

            // If the heap property is restored, break out of the loop.
            if (compare(heap.get(smallestChildIndex), heap.get(j)) >= 0) break;

            // Otherwise, swap the two entries and set j to the smallest child index.
            swap(j, smallestChildIndex);
            j = smallestChildIndex;
        }
	}

    /**
     * Returns the size of the priority queue
     * @return the size of the priority queue
     */
	@Override
    @TimeComplexity("O(1)")
	public int size() {
		return heap.size();
	}

    /**
     * Tests if the priority queue is empty.
     * @return true if there are no elements in the priority queue, false otherwise
     */
	@Override
    @TimeComplexity("O(1)")
	public boolean isEmpty() {
		return heap.isEmpty();
	}

    /**
     * Inserts an entry in the priority queue.
     * @param key     the key of the new entry
     * @param value   the associated value of the new entry
     * @return        a priority queue with a new entry
     * @throws IllegalArgumentException if the key is not valid
     */
	@Override
    @TimeComplexity("O(lg n)")
    @TimeComplexityAmortized("O(n)")
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        /* TCJ
         * If the newly inserted at the end of the heap violates the heap property, the entry must be upheaped.
         * At worst case when the new entry is the smallest entry, upheap will perform at O(lg n).
         * The method call checkKey() is O(1).
         * The method call addLast() is O(1) when size < capacity, otherwise it is O(n).
         * The method call upheap() is O(lg n).
         * The worst case time complexity of the insert method is O(n) where n is the size of the heap
         * and the arraylist has reached capacity.
         * The worst case amortized time complexity of the insert method is O(lg n) where n is the size of the heap.
         */
	    // Tests if key is valid.
        checkKey(key);

        // Inserts new entry at end of heap and upheap to maintain heap property.
        Entry<K,V> e = new PQEntry<>(key, value, heap.size());
        heap.addLast(e);
        upheap(heap.size() - 1);

        // Returns the inserted entry.
		return e;
	}

    /**
     * Returns, without removing, the minimum entry of the priority queue.
     * @return the minimum entry of the priority queue, null if the priority queue is empty
     */
	@Override
    @TimeComplexity("O(1)")
	public Entry<K, V> min() {
        if (heap.isEmpty()) return null;

		return heap.get(0);
	}

    /**
     * Returns and removes the minimum entry of the priority queue.
     * @return the minimum entry of the priority queue, null if the priority queue is empty
     */
	@Override
    @TimeComplexity("O(lg n)")
	public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;

        // Swaps the first and last element in the heap, removes the last element,
        // then downheaps to restore heap property.
        Entry<K,V> e = heap.get(0);
        swap(0, heap.size()-1);
        heap.removeLast();
        downheap(0);

        // Returns the removed element
		return e;
	}

    /**
     * Removes a specific entry from the priority queue.
     * @param entry  an entry of this priority queue
     * @throws IllegalArgumentException if the entry is not valid
     */
    @Override
    @TimeComplexity("O(lg n)")
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
	    // Validates and returns a PQEntry given the entry.
		PQEntry<K,V> locator = validate(entry);

		// Gets the index of the entry.
		int j = locator.getIndex();

		// If it's the last element of the priority queue, simply remove it.
		if (j == heap.size() - 1) {
		    heap.removeLast();
        }
		// Entry is in the middle or beginning of the priority queue. Remove it, then maintain the heap property.
		else {
		    swap(j, heap.size() - 1);
		    heap.removeLast();
		    maintainHeap(j);
        }
	}

    /**
     * Replaces the key at a given entry.
     * @param entry  an entry of this priority queue
     * @param key    the new key
     * @throws IllegalArgumentException if either the entry or the key are not valid.
     */
	@Override
    @TimeComplexity("O(lg n)")
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        // Validates and returns a PQEntry given the entry.
        PQEntry<K,V> locator = validate(entry);

        // Validates the key.
        checkKey(key);

        // Sets the entry's key, to the given key, then maintains the heap property.
        locator.setKey(key);
        maintainHeap(locator.getIndex());
	}

    /**
     * Replaces the value at a given entry.
     * @param entry  an entry of this priority queue
     * @param value  the new value
     * @throws IllegalArgumentException if the entry is not valid
     */
	@Override
    @TimeComplexity("O(1)")
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        // Validates and returns a PQEntry given the entry.
		PQEntry<K,V> locator = validate(entry);

		// Sets the entry's value to the given value.
		locator.setValue(value);
	}
}
