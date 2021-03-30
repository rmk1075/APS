# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def partition(self, head, x):
        dummy = ListNode(-101, head)
        boundary = dummy
        while boundary.next and boundary.next.val < x:
            boundary = boundary.next

        current = boundary
        while current and current.next:
            if current.next.val < x:
                temp = current.next.next
                current.next.next = boundary.next
                boundary.next = current.next
                boundary = boundary.next
                current.next = temp
            else:
                current = current.next
        
            print(dummy.next)
        
        return dummy.next