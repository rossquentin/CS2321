package cs2321;

import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {
    DoublyLinkedList<E> data;
    int size = 0;

    public DLLStack() {
        data = new DoublyLinkedList<>();
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
	public void push(E e) {
		data.addLast(e);
		size++;
	}

	@Override
	public E top() {
        if (data.last() == null) {
            return null;
        }
		return data.last().getElement();
	}

	@Override
	public E pop() {
        if(data.isEmpty()) {
            return null;
        }
        size--;
		return data.removeLast();
	}

}
