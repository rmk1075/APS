/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        
        ListNode current = dummy;
        while(current != null && current.next != null) {
            int nextVal = current.next.val;
            if(nextVal == val) current.next = current.next.next;
            else current = current.next;
        }

        return dummy.next;
    }
}