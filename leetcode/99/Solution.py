# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def recoverTree(self, root: TreeNode) -> None:
        nodes = [root]
        self.arrange(root, 0, nodes)

        nodeList = []
        for i, node in enumerate(nodes):
            nodeList.append([i, node.val])
        nodeList.sort(key=lambda n: n[1])
        
        candidates = []
        for i, node in enumerate(nodeList):
            if i != node[0]:
                candidates.append(node[0])
        
        nodes[candidates[0]].val, nodes[candidates[1]].val = nodes[candidates[1]].val, nodes[candidates[0]].val

    def arrange(self, node: TreeNode, index: int, nodes: List[TreeNode]) -> None:
        if node.right:
            nodes.insert(index + 1, node.right)
            self.arrange(node.right, index + 1, nodes)
        
        if node.left:
            nodes.insert(index, node.left)
            self.arrange(node.left, index, nodes)