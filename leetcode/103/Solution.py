# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def zigzagLevelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []

        result, queue = [[root.val]], [root]
        toggle = False
        while queue:
            temp = []
            size = len(queue)
            while 0 < size:
                current = queue.pop(0)
                if current.left:
                    temp.append(current.left)
                if current.right:
                    temp.append(current.right)
                size -= 1
            queue += temp[:]
            if temp:
                temp = temp if toggle else list(reversed(temp))
                toggle = not toggle
                result.append([t.val for t in temp])
        return result