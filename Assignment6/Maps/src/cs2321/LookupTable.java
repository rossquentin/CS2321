package cs2321;

import net.datastructures.*;

public class LookupTable<K extends Comparable<K>, V> extends AbstractMap<K,V> implements SortedMap<K, V> {
	
	/* 
	 * Use Sorted ArrayList for the Underlying storage for the map of entries.
	 * TODO: Uncomment this line;
	 * private ArrayList<Entry<K,V>> table; 
	 */

	public LookupTable(){
		//TO DO: finish the constructor. 
	}

	
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
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

	@Override
	public Entry<K, V> firstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> ceilingEntry(K key)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> floorEntry(K key)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> higherEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey){
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


}
