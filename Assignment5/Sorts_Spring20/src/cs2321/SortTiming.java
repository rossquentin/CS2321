package cs2321;

import java.awt.SystemTray;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Time testing for Sorts.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #5
 * @author Instructor
 */

public class SortTiming {

	public static void main(String [] args){
		double avgtime;
		int maximum=100000;
		
		BufferedWriter out = null;
	
		try {
			long startTime = System.nanoTime();
			
			FileWriter fstream = new FileWriter("out.txt", false); //false tells to overwrite data.
			out = new BufferedWriter(fstream);
			
			out.write("Size InPlaceInsertion InPlaceSelection InPlaceHeap MergeSort QuickSort\n");

			
			java.util.Random random = new java.util.Random();
			for (int size=1000; size<=maximum; size = size+1000) {

				//generate test data array: data[size]
				Integer[] testee= new Integer[size];
				for (int i=0; i<size;i++) {
					testee[i]=random.nextInt(100000);
				}

				
				System.out.println("data array size:" + size) ;
				out.write(size+"");

				//call of using testSort
				avgtime = testSort(testee, new InPlaceInsertionSort<Integer>());	
				out.write(" " + String.format("%.0f", avgtime));
				System.out.println(" InPlaceInsertion: " + String.format("%.0f", avgtime) );

				avgtime = testSort(testee, new InPlaceSelectionSort<Integer>());	
				out.write(" " + String.format("%.0f", avgtime));
				System.out.println(" InPlaceSeletion: " + String.format("%.0f", avgtime));

				avgtime = testSort(testee, new InPlaceHeapSort<Integer>());	
				out.write(" " + String.format("%.0f", avgtime));
				System.out.println(" InPlaceHeap: " + String.format("%.0f", avgtime));


				avgtime = testSort(testee, new MergeSort<Integer>());	
				out.write(" " + String.format("%.0f", avgtime));
				System.out.println(" MergeSort: " + String.format("%.0f", avgtime));

				avgtime = testSort(testee, new QuickSort<Integer>());	
				out.write(" " + String.format("%.0f", avgtime));
				System.out.println(" QuickSort: " + String.format("%.0f", avgtime));

				out.write("\n");
				System.out.print("\n");
			}
			
			out.close();
			
			long endTime = System.nanoTime();
			System.out.println("It took " + ( endTime - startTime)/1000000000 + "s");
			
		}catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
				return;

		}
		

		
	}

	/**
	 * Algorithm: testSort
	 * @param arr - an array of Integers to use for empirical measurement of a sort
	 * @param sortClass - the Class representing the sorting algorithm to be run
	 * @return average time taken for a single execution of a sort (in nanoseconds)
	 * 
	 * A copy (clone) of the array is made to test over, so that the original may be reused.
	 */
	public static double testSort(Integer[] arr, Sorter<Integer> sortClass){
		long startTime = 0, endTime = 0;
		int samples = 0;

		System.gc();
		startTime = System.nanoTime();
		//#repeated measurements (no less than 1 millisecond worth of repeats)
		do{
			//create a copy of the array for each test case
			Integer[] testCase = arr.clone();
			//the sorting algorithm, based on the Sorter Class
			sortClass.sort(testCase);
			
			samples++;
			endTime = System.nanoTime();
		}while(endTime - startTime < 1000000);
				
		return (double)(endTime - startTime) / (1000000* samples);
	}
	
	private boolean isSorted (Integer[] arr) {
		for (int i=1;i<arr.length;i++) {
			if (arr[i]<arr[i-1]) return false; 
		}
		return true;
	}
}
