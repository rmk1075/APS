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
        int N = lists.length;
        if (N == 0)
            return null;

        ListNode result = null, current;

        int index;
        for (index = 0; index < N; index++) {
            if (lists[index] != null) {
                result = lists[index];
                break;
            }
        }

        for (int i = index + 1; i < N; i++) {
            current = result;

            if (lists[i] == null)
                continue;

            if (lists[i].val < current.val) {
                result = lists[i];
                lists[i] = lists[i].next;
                result.next = current;
                current = result;
            }

            while (lists[i] != null) {
                int val = lists[i].val;

                ListNode past = null;
                while (current != null && current.val <= val) {
                    past = current;
                    current = current.next;
                }

                past.next = lists[i];
                lists[i] = lists[i].next;
                past.next.next = current;
                current = past.next;
            }
        }

        return result;
    }
}

///////////////////////////
// class Solution {
// public ListNode mergeKLists(ListNode[] lists) {
// int N = lists.length;
// if (N == 0)
// return null;

// ListNode[] points = new ListNode[N];
// for (int i = 0; i < N; i++)
// points[i] = lists[i];

// ListNode l1 = points[0], l2 = null;
// for (int i = 1; i < N; i++) {
// l2 = points[i];
// if (l2 == null)
// continue;

// l1 = mergeTwoLists(l1, l2);
// }

// return l1;
// }

// public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
// ListNode result, current;
// if (l1 != null && l2 == null)
// return l1;
// else if (l1 == null && l2 != null)
// return l2;
// else if (l1 == null & l2 == null)
// return null;

// if (l2.val < l1.val) {
// result = current = l2;
// l2 = l2.next;
// } else {
// result = current = l1;
// l1 = l1.next;
// }

// while (l1 != null && l2 != null) {
// if (l2.val < l1.val) {
// current.next = l2;
// l2 = l2.next;
// } else {
// current.next = l1;
// l1 = l1.next;
// }
// current = current.next;
// }

// if (l1 != null)
// current.next = l1;
// else if (l2 != null)
// current.next = l2;

// return result;
// }
// }

////////////////////////////////////////
// class Solution {
// public ListNode mergeKLists(ListNode[] lists) {
// ListNode result = null;

// int N = lists.length;
// ListNode[] points = new ListNode[N];
// for (int i = 0; i < N; i++)
// points[i] = lists[i];

// int index, minVal;
// ListNode current = null;
// while (true) {
// index = -1;
// minVal = Integer.MAX_VALUE;
// for (int i = 0; i < N; i++) {
// if (points[i] == null)
// continue;

// if (points[i].val < minVal) {
// index = i;
// minVal = points[i].val;
// }
// }

// if (index == -1)
// break;

// if (current == null) {
// result = current = points[index];
// } else {
// current.next = points[index];
// current = current.next;
// }

// points[index] = points[index].next;
// }

// return result;
// }
// }