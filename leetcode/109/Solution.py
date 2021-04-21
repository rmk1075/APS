# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        nodes = []
        current = head
        while current:
            nodes.append(current.val)
            current = current.next

        print(nodes)

        if len(nodes) == 0:
            return None

        left, right = 0, len(nodes)
        mid = (left + right) // 2
        root = TreeNode(nodes[mid])
        self.dfs(root, nodes)

        return root
    
    def dfs(self, parent: TreeNode, nodes: List[int]) -> None:
        if len(nodes) == 1:
            return

        mid = nodes.index(parent.val)
        
        l = mid // 2
        if l != mid:
            parent.left = TreeNode(nodes[l])
            self.dfs(parent.left, nodes[:mid])

        r = (len(nodes) + mid) // 2
        if r != mid:
            parent.right = TreeNode(nodes[r])
            self.dfs(parent.right, nodes[mid + 1:])
