package cs2321;

import net.datastructures.Entry;
import net.datastructures.SortedMap;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
	
	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)
	
	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		// TODO Add necessary initialization
	}
	
	/* 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	
	@Override
	public int size(){
		// TODO Auto-generated method stub
		return 0;
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
	public Entry<K, V> ceilingEntry(K key) {
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
	public Entry<K, V> higherEntry(K key){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
