
public class LinkedListProblems {
	/*
	Given a sorted linked list, delete all duplicates such that each element appear only once.

	Example 1:
	Input: 1->1->2
	Output: 1->2

	Example 2:
	Input: 1->1->2->3->3->3
	Output: 1->2->3
    */

    /**
     * Deletes all duplicates from a sorted list
     * @param head The list
     * @return A list without duplicates
     */
    //@TimeComplexity("O(n)")
	public static ListNode deleteDuplicates(ListNode head) {
        /*  TCJ
            Traversing the list only happens once throughout
            the execution of the method. If a duplicate is found,
            the element is then skipped until a unique element is found.
            In all cases best, average, and worst, the time complexity
            should be O(n)
         */
        ListNode current = head;

        while (current != null) {
            ListNode temp = current;
            while(temp!=null && temp.val==current.val) {
                temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }
		return head;
	}


	/*
	 * Reverse a singly linked list.
		Example:
		Input: 1->2->3->4->5->NULL
		Output: 5->4->3->2->1->NULL
	 */

    /**
     * Reverses a singly linked list.
     * @param head The list
     * @return A list without duplicates
     */
    //@TimeComplexity("O(n)")
	public static ListNode reverseList(ListNode head) {
        /*  TCJ
            The traversal of the list happens only once in a single
            while loop through all n elements of the list. Worst, average,
            and best case time complexity is O(n) in all cases.
         */
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
		return head;
	}
}
