# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        node_list = []
        node = head
        while node:
            node_list.append(node)
            node = node.next
            if node in node_list:
                return True
        
        return False