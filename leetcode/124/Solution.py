# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def maxPathSum(self, root: TreeNode) -> int:
        result_max, result_sum = self.cal(root)
        return result_max
    
    def cal(self, node: TreeNode) -> (int, int):
        left_max, left_sum, right_max, right_sum = -1001, -1001, -1001, -1001

        if node.left:
            left_max, left_sum = self.cal(node.left)
        
        if node.right:
            right_max, right_sum = self.cal(node.right)

        if left_sum < 0 and right_sum < 0:
            current_sum = node.val
        else:
            current_sum = node.val + max(left_sum, right_sum)
        
        current_max = max(left_max, right_max, current_sum, node.val + left_sum + right_sum)

        return current_max, current_sum
        