package cs2321;

import java.util.Iterator;

import net.datastructures.*;


/**
 * @param <E>
 * @author ruihong-adm
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
    private static class Node<E> implements Position<E> {
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        private E element;

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.element = element;
        }


    }

    private Node<E> root;
    private int size = 0;

    public LinkedBinaryTree() {
        root = null;
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {         // Tests if p is a valid instance type.
            throw new IllegalArgumentException("Not valid position type");
        }
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) {     // Tests if node is defunct.
            throw new IllegalArgumentException("p is no longer in tree");
        }
        return node;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        ArrayList<Position<E>> children = new ArrayList<>(2);
        if (left(p) != null) {
            children.addLast(left(p));
        }
        if (right(p) != null) {
            children.addLast(right(p));
        }
        return children;
    }

    @Override
    /* count only direct child of the node, not further descendant. */
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return root.equals(p);
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
    public Iterator<E> iterator() {
        return new elementIterator();
    }

    private class elementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = positions().iterator();
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }
    }

    public Iterable<Position<E>> inorder() {
        ArrayList<Position<E>> treeList = new ArrayList<>();
        if(!isEmpty()) {
            inorderSubtree(root(), treeList);      // Recursively add all elements in the tree via pre-order traversal.
        }
        return treeList;
    }

    private void inorderSubtree(Position<E> p, ArrayList<Position<E>> list) {
        if (left(p) != null) {
            inorderSubtree(left(p), list);
        }
        list.addLast(p);
        if (right(p) != null) {
            inorderSubtree(right(p), list);
        }
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    private Iterable<Position<E>> preorder() {
        ArrayList<Position<E>> treeList = new ArrayList<>();
        if(!isEmpty()) {
            preorderSubtree(root(), treeList);      // Recursively add all elements in the tree via pre-order traversal.
        }
        return treeList;
    }

    private void preorderSubtree(Position<E> p, ArrayList<Position<E>> list) {
        list.addLast(p);                            // Add p to the list.
        for (Position<E> child : children(p)) {     // children(p) has a left then a right child in that order.
            preorderSubtree(child, list);           // Recursively add to the list down the tree.
        }
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    public void setElement(Position<E> p, E element) throws IllegalArgumentException {
        Node<E> node = validate(p);
        node.setElement(element);
    }

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null) return null;

        if (left(parent) == p) {        // If p is a left child of it's parent, return the right child of the parent
            return right(parent);
        } else {                        // Otherwise, return the left child of the parent.
            return left(parent);
        }
    }

    /* creates a root for an empty tree, storing e as element, and returns the
     * position of that root. An error occurs if tree is not empty.
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = new Node<E>(e, null, null, null);    // Create a new root node.
        size = 1;
        return root;
    }

    /* creates a new left child of Position p storing element e, return the left child's position.
     * If p has a left child already, throw exception IllegalArgumentException.
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = new Node<E>(e, parent, null, null);     // Create new child node with parent as p.
        parent.setLeft(child);              // Set the left child of p as the child.
        size++;                             // Increment size.
        return child;
    }

    /* creates a new right child of Position p storing element e, return the right child's position.
     * If p has a right child already, throw exception IllegalArgumentException.
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = new Node<E>(e, parent, null, null);     // Create new child node with parent as p.
        parent.setRight(child);             // Set the right child of p as the child.
        size++;                             // Increment size.
        return child;
    }

    /* Attach trees t1 and t2 as left and right subtrees of external Position.
     * if p is not external, throw IllegalArgumentException.
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();      // Add the size of the two trees to the total size.
        if (!t1.isEmpty()) {
            t1.root.setParent(node);        // Set t1 as the left sub tree of p.
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(t2.root);     // Set t2 as the right sub tree of p.
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }


    /**
     * If p is an external node ( that is it has no child), remove it from the tree.
     * If p has one child, replace it with its child.
     * If p has two children, throw IllegalArgumentException.
     *
     * @param p who has at most one child.
     * @return the element stored at position p if p was removed.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());    // Get's the child of the node given or null if no children.
        if (child != null) {                        // As long as the child exists,
            child.setParent(node.getParent());      // Set the parent of the child to the parent of the node given.
        }
        if (node.equals(root)) {                    // If the node is the root, simply set the root as the child.
            root = child;
        } else {                                    // Otherwise, check which child the node given is either left or right child
            Node<E> parent = node.getParent();      // of it's parent node.
            if(node == parent.getLeft()) {          // If it's a left child, set the parent's left child to the child node.
                parent.setLeft(child);
            } else {                                // Otherwise, set the parent's right child to the child node.
                parent.setRight(child);
            }
        }
        size--;                                     // Decrease size.
        E element = node.getElement();              // Retrieve removed element to return.
        node.setElement(null);                      // Remove element.
        node.setLeft(null);                         // Remove left child.
        node.setRight(null);                        // Remove right child.
        node.setParent(node);                       // Node is defunct.
        return element;
    }
}
