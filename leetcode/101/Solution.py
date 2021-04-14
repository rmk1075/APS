# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        return self.cmpNodes(root.left, root.right)
    
    def cmpNodes(self, left: TreeNode, right: TreeNode) -> bool:
        l, r = (left == None), (right == None)
        if l and r:
            return True
        
        if l ^ r or left.val != right.val:
            return False
        
        return self.cmpNodes(left.left, right.right) and self.cmpNodes(left.right, right.left)

        

        