package cs2321;

public class   Josephus {
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @param persons  an array of string which contains all player names.
	 * @param k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a doubly linked list in the order when the players were out of the game. 
	 *         the last one in the list is the winner.
     * @throws IllegalArgumentException if k < 0 or if there are no elements in persons.
	 */
	public DoublyLinkedList<String> order(String[] persons, int k ) throws IllegalArgumentException{

	    if (k < 1) {
	        throw new IllegalArgumentException("Invalid k");
        }

	    if (persons.length == 0) {
	        throw new IllegalArgumentException("No persons in array");
        }

	    // Creates a queue of size persons.length and a doubly linked list
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(persons.length);
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // Enqueues all elements in persons to the queue
        for (String person : persons) {
            queue.enqueue(person);
        }

        // Loops though until
        while(!queue.isEmpty()) {
            // Enqueues the front of the queue k-1 times.
            for (int i = 1; i < k; i++) {
                queue.enqueue(queue.dequeue());
            }
            // Dequeue the front of the queue and add that to the list.
            list.addLast(queue.dequeue());
        }

		return list;
	}	
}
