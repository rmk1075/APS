# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        val, limit = 2 * 10 ** 5 + 1, 10 ** 5
        current = head
        while current:
            if limit < current.val:
                current.val -= val
                return current
            current = current.next
        return None

# class Solution:
#     def detectCycle(self, head: ListNode) -> ListNode:
#         node_list = []
#         current = head
#         while current:
#             node_list.append(current)
#             current = current.next
#             if current in node_list:
#                 return current
#         return None