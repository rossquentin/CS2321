package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	
	public void sort(K[] array) {
		// TODO Auto-generated method stub

        int n = array.length;

        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 ; j--) {
                if (array[j].compareTo(array[j-1]) < 0) {
                    K temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = array[j];
                }
            }
        }
	}
}
