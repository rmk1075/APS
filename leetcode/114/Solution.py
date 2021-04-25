# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def flatten(self, root: TreeNode) -> None:
        self.dfs(root)

    def dfs(self, node: TreeNode) -> TreeNode:
        if not node:
            return

        subTree = node.right
        if node.left:
            node.right, node.left = node.left, None
            node = self.dfs(node.right)

        if subTree:
            node.right, node.left = subTree, None
            node = self.dfs(node.right)
        
        return node

# # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
# class Solution:
#     def flatten(self, root: TreeNode) -> None:
#         nodeList = []
#         self.dfs(nodeList, root)
        
#         current = root
#         for node in nodeList:
#             current.left, current.right, current = None, node, node
    
#     def dfs(self, nodeList: List[TreeNode], node: TreeNode) -> None:
#         if not node:
#             return

#         if node.left:
#             nodeList.append(node.left)
#             self.dfs(nodeList, node.left)
        
#         if node.right:
#             nodeList.append(node.right)
#             self.dfs(nodeList, node.right)