# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def generateTrees(self, n: int) -> List[TreeNode]:
        return self.generate(0, n)
    
    def generate(self, left: int, right: int) -> List[TreeNode]:
        if left == right:
            return [None]
        
        result = []
        for i in range(left, right):
            for l_node in self.generate(left, i):
                for r_node in self.generate(i + 1, right):
                    root = TreeNode(i + 1)
                    root.left = l_node
                    root.right = r_node
                    result.append(root)
        return result