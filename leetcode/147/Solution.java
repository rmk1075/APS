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
    public ListNode insertionSortList(ListNode head) {
        ListNode current = head;
        while(current != null && current.next != null) {
            ListNode next = current.next;
            if(current.val <= next.val) {
                current = next;
                continue;
            }

            ListNode prev = null, point = head;
            while(point.val < next.val) {
                prev = point;
                point = point.next;
            }

            current.next = next.next;
            next.next = point;
            if(point.equals(head)) {
                head = next;
            } else {
                prev.next = next;
            }
        }
        return head;
    }
}