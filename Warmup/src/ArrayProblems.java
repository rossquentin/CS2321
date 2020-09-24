public class ArrayProblems {

	/*
	Given an array of integers nums, sort the array in ascending order.

	Example 1:
	Input: nums = [5,2,3,1]
	Output: [1,2,3,5]
	
	Example 2:
	Input: nums = [5,1,1,2,0,0]
	Output: [0,0,1,1,2,5]
	*/

    /**
     * Sorts the array in-place via insertion sort from least to greatest
     * @param nums The array to be sorted
     * @return A sorted array
     */
    //@TimeComplexity("O(n^2)")
	public static int[] sortArray(int[] nums) {
        /*  TCJ
            Given a completely reversed list, insertion sort will have to
            traverse the array n times through n elements. Given this behaviour,
            the worst case time complexity should be O(n).
         */
        if (nums.length == 0) {
            return nums;
        }
        int n = nums.length;

        for (int i = 1; i < n;  i++) {
            int key = nums[i];
            int j = i - 1;

            while(j >= 0 && nums[j] > key) {
                nums[j+1] = nums[j];
                j--;
            }

            nums[j+1] = key;
        }
		return nums;
	}

	/*
	 * Find the kth largest element in an unsorted array.
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	   Example1: Input: [3,2,1,5,6,4] and k = 2
			    Output: 5
	   Example2: Input: [3,2,3,1,2,4,5,5,6] and k = 4
			    Output: 4
	*/

    /**
     * Finds the kth largest element in an unsorted array.
     * @param nums the array to find the kth largest element
     * @param k the kth largest element to find in the array
     * @return the kth largest element in the array
     * @throws IndexOutOfBoundsException if k is greater than the size of the array or is less than 1
     */
    //@TimeComplexity("O(n^2)")
    public static int findKthLargest(int[] nums, int k) throws IndexOutOfBoundsException {
        /*  TCJ
            Sorting the array takes at worst O(n^2) time to sort. Finding the
            element takes O(1) time. Therefore, because sorting has a worse time
            complexity, the method has a time complexity O(n^2)
         */
        sortArray(nums);
        return nums[nums.length - k];
    }
    
}
