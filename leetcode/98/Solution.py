# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        minimum, maximum = -2147483649, 2147483648
        if (root.left and root.val <= root.left.val) or (root.right and root.right.val <= root.val):
            return False
        return (self.validBST(minimum, root.val, root.left) if root.left else True) and (self.validBST(root.val, maximum, root.right) if root.right else True)
    
    def validBST(self, minimum: int, maximum: int, node: TreeNode) -> bool:
        if node == None:
            return True
        if (node.left and (node.val <= node.left.val or node.left.val <= minimum)) or (node.right and (maximum <= node.right.val or node.right.val <= node.val)):
            return False
        
        return (self.validBST(minimum, node.val, node.left) if node.left else True) and (self.validBST(node.val, maximum, node.right) if node.right else True)