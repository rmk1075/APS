# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def levelOrderBottom(self, root: TreeNode) -> List[List[int]]:
        result = []
        if not root:
            return result

        queue = [root]
        while queue:
            temp = []
            size = len(queue)
            while 0 < size:
                current = queue.pop(0)
                if current.left:
                    queue.append(current.left)
                if current.right:
                    queue.append(current.right)
                temp.append(current.val)
                size -= 1
            result.insert(0, temp[:])
        return result