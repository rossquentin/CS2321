package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	

/**
 * @author ruihong-adm
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public  LinkedBinaryTree( ) {
		//TODO default constructor
	}
	
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/* count only direct child of the node, not further descendant. */
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
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
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		//TODO: implement this method
		return null;
	}
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		//TODO: implement this method
		return null;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		//TODO: implement this method
		return null;
	}
	
	/* Attach trees t1 and t2 as left and right subtrees of external Position. 
	 * if p is not external, throw IllegalArgumentExeption.
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
			throws IllegalArgumentException {
		//TODO: implement this method
	
	}
	
	
	/**
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p has two children, throw IllegalAugumentException. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		return null;
	}
}
