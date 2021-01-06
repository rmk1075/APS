/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result, current;
        if (l1 != null && l2 == null)
            return l1;
        else if (l1 == null && l2 != null)
            return l2;
        else if (l1 == null & l2 == null)
            return null;

        if (l2.val < l1.val) {
            result = current = l2;
            l2 = l2.next;
        } else {
            result = current = l1;
            l1 = l1.next;
        }

        while (l1 != null && l2 != null) {
            if (l2.val < l1.val) {
                current.next = l2;
                l2 = l2.next;
            } else {
                current.next = l1;
                l1 = l1.next;
            }
            current = current.next;
        }

        if (l1 != null)
            current.next = l1;
        else if (l2 != null)
            current.next = l2;

        return result;
    }
}