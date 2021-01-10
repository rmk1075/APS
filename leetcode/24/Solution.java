public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode current = head;
        head = head.next;
        current.next = head.next;
        head.next = current;

        ListNode pair1, pair2;
        while (current.next != null && current.next.next != null) {
            pair1 = current.next;
            pair2 = current.next.next;

            current.next = pair2;
            pair1.next = pair2.next;
            pair2.next = pair1;

            current = pair1;
        }

        return head;
    }
}