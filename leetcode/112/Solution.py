# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def hasPathSum(self, root: TreeNode, targetSum: int) -> bool:
        if not root:
            return False
        return self.dfs(root, targetSum, root.val)
    
    def dfs(self, node: TreeNode, targetSum: int, val: int) -> bool:
        if not (node.left or node.right):
            return targetSum == val
        
        left, right = False, False
        if node.left:
            left = self.dfs(node.left, targetSum, val + node.left.val)
        
        if node.right:
            right = self.dfs(node.right, targetSum, val + node.right.val)
        
        return left or right

