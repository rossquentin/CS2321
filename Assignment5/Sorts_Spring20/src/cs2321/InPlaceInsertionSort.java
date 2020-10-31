package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
        /* TCJ
         * Insertion sort will swap items until A[i] > A[i-1].
         * When i = n - 1 and A[i] is the smallest item in the array, there are n swaps.
         * Insertion Sort will loop through all n items in the array and perform, at worst, n swaps.
         * At worst case, for n items and n swaps, insertion sort is O(n^2)
         */
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
