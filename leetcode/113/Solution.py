# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def pathSum(self, root: TreeNode, targetSum: int) -> List[List[int]]:
        if not root:
            return []

        result = []
        queue = [(root, root.val, [root.val])]
        while queue:
            current, val, nodeList = queue.pop()
            if not (current.left or current.right):
                if val == targetSum:
                    result.append(nodeList)
                continue
            
            if current.left:
                l = current.left
                queue.append((l, val + l.val, nodeList + [l.val]))
            
            if current.right:
                r = current.right
                queue.append((r, val + r.val, nodeList + [r.val]))

        return result