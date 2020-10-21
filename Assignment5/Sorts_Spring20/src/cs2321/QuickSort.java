package cs2321;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	public void sort(E[] array) {
		// TODO Auto-generated method stub
        quickSort(array, 0, array.length - 1);
	}

	private void quickSort(E[] array, int start, int end) {
	    if (start < end) {
	        int part = partition(array, start, end);
	        quickSort(array, start, part-1);
	        quickSort(array, part+1, end);
        }
    }

    private int partition(E[] array, int start, int end) {
	    E pivot = array[end];
        int i = start;
        int j = end - 1;
        while (i <= j) {
            while (i <= j && array[i].compareTo(pivot) < 0) {
                i++;
            }
            while (i <= j && array[j].compareTo(pivot) >= 0 ) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, i, end);
        return i;
    }

    private void swap(E[] arr, int i, int j) {
	    E temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
    }
}
