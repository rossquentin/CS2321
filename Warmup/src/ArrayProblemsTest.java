import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	sortArray CASE 1:
	Input: nums = [5,2,3,1]
	Output: [1,2,3,5]
	 */
	@Test
	void testSortArray1() {
		int[] nums= {5,4,3,1};
		int[] expected = {1,3,4,5};
		assertArrayEquals(expected, ArrayProblems.sortArray(nums));
	}

	/*
	sortArray CASE 2:
	Input: nums = [5,1,1,2,0,0]
	Output: [0,0,1,1,2,5]
	 */
	@Test
	void testSortArray2() {
        int[] nums = {5, 1, 1, 2, 0, 0};
        int[] expected = {0, 0, 1, 1, 2, 5};
        assertArrayEquals(expected, ArrayProblems.sortArray(nums));
    }

	/*
	findKthLargest test case1: 
	Input: [3,2,1,5,6,4] and k = 2
	Output: 5
	 */
	@Test
	void testFindKthLargest1() {
		int[] nums = {3,2,1,5,6,4};
		int k = 2;
		int expected = 5;
		assertEquals(expected, ArrayProblems.findKthLargest(nums, k));
		
	}


	/*
	findKthLargest test case1: 
	Input: [3,2,3,1,2,4,5,5,6] and k = 4
	Output: 4
	 */
	@Test
	void testFindKthLargest2() {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int expected = 4;
        assertEquals(expected, ArrayProblems.findKthLargest(nums, k));
	}

	/*
	findKthLargest test case1:
	Input: [3,2,1,5,6,4] and k = 7
	Output: throws IndexOutOfBoundsException
	 */
	@Test
    void testFindKthLargestTooLargeK() {
        int[] nums = {3,2,1,5,6,4};
        int k = 7;
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayProblems.findKthLargest(nums, k));
    }
}
