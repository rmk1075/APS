# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if (p == None) ^ (q == None):
            return False

        p_cur, q_cur = p, q
        return self.dfs(p_cur, q_cur)
    def dfs(self, p: TreeNode, q:TreeNode):
        if p == None:
            return True
        if (p.val != q.val) or ((p.left == None) ^ (q.left == None)) or ((p.right == None) ^ (q.right == None)):
            return False
        return self.dfs(p.left, q.left) and self.dfs(p.right, q.right)
