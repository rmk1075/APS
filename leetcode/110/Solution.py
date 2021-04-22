# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        return self.checkBalanced(root)[0]
    
    def checkBalanced(self, root: TreeNode) -> (bool, int):
        if not root:
            return True, 0
        
        l, r = self.checkBalanced(root.left), self.checkBalanced(root.right)

        if not (l[0] and r[0]):
            return False, -1
        
        return abs(l[1] - r[1]) < 2, max(l[1], r[1]) + 1
