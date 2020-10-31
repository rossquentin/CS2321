package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * TODO:  Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private int size;
	private ArrayList<Entry<K,V>> table;
	public UnorderedMap() {
		// TODO Auto-generated constructor stub
        size = 0;
        table = new ArrayList<>();
	}
		

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
        for (Entry<K,V> entry : table) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
        for (Entry<K,V> entry : table) {
            if (entry.getKey().equals(key)) {
                entry.
            }
        }
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
