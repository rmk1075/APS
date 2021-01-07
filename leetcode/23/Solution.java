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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;

        int N = lists.length;
        ListNode[] points = new ListNode[N];
        for (int i = 0; i < N; i++)
            points[i] = lists[i];

        int index, minVal;
        ListNode current = null;
        while (true) {
            index = -1;
            minVal = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (points[i] == null)
                    continue;

                if (points[i].val < minVal) {
                    index = i;
                    minVal = points[i].val;
                }
            }

            if (index == -1)
                break;

            if (current == null) {
                result = current = points[index];
            } else {
                current.next = points[index];
                current = current.next;
            }

            points[index] = points[index].next;
        }

        return result;
    }
}