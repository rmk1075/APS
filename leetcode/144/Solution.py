# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        result = []
        if root:
            self.dfs(result, root)
        return result
    def dfs(self, result: List[int], node: TreeNode):
        result.append(node.val)
        if node.left:
            self.dfs(result, node.left)
        if node.right:
            self.dfs(result, node.right)