package cs2321;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	public void sort(K[] array) {
	    heapSort(array, array.length);
    }

    /**
     * Sorts, in place, an array of size n via heap sort.
     * @param array the array to sort
     * @param n     the size of the array
     */
    private void heapSort(K[] array, int n) {
        // Create the max-heap of the array.
        for (int i = (n/2)-1; i >= 0; i--) {
            downheap(array, i, n);
        }

        // Swap the first element in the array with the last one still in the heap,
        // then downheap all elements in the interval [0, i) where i is the
        // index of the swapped element.
        for (int i = n-1; i >= 1 ; i--) {
            swap(array, 0,i);
            downheap(array, 0, i);
        }
    }

    /**
     * Returns the left child's index given a parent index i.
     * @param i     the index of the parent.
     * @return      the index of the left child.
     */
    private int left(int i) {
	    return 2 * i + 1;
    }

    /**
     * Returns the left child's index given a parent index i.
     * @param i     the index of the parent.
     * @return      the index of the left child.
     */
    private int right(int i) {
	    return 2 * i + 2;
    }

    /**
     * Downheaps an element i to it's correct place or until i >= j.
     * @param array the array to perform the downheap on
     * @param i     the index of the element to downheap
     * @param j     the maximum index that i could be
     */
    private void downheap(K[] array, int i, int j) {

        // While a left child exists
	    while(left(i) < j) {

	        // Gets the left child's index and assume A[leftIndex] is the largest of the two children.
	        int leftIndex = left(i);
	        int largestIndex = leftIndex;

	        // If a right child exists.
	        if (right(i) < j) {

	            // Gets the right child's index and compares A[leftIndex] to A[rightIndex]
                // If A[rightIndex] > A[leftIndex], then the right child is the larger of the two children.
	            int rightIndex = right(i);
	            if(array[leftIndex].compareTo(array[rightIndex]) < 0) {
	                largestIndex = rightIndex;
                }
            }

	        // If the heap property is not violated, break out of the loop.
	        if (array[largestIndex].compareTo(array[i]) <= 0) break;

	        // Otherwise, swap i with the largest child's index and set i to said index.
	        swap(array, i, largestIndex);
	        i = largestIndex;
        }
    }

    /**
     * Swaps two indices in an array.
     * @param array the array to swap with
     * @param i     the first index to swap
     * @param j     the second index to swap
     */
    private void swap(K[] array, int i, int j) {
        K temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
