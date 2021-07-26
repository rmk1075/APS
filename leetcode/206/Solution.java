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
    public ListNode reverseList(ListNode head) {
        ListNode node = new ListNode();
        while(head != null) {
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }
        return node.next;
    }
}