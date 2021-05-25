# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        if not head:
            return False

        node1, node2 = head, head
        while node2 and node2.next:
            node1, node2 = node1.next, node2.next.next
            if node1 == node2:
                return True
        return False

# class Solution:
#     def hasCycle(self, head: ListNode) -> bool:
#         node_list = []
#         node = head
#         while node:
#             node_list.append(node)
#             node = node.next
#             if node in node_list:
#                 return True
        
#         return False