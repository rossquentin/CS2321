package cs2321;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	public void sort(E[] array) {
		// TODO Auto-generated method stub
        quickSort(array, 0, array.length - 1);
	}

	private void quickSort(E[] arr, int low, int high) {
	    if (low < high) {
	        int part = partition(arr, low, high);
	        quickSort(arr, low, part-1);
	        quickSort(arr, part+1, high);
        }
    }

    private int partition(E[] arr, int low, int high) {
	    E key = arr[high];
	    int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(key)<= 0) {
                i++;
                swap(arr,i, j);
            }
        }
        swap(arr, high, i+1);
	    return i+1;
    }

    private void swap(E[] arr, int i, int j) {
	    E temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
    }
}
