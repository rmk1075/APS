# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        N = len(nums)
        mid = N // 2
        root = TreeNode(nums[mid])
        queue = [(root, mid, 0, N)]
        while queue:
            node, mid, left, right = queue.pop(0)

            # left
            idx = (left + mid) // 2
            if mid != left and left <= idx:
                node.left = TreeNode(nums[idx])
                queue.append((node.left, idx, left, mid))

            # right
            idx = (right + mid) // 2
            if mid != right - 1 and idx < right:
                node.right = TreeNode(nums[idx])
                queue.append((node.right, idx, mid + 1, right))

        return root
