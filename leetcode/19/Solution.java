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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode startNode = new ListNode(0, head);
        ListNode a = startNode, b = startNode;

        int step = 0;
        while (a.next != null) {
            step++;
            a = a.next;
            if (n < step)
                b = b.next;
        }

        if (b == startNode)
            head = head.next;
        else
            b.next = b.next.next;

        return head;
    }
}