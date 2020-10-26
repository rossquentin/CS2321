package cs2321;

import static java.util.Arrays.copyOfRange;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	public void sort(E[] array) {
        mergeSort(array, array.length);
	}

    /**
     * Sorts an array of size n via merge sort
     * @param array the array to sort
     * @param n     the size of the array
     */
	private void mergeSort(E[] array, int n) {
	    if (array.length <= 1) {
	        return;
        }

	    // Creates two sub arrays of with sizes (mid) and (n - mid)
        int mid = n/2;
        E[] left = copyOfRange(array, 0, mid);
        E[] right = copyOfRange(array, mid, n);

        // Sorts the two sub arrays.
        mergeSort(left, left.length);
        mergeSort(right, right.length);

        // Then merges the two arrays together.
        merge(left, right, array);
    }

	private void merge(E[] left, E[] right, E[] array) {
	    int l = left.length;
	    int r = right.length;
	    int i = 0;
	    int j = 0;
	    int k = 0;

	    // Loops through elements in the left and right sub arrays until either
        // sub array's elements are looped through
	    while (i < l && j < r) {

	        // Inserts the smaller of the two elements into the array.
	        if (left[i].compareTo(right[j]) < 0) {
	            array[k] = left[i++];
            }
	        else {
	            array[k] = right[j++];
            }
	        k++;
        }

	    // Insert all remaining elements in the left sub array into the array.
	    while (i < l) {
	        array[k] = left[i++];
	        k++;
        }

	    // Insert all remaining elements in the right sub array into the array.
	    while(j < r) {
	        array[k] = right[j++];
	        k++;
        }
    }
}

