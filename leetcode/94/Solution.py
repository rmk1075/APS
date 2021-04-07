# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        result = self.divideAndConquer(root)
        return result

    def divideAndConquer(self, root: TreeNode) -> List[int]:
        result = []
        if root:
            result = self.divideAndConquer(root.left) + [root.val] + self.divideAndConquer(root.right)
        return result