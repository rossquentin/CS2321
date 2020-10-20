package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	
	public void sort(K[] array) {
		// TODO Auto-generated method stub

        int n = array.length;
        for (int i = 0; i < n-1; i++) {

            int min = i;

            for (int j = i+1; j < n; j++) {
                if(array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }

            if (i != min) {
                K temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
	}

}
