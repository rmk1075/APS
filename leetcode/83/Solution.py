# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution(object):
    def deleteDuplicates(self, head):
        past, current = head, head
        while current and current.next:
            while current.next and current.val == current.next.val:
                current = current.next
            past.next = current.next
            past = past.next
            current = current.next
        return head