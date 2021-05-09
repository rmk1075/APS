# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def sumNumbers(self, root: TreeNode) -> int:
        return self.dfs(root, root.val)
    
    def dfs(self, root: TreeNode, val: int) -> int:
        if not root.left and not root.right:
            return val
        result = 0
        if root.left:
            result += self.dfs(root.left, val * 10 + root.left.val)
        if root.right:
            result += self.dfs(root.right, val * 10 + root.right.val)
        return result