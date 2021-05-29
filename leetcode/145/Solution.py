# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:
        result = []
        if root:
            self.dfs(root, result)
        return result
    
    def dfs(self, root: TreeNode, result: List[int]):
        if root.left:
            self.dfs(root.left, result)
        if root.right:
            self.dfs(root.right, result)
        result.append(root.val)