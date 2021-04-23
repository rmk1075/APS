# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def minDepth(self, root: TreeNode) -> int:
        if not root:
            return 0

        queue = [root]
        depth = 1
        while queue:
            size = len(queue)
            while 0 < size:
                current = queue.pop(0)
                if not current:
                    size -= 1
                    continue

                if not (current.left or current.right):
                    return depth

                queue.append(current.left)
                queue.append(current.right)
                size -= 1
            depth += 1

        return depth
