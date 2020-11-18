package cs2321;

import net.datastructures.*;

public class LookupTable<K extends Comparable<K>, V> extends AbstractMap<K, V> implements SortedMap<K, V> {

    /*
     * Use Sorted ArrayList for the Underlying storage for the map of entries.
     */

    private ArrayList<Entry<K, V>> table;

    public LookupTable() {
        table = new ArrayList<>();
    }

    private int findIndex(K key) {
        int l = 0;                              // Starting indices
        int r = table.size() - 1;

        while (l <= r) {                        // Continue as long as a range exists.
            int mid = (l + r) / 2;              // Get the mid of the left and right indices.
            K k = table.get(mid).getKey();      // Get the key in the table at index mid
            if (k.equals(key)) {                // If we found the key, return the mid index
                return mid;
            } else if (k.compareTo(key) < 0) {  // If the key gotten is less than the given key, search the right half
                l = mid + 1;
            } else {                            // Otherwise search the left half
                r = mid - 1;
            }
        }
        return l;                               // Otherwise the key wasn't found; return the closest index.
    }

    /**
     * Tests if the index is within the table.
     * @param i The index to test.
     * @return  true if i is within the range [0,table.size()), false otherwise.
     */
    private boolean isSafeIndex(int i) {
        return (i >= 0 && i < table.size());
    }

    @Override
    @TimeComplexity("O(lg n)")
    public V get(K key) {
        /* TCJ
         * findIndex is O(lg n), as the algorithm searches through a specific half of the array each iteration.
         * isSafeIndex, get, getKety, equals, and getValue are all O(1)
         * Because there are no loops and the largest time complexity is O(lg n), the worst case time complexity
         * is O(lg n)
         */
        int i = findIndex(key);                                         // Find the index of the key
        if (isSafeIndex(i) && table.get(i).getKey().equals(key)) {      // If the key at index i in the table equals the key
            return table.get(i).getValue();                             // Return the value of the entry.
        }

        return null;                                                    // Otherwise return null.
    }

    @Override
    @TimeComplexity("O(n)")
    public V put(K key, V value) {
        /* TCJ
         * findIndex is O(lg n), as the algorithm searches through a specific half of the array each iteration.
         * isSafeIndex, get, getKey, equals, getValue, setValue are all O(1).
         * If the entry does not exist, the add method is called. Assuming i = 0, add is O(n).
         * Therefore the worst case time complexity is O(n)
         */
        int i = findIndex(key);                                     // Find the index of the key.
        if (isSafeIndex(i) && table.get(i).getKey().equals(key)) {  // If the key at index i in the table equals the key
            V oldV = table.get(i).getValue();                       // Store the old value of the entry to return later
            ((mapEntry<K, V>) table.get(i)).setValue(value);        // Set the value of the entry to the given value
            return oldV;                                            // Return the old value
        }

        table.add(i, new mapEntry<>(key, value));                   // Otherwise the key doesn't exist; add a new entry at index i
        return null;                                                // And return null.
    }

    @Override
    @TimeComplexity("O(n)")
    public V remove(K key) {
        /* TCJ
         * findIndex is O(lg n), as the algorithm searches through a specific half of the array each iteration.
         * isSafeIndex, get, getKey, equals, getValue, setValue are all O(1).
         * If the entry exists, the remove method is called. Assuming i = 0, remove is O(n).
         * Therefore the wost case time complexity is O(n)
         */
        int i = findIndex(key);                                     // Find the index of the key
        if (isSafeIndex(i) && table.get(i).getKey().equals(key)) {  // If the key at index i in the table equals the key
            V oldV = table.get(i).getValue();                       // Store the old value of the entry to return later
            table.remove(i);                                        // Remove the entry from the table
            return oldV;                                            // And return the old value
        }

        return null;                                                // Otherwise the entry doesn't exist; return null.
    }


    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return table;       // As the ArrayList is an iterable object, we can return the table here.
    }

    /**
     * Tests if an index is safe to use. If so, return the entry at index i. Otherwise return null.
     * @param i The index to test
     * @return  The entry at index i if i is in interval [0,table.size()), null otherwise.
     */
    private Entry<K, V> safeEntry(int i) {
        if (!isSafeIndex(i)) return null;
        return table.get(i);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    @Override
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size() - 1);
    }

    @Override
    @TimeComplexity("O(lg n)")
    public Entry<K, V> ceilingEntry(K key) {
        /* TCJ
         * findIndex is O(lg n), as the algorithm searches through a specific half of the array each iteration.
         * safeEntry is O(1), as all it checks is if the index given is a valid index in the table.
         * Therefore the wost case time complexity is O(lg n)
         */
        return safeEntry(findIndex(key));
    }

    @Override
    @TimeComplexity("O(lg n)")
    public Entry<K, V> floorEntry(K key) {
        /* TCJ
         * findIndex is O(lg n), as the algorithm searches through a specific half of the array each iteration.
         * safeEntry is O(1), as all it checks is if the index given is a valid index in the table.
         * Therefore the wost case time complexity is O(lg n)
         */
        int i = findIndex(key);
        if (i == size() || !(key.equals(table.get(i).getKey()))) {
            i--;
        }
        return safeEntry(i);
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return safeEntry(findIndex(key) - 1);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        int i = findIndex(key);
        if (i < table.size() && key.equals(table.get(i).getKey())) {
            i++;
        }
        return safeEntry(i);
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int i = findIndex(fromKey);                                             // Finds the starting index
        while (i < table.size() && !(table.get(i).getKey().equals(toKey))) {    // While i is within the table size and i's key is not the toKey
            buffer.addLast(table.get(i++));                                     // Add the entry to the list.
        }
        return buffer;                                                          // Return the list.
    }


    @Override
    public int size() {
        return table.size();
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }
}
