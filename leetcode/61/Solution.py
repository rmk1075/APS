# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if not head:
            return head

        length, last = 1, head
        while last.next:
            last = last.next
            length += 1
        last.next = head

        k %= length
        current = last
        for i in range(length - k):
            current = current.next
            
        head, current.next = current.next, None
        return head