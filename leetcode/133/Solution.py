# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

class Solution:
    def cloneGraph(self, node: 'Node') -> 'Node':
        if not node:
            return None
        head = Node(node.val, [])
        if node.neighbors:
            self.dfs(head, node, {head.val: head})
        return head

    def dfs(self, cp: 'Node', og: 'Node', visited: Dict):
        for neighbor in og.neighbors:
            if neighbor.val not in visited.keys():
                node = Node(neighbor.val)
                visited[node.val] = node
                if neighbor.neighbors:
                    self.dfs(node, neighbor, visited)
            cp.neighbors.append(visited[neighbor.val])