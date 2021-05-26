# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        node_list = []
        current = head
        while current:
            node_list.append(current)
            current = current.next
            if current in node_list:
                return current
        return None