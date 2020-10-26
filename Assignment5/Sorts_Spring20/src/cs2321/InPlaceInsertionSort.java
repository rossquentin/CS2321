package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	public void sort(K[] array) {
        insertionSort(array , array.length);
	}

    /**
     * Sorts, in place, an array of size n via insertion sort.
     * @param array the array to sort
     * @param n     the size of the array
     */
	private void insertionSort(K[] array, int n) {
        // Loop through all elements in the interval [1,n)
        for (int i = 1; i < n; i++) {

            // Loop through all elements in reverse order from [i,0)
            for (int j = i; j > 0 ; j--) {

                // If A[j] < A[j-1], swap A[j] and A[j-1]
                if (array[j].compareTo(array[j-1]) < 0) {
                    K temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }

                // Otherwise we know that the element is already in place.
                else {
                    break;
                }
            }
        }
    }
}
