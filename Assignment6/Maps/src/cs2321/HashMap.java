package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		// TODO Add necessary initialization

	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		// TODO Add necessary initialization
		
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
		// TODO Auto-generated method stub
		return 0;
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
	

}
