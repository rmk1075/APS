# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reorderList(self, head: ListNode) -> None:
        stack = []
        current = head
        while current:
            stack.append(current)
            current = current.next
        
        left, right = head, None
        N = len(stack)
        for i in range(int(N / 2) if N % 2 == 0 else int(N / 2) + 1):
            right = stack.pop()
            next = left.next
            left.next = right
            left = next
            right.next = left
        right.next = None