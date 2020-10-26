package cs2321;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	public void sort(E[] array) {
        quickSort(array, 0, array.length - 1);
	}

    /**
     * Sorts a subarray of size (end - start).
     * @param array the array to sort
     * @param start the starting index, inclusive, of the sub array
     * @param end   the ending index, inclusive, of the sub array
     */
	private void quickSort(E[] array, int start, int end) {

	    // While we still have a sub array to parse
	    if (start < end) {
	        // Get the pivot index post partitioning.
	        int part = partition(array, start, end);

	        // Sort both sub array less than or greater than te partition's pivot.
	        quickSort(array, start, part-1);
	        quickSort(array, part+1, end);
        }
    }

    /**
     * Partitions a subarray of size (end - start) where items less than a pivot are left of the pivot
     * and items greater than the pivot are right of the pivot.
     * @param array the array to partition
     * @param start the starting index, inclusive, of the sub array
     * @param end   the ending index, inclusive, of the sub array
     * @return      a partition of the sub array
     */
    private int partition(E[] array, int start, int end) {
        // Sets the pivot to the last element
	    E pivot = array[end];

	    // Sets the indices of the range
        int i = start;
        int j = end - 1;

        // While the range >= 0
        while (i <= j) {

            // Check if range condition is violated and if A[i] < A[pivot].
            // If true, increment i.
            while (i <= j && array[i].compareTo(pivot) < 0) {
                i++;
            }

            // Check if range condition is violated and if A[j] >= A[pivot].
            // If true, decrement j.
            while (i <= j && array[j].compareTo(pivot) >= 0 ) {
                j--;
            }

            // If range >= 0, swap i and j, then increment and decrement respectively.
            if (i < j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        // Swap i with the pivot to complete the partition, then return the index of the pivot.
        swap(array, i, end);
        return i;
    }

    /**
     * Swaps two indices in an array.
     * @param array the array to swap with
     * @param i     the first index to swap
     * @param j     the second index to swap
     */
    private void swap(E[] array, int i, int j) {
	    E temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
    }
}
