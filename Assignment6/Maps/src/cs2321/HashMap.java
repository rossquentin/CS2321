package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    /* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
     *
     */
    private UnorderedMap<K, V>[] table;
    int size;  // number of mappings(entries)
    int capacity; // The size of the hash table.
    // int DefaultCapacity = 17; //The default hash table size

    /* Maintain the load factor <= 0.75.
     * If the load factor is greater than 0.75,
     * then double the table, rehash the entries, and put then into new places.
     */
    double loadfactor = 0.75;

    /**
     * Constructor that takes a hash size
     *
     * @param hashtablesize: the number of buckets to initialize
     */
    public HashMap(int hashtablesize) {
        size = 0;
        capacity = hashtablesize;
        table = new UnorderedMap[capacity];
    }

    /**
     * Constructor that takes no argument
     * Initialize the hash table with default hash table size: 17
     */
    public HashMap() {
        this(17);
    }

    /* This method should be called by map an integer to the index range of the hash table
     */
    private int hashValue(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /*
     * The purpose of this method is for testing if the table was doubled when rehashing is needed.
     * Return the the size of the hash table.
     * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
     */
    public int tableSize() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @TimeComplexityExpected("O(1)")
    public V get(K key) {
        /* TCJ
         * Assuming no collisions, there is only space for a single key-value pair to be placed in a unique hashed index.
         * Because bucket.get(key) is O(1) for no collisions, and accessing an array is O(1),
         * the expected worst case time complexity is O(1).
         */
        UnorderedMap<K,V> bucket = table[hashValue(key)];   // Gets the bucket associated with the hashvalue of the key
        if (bucket == null) return null;                    // If the bucket is null, return null
        return bucket.get(key);                             // Otherwise find the value given the key in the bucket.
    }

    @Override
    @TimeComplexityExpected("O(1)")
    public V put(K key, V value) {
        /* TCJ
         * Assuming no collisions, there is only space for a single key-value pair to be placed in a unique hashed index.
         * bucketPut() simply puts the key-value pair into the array, and is O(1). Assuming we don't have to resize,
         * the expected worst case time complexity is O(1)
         */
        V v = bucketPut(key, value);                // Gets the value from the bucket.
        if (size/(double)capacity > loadfactor) {   // If putting the entry would increase the table size past the load factor
            resize(2 * capacity);           // Double the table's capacity
        }
        return v;                                   // Finally, return the value.
    }

    /**
     * Resizes and rehashes the table given a new capacity.
     * @param capacity  The new capacity
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size);
        for (Entry<K, V> entry : entrySet()) {                      // Add all elements to a new temporary buffer
            buffer.addLast(entry);
        }

        this.capacity = capacity;                                   // Set the capacity equal to the given capacity
        table = (UnorderedMap<K, V>[]) new UnorderedMap[capacity];  // Create a new hashtable with the new capacity
        size = 0;                                                   // Reset the size.
        for (Entry<K, V> entry : buffer) {                          // Put all entries into the new table
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Helper method to put a key-value pair into the bucket
     * @param key   The key to insert
     * @param value The value to insert
     * @return      The value of the previous entry if the key already existed, null otherwise.
     */
    private V bucketPut(K key, V value) {
        UnorderedMap<K, V> bucket = table[hashValue(key)];          // Get the bucket of the given key.
        if (bucket == null) {                                       // If the bucket is empty, create the bucket
            bucket = table[hashValue(key)] = new UnorderedMap<>();
        }

        int oldSize = bucket.size();
        V v = bucket.put(key, value);                               // Put the key-value pair into the map of the bucket.
        size += (bucket.size() - oldSize);                          // Increase the size if the size changed
        return v;                                                   // Return the value from the put method.
    }


    @Override
    @TimeComplexityExpected("O(1)")
    public V remove(K key) {
        /* TCJ
         * Assuming no collisions, there is only space for a single key-value pair to be placed in a unique hashed index.
         * Accessing an array, size, and remove are all O(1) for no collisions. Therefore, the expected
         * worst case time complexity is O(1).
         */
        UnorderedMap<K, V> bucket = table[hashValue(key)];      // Get the bucket of the given key.
        if (bucket == null) {                                   // If the bucket is empty, return null
            return null;
        }
        int oldSize = bucket.size();
        V v = bucket.remove(key);                               // Remove the entry with the given key.
        size -= (oldSize - bucket.size());                      // Decrease the size if it changed.
        return v;                                               // Return the value from the remove method.
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size);
        for (int i = 0; i < capacity; i++) {                        // Loop through all buckets in the hash map
            if (table[i] != null) {                                 // If the bucket is not null
                for (Entry<K, V> entry : table[i].entrySet()) {     // Add all entries in the bucket
                    buffer.addLast(entry);
                }
            }
        }
        return buffer;                                              // Return the entry set.
    }
}
