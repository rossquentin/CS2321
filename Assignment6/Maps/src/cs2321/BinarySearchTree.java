package cs2321;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.SortedMap;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
	
	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)

	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
        tree = new LinkedBinaryTree<>();
        tree.addRoot(null);             // This adds a dummy/sentinel node to the root. This is treated as an external node.
        size = 0;
	}

	/*
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}

    /**
     * Helper method to compare two keys.
     * @param key1  The first key to compare.
     * @param key2  The second key to compare
     * @return      -1 if key1 < key2, 1 if key1 > key2, 0 otherwise.
     */
 	private int compare(K key1, K key2) {
	    return key1.compareTo(key2);
    }

    /**
     * Removes the external position and it's parent.
     * @param p The external position to remove.
     */
    private void removeExternalAndParent(Position<Entry<K,V>> p) {
	    Position<Entry<K,V>> parent = tree.parent(p);       // Gets the parent of the external node.
	    tree.remove(p);                                     // Removes the external node, which makes the parent have 1 child.
	    tree.remove(parent);                                // Removes the parent node.
    }

    /**
     * Finds the position, or it's nearest external position, for the given key for a given starting position p.
     * @param p     The starting position to search from.
     * @param key   The key to search for
     * @return      The position of the given key or the nearest external position.
     */
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key) {
        if (tree.isExternal(p)) {                                   // If the node is external, return the position.
            return p;
        }
        if (compare(key, p.getElement().getKey()) == 0) {           // If we found the key, return the position.
            return p;
        } else if (compare(key, p.getElement().getKey()) < 0) {     // If p's key is less than the given key, search the left sub tree.
            return treeSearch(tree.left(p), key);
        } else {                                                    // Otherwise, the key may be in the right sub tree; search it.
            return treeSearch(tree.right(p), key);
        }
    }

    /**
     * Inserts an entry into an external position and adds two sentinel positions as it's children.
     * @param p     The external position to expand.
     * @param entry The entry to insert into the position
     */
    private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
	    tree.setElement(p, entry);     // Set the entry of the position to the given entry.
	    tree.addLeft(p, null);      // Adds a left external position.
	    tree.addRight(p, null);     // Adds a right external position.
    }

    /**
     * Finds the minimum position from a given starting position.
     * @param p The starting position.
     * @return  The minimum position
     */
    private Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
        Position<Entry<K,V>> curr = p;      // Get's the current position.
        while (tree.isInternal(curr)) {     // Traverses left on the tree while current position is internal
            curr = tree.left(curr);
        }
        return tree.parent(curr);           // The current position is external; return the parent of it.
    }

    /**
     * Finds the maximum position from a given starting position.
     * @param p The starting position.
     * @return  The maximum position
     */
    private Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
        Position<Entry<K,V>> curr = p;      // Get's the current position.
        while (tree.isInternal(curr)) {     // Traverses right on the tree while current position is internal
            curr = tree.right(curr);
        }
        return tree.parent(curr);           // The current position is external; return the parent of it.
    }

	@Override
    @TimeComplexity("O(1)")
	public int size(){
		return (tree.size()-1)/2;   // This is the amount of internal entries.
	}

	@Override
    @TimeComplexity("O(n)")
	public V get(K key) {
	    /* TCJ
	     * Assuming all inserted elements are organized where all children are a left or right child, but none of
	     * the other exist, then the tree resembles a singly linked list.
	     * If the key is located at the end of the tree, or if it doesn't exist but the key would lead to the end of the tree
	     * then treeSearch will run n time recursively. Therefore, treeSearch will be O(n).
	     * isExternal, getElement, and getValue are all O(1).
	     * Therefore the worst case time complexity is O(n).
	     */
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);  // Finds the key in the tree.
        if (tree.isExternal(p)) return null;                    // If it's external, the key doesn't exist. Return null
		return p.getElement().getValue();                       // Otherwise return the value of the position.
	}



	@Override
    @TimeComplexity("O(n)")
	public V put(K key, V value) {
        /* TCJ
         * Assuming all inserted elements are organized where all children are a left or right child, but none of
         * the other exist, then the tree resembles a singly linked list.
         * If the key is located at the end of the tree, or if it doesn't exist but the key would lead to the end of the tree
         * then treeSearch will run n time recursively. Therefore, treeSearch will be O(n).
         * isExternal, getElement, getValue, expandExternal, and setElement are all O(1).
         * Therefore the worst case time complexity is O(n).
         */
        Entry<K,V> entry = new mapEntry<>(key, value);          // Generates a new mapEntry with the key-value given
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);  // Finds the key in the tree.
        if (tree.isExternal(p)) {                               // If the key doesn't exist
            expandExternal(p, entry);                           // Insert and expand the external position.
            return null;
        }
        V oldV = p.getElement().getValue();                     // Otherwise the key exists; get the old value to return later.
        tree.setElement(p, entry);                              // Sets the key-value of the position to the new key-value pair.
		return oldV;                                            // Return the old value
	}

	@Override
    @TimeComplexity("O(n)")
	public V remove(K key) {
        /* TCJ
         * Assuming all inserted elements are organized where all children are a left or right child, but none of
         * the other exist, then the tree resembles a singly linked list.
         * If the key is located at the end of the tree, or if it doesn't exist but the key would lead to the end of the tree
         * then treeSearch will run n time recursively. Therefore, treeSearch will be O(n).
         * isExternal, getElement, getValue, removeExternalAndParent, left, right, and setElement are all O(1).
         * If the tree has a right child at the root and all subsequent children are left children, then treeMin will be
         * O(n) as treeMin traverses to the left most child of a given node, which in this case is for n-2 nodes.
         * Therefore the worst case time complexity is O(n).
         */
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);          // Finds the key in the tree.
        if (tree.isExternal(p)) return null;                            // If the key doesn't exist, return null

        V oldV = p.getElement().getValue();                             // Otherwise key exists; save the old value to return later.

        if (tree.isExternal(tree.left(p)))
            removeExternalAndParent(tree.left(p));                      // If either the left or the right children are external,

        else if (tree.isExternal(tree.right(p)))
            removeExternalAndParent(tree.right(p));                     // Remove that child and it's parent.

        else {
            Position<Entry<K,V>> successor = treeMin(tree.right(p));    // Otherwise find the successor node and it's left external
            Position<Entry<K,V>> external = tree.left(successor);
            tree.setElement(p, successor.getElement());                 // Set the element of the found position to the successor position.
            removeExternalAndParent(external);                          // Then remove the external of the successor and the successor position.
        }
		return oldV;                                                    // Finally, return the old value.
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> treeList = new ArrayList<>(size());       // Generates an iterable ArrayList object
        for (Position<Entry<K,V>> p : tree.inorder()) {                 // Loops through all positions in order
            if (tree.isInternal(p)) treeList.addLast(p.getElement());   // If it's internal, add to the list
        }
		return treeList;                                                // Return the list.
	}

	@Override
	public Entry<K, V> firstEntry() {
        if (isEmpty()) return null;                 // If the tree is empty, there's no "first entry"; return null.
        return treeMin(tree.root()).getElement();   // Otherwise return the minimum entry.
	}

	@Override
	public Entry<K, V> lastEntry() {
        if (isEmpty()) return null;                 // If the tree is empty, there's no "first entry"; return null/
        return treeMax(tree.root()).getElement();   // Otherwise return the maximum entry.
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);      // Finds the position of the key.
        if (tree.isInternal(p))                                     // If we found the key, return the position's entry.
            return p.getElement();

        while (!tree.isRoot(p)) {                                   // Otherwise move up through the tree
            if (p.equals(tree.left(tree.parent(p))))                // Until this position is a left child
                return tree.parent(p).getElement();                 // Return the parent.

            p = tree.parent(p);
        }
        return null;                                                // Otherwise we didn't find a suitable position; return null.
	}

	@Override
	public Entry<K, V> floorEntry(K key)  {
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);      // Finds the position of the key
        if (tree.isInternal(p))                                     // If we found the key, return the position's entry.
            return p.getElement();

        while (!tree.isRoot(p)) {                                   // Otherwise move up through the tree
            if (p.equals(tree.right(tree.parent(p))))               // Until this position is a right child
                return tree.parent(p).getElement();                 // Return the parent.

            p = tree.parent(p);
        }
        return null;                                                // Otherwise we didn't find a suitable position; return null.
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);          // Find the position of the key.
        if (tree.isInternal(p) && tree.isInternal((tree.left(p))))      // If BOTH the key's position and it's left child are internal,
            return treeMax(tree.left(p)).getElement();                  // Return the left position's key

        while (!tree.isRoot(p)) {                                       // Otherwise move up the tree
            if (p.equals(tree.right(tree.parent(p))))                   // Until this position is a right child
                return tree.parent(p).getElement();                     // Return the parent.

            p = tree.parent(p);
        }
        return null;
	}

	@Override
	public Entry<K, V> higherEntry(K key){
        Position<Entry<K,V>> p = treeSearch(tree.root(), key);          // Find the position of the key.
        if (tree.isInternal(p) && tree.isInternal((tree.right(p))))     // If BOTH the key's position and it's right child are internal,
            return treeMin(tree.right(p)).getElement();                 // Return the right position's key.

        while (!tree.isRoot(p)) {                                       // Otherwise move up the tree
            if (p.equals(tree.left(tree.parent(p)))  )                  // Until this position is a left child
                return tree.parent(p).getElement();                     // Return the parent

            p = tree.parent(p);
        }
        return null;                                                    // Otherwise we didn't find a suitable position; return null.
	}

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        ArrayList<Entry<K,V>> treeList = new ArrayList<>(size());       // Create an iterable ArrayList object
        if (compare(fromKey, toKey) < 0) {                              // If toKey-fromKey > 0, recursively add to the list.
            subMapRecurse(fromKey, toKey, tree.root(), treeList);
        }
		return treeList;                                                // Return the list.
	}

	private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p, ArrayList<Entry<K,V>> list) {
	    if (tree.isInternal(p)) {                                           // Base case: position is internal
	        if (compare(p.getElement().getKey(), fromKey) < 0) {            // If the position's key is less than fromKey
	            subMapRecurse(fromKey, toKey, tree.right(p), list);         // The relevant entries are in the right sub tree
            } else {
	            subMapRecurse(fromKey, toKey, tree.left(p),  list);         // First attempt the left sub tree
	            if (compare(p.getElement().getKey(), toKey) < 0) {          // If the position is in the range of fromKey to toKey
	                list.addLast(p.getElement());                           // Add the entry to the list
	                subMapRecurse(fromKey, toKey, tree.right(p), list );    // And continue down the right subtree.
                }
            }
        }
    }

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}
}
