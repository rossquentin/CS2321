import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class LinkedListProblemsTest {

	ListNode T;
	
	/* The common test data to be used is the list T: 1->1->2->3->3
	*/
	@Before
	public void setUp() throws Exception {

		int[] testData= {1,1,2,3,3};
		T = new ListNode(testData[0]);
		ListNode curr = T;
		for (int i=1; i<testData.length;i++) {
			curr.next = new ListNode(testData[i]);
			curr = curr.next;
		}
	}

	/*
	Given a sorted linked list, delete all duplicates such that each element appear only once.

	Example 1:
	Input: 1->1->2->3->3->null
	Output: 1->2->3->null
	*/
    
	@Test
	public void testDeleteDuplicates() {
		int[] expected = {1,2,3};
		int n = expected.length;

        ListNode p= LinkedListProblems.deleteDuplicates(T);
		int i = 0;
		while (p!=null) {
            if (i >= n) {
                fail("Will throw IndexOutOfBoundsException.");
            }
			assertEquals(expected[i], p.val);
			i = i+1;
			p = p.next;
		}
	}

	/*
	 * Reverse a singly linked list.
		Example:
		Input: 1->1->2->3->3->NULL
		Output: 3->3->1->1->1->NULL
	 */
	@Test
	public void testReverseList() {
	    int[] expected = {3,3,2,1,1};
	    int n = expected.length;
        ListNode q = LinkedListProblems.reverseList(T);
	    int i = 0;
	    while (q != null) {
            if (i >= n) {
                fail("Will throw IndexOutOfBoundsException.");
            }
	        assertEquals(expected[i], q.val);
	        i++;
	        q = q.next;
        }
	}
}
