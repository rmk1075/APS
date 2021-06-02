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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode();
        ListNode current = head;
        while(left != null && right != null) {
            if(left.val < right.val) {
                current.next = left;
                current = current.next;
                left = left.next;
            } else {
                current.next = right;
                current = current.next;
                right = right.next;
            }
        }
        current.next = (left == null) ? right : left;
        return head.next;
    }

    public ListNode getMid(ListNode head) {
        ListNode node = head;
        int N = 0;
        while(node != null) {
            node = node.next;
            N++;
        }

        ListNode prev = null;
        node = head;
        N = N / 2;
        while(0 < N--) {
            prev = node;
            node = node.next;
        }

        if(prev != null)
            prev.next = null;
        return node;
    }
}