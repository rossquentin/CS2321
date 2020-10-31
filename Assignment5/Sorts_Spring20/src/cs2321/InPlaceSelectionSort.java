package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
        /* TCJ
         * Selection sort will swap the minimum item A[j] with the current pointer A[i]
         * Selection sort loops through all n items, at worst and best case, n times.
         * At worst and best case, selection sort is O(n^2), where n is the size of the array.
         */
	    selectionSort(array, array.length);
	}

    /**
     * Sorts, in place, an array of size n via selection sort.
     * @param array the array to sort
     * @param n     the size of the array
     */
	private void selectionSort(K[] array, int n) {
        // Loop through all elements in the interval [0,n-1)
        for (int i = 0; i < n-1; i++) {

            // Assume the minimum element's index is at i.
            int min = i;

            // Loop through all elements in the interval [i+1, n)
            for (int j = i+1; j < n; j++) {

                // If A[j] < A[min], the minimum index is at j.
                if(array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }

            // If the minimum index is not at i, swap the two indices.
            if (i != min) {
                K temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

}
