package cs2321;

import static java.util.Arrays.copyOfRange;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	public void sort(E[] array) {
		// TODO Auto-generated method stub

        if (array.length <= 1) {
            return;
        }

        int mid = array.length/2;
        E[] left = copyOfRange(array, 0, mid);
        E[] right = copyOfRange(array, mid, array.length);

        sort(left);
        sort(right);

        merge(left, right, array);
	}

	private void merge(E[] left, E[] right, E[] arr) {
	    int i = 0;
	    int j = 0;
	    int k = 0;

	    while (i < left.length && j < right.length) {
	        if (left[i].compareTo(right[j]) < 0) {
	            arr[k] = left[i++];
            }
	        else {
	            arr[k] = right[j++];
            }
	        k++;
        }

	    while (i < left.length) {
	        arr[k] = left[i++];
	        k++;
        }

	    while(j < right.length) {
	        arr[k] = right[j++];
	        k++;
        }
    }
}

