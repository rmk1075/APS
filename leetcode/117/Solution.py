# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next

class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return None

        queue = [root]
        while queue:
            prev_node, next_node = queue.pop(0), None
            size = len(queue)
            while 0 < size:
                if prev_node.left:
                    queue.append(prev_node.left)
                if prev_node.right:
                    queue.append(prev_node.right)

                next_node = queue.pop(0)
                prev_node.next = next_node
                prev_node = next_node
                size -= 1
            if prev_node.left:
                queue.append(prev_node.left)
            if prev_node.right:
                queue.append(prev_node.right)
            
        return root