/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode tail = new ListNode(0);
        ListNode head = tail;

        ListNode t1 = l1;
        ListNode t2 = l2;

        int carry = 0;

        while (t1 != null || t2 != null) {
            int x = (t1 != null) ? t1.val : 0;
            int y = (t2 != null) ? t2.val : 0;

            int sum = carry + x + y;

            ListNode result = new ListNode(sum % 10);
            tail.next = result;

            carry = sum / 10;

            if (t1 != null)
                t1 = t1.next;
            if (t2 != null)
                t2 = t2.next;

            tail = tail.next;
        }

        if (0 < carry) {
            tail.next = new ListNode(carry);
        }

        head = head.next;

        return head;
    }
}