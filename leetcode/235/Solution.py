# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        right = max(p.val, q.val)
        left = min(p.val, q.val)
        current = root
        while current:
            if current.val in (left, right) or (left < current.val and current.val < right):
                return current
            if left < current.val:
                current = current.left
            else:
                current = current.right