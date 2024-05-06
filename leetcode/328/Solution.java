/**
 * Definition for singly-linked list.
 */
class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
  private ListNode lastOdd;
  private ListNode lastEven;
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode current = init(head);
    int index = 3;
    while (current != null) {
      ListNode next = current.next;
      if (index++ % 2 == 0) {
        putEven(current);
      } else {
        putOdd(current);
      }
      current = next;
    }
    return head;
  }

  private ListNode init(ListNode head) {
    lastOdd = head;
    lastEven = head.next;
    ListNode current = lastEven.next;
    lastEven.next = null;
    return current;
  }

  private void putOdd(ListNode current) {
    lastOdd = putNode(lastOdd, current);
  }

  private void putEven(ListNode current) {
    lastEven = putNode(lastEven, current);
  }

  private ListNode putNode(ListNode prev, ListNode current) {
    ListNode temp = prev.next;
    prev.next = current;
    current.next = temp;
    return current;
  }
}