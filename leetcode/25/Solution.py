# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        result = ListNode(0, head)
        point = result
        current = result.next

        while current:
            tail = current
            count = 0

            while current and count < k:
                current = current.next
                count += 1

            if count == k:
                point.next = self.find(tail, k)
                point = tail
            else:
                point.next = tail
        
        return result.next

    def find(self, tail: ListNode, k: int) -> ListNode:
        pastNode = None
        while tail and k > 0:
            nextNode = tail.next
            tail.next = pastNode
            pastNode = tail
            tail = nextNode
            k -= 1
        
        return pastNode