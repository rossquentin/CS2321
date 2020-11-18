package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K, V> extends AbstractMap<K, V> {

    private final ArrayList<Entry<K, V>> table;

    public UnorderedMap() {
        table = new ArrayList<>();
    }


    @Override
    public int size() {
        return table.size();
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    @TimeComplexity("O(n)")
    public V get(K key) {
        /* TCJ
         * Assuming the given key is at the end of the table, the for loop will iterate n times.
         * getKey, equals, and getValue are all O(1).
         * Therefore, because of the loop, the worst case time complexity is O(n)
         */
        for (Entry<K, V> entry : table) {       // Tests every key if the key of the entry is equal to the key.
            if (entry.getKey().equals(key)) {
                return entry.getValue();        // If true, return the value of the key.
            }
        }
        return null;                            // Otherwise return null
    }

    @Override
    @TimeComplexity("O(n)")
    public V put(K key, V value) {
        /* TCJ
         * Assuming the given key is at the end of the table, or the entry doesn't exist, the for loop will iterate n times.
         * getKey, equals, getValue, setValue, and addLast are all O(1).
         * Therefore, because of the loop, the worst case time complexity is O(n)
         */
        for (Entry<K, V> entry : table) {                   // Tests every key if the key of the entry is equal to the key.
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();              // If true, store the old value
                ((mapEntry<K, V>) entry).setValue(value);   // Set the entry's value to the new value
                return oldValue;                            // And return the old value.
            }
        }
        table.addLast(new mapEntry<>(key, value));          // Otherwise, insert the key-value pair at the end of the table
        return null;                                        // And return null.
    }

    @Override
    @TimeComplexity("O(n)")
    public V remove(K key) {
        /* TCJ
         * Assuming the given key is at the end of the table, or doesn't exist, the for loop will iterate n times.
         * Assuming the given key is at the beginning of the table, the remove method is O(n)
         * get, getKey, equals, and getValue are all O(1)
         * Therefore, if the key is at the end or doesn't exist or the key is at the beginning of the table,
         * the worst case time complexity is O(n)
         */
        for (int i = 0; i < table.size(); i++) {        // Tests every key if the key of the entry is equal to the key.
            if (table.get(i).getKey().equals(key)) {
                V oldV = table.get(i).getValue();       // If true, store the old value
                table.remove(i);                        // Remove the entry from the table
                return oldV;                            // And return the old value
            }
        }
        return null;                                    // Otherwise return null
    }


    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return table;                         // Since ArrayList is an iterable object, we can return the table here.
    }
}
