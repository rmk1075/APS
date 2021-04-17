# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        root = TreeNode(preorder[0])
        
        index, N = 0, len(preorder)
        val = preorder[index]
        node = TreeNode(val)
        idx = inorder.index(val)

        if idx != 0:
            index += 1
            root.left = TreeNode(preorder[index])
            index = self.dfs(root.left, N, index, preorder, inorder[:idx])

        if index < N and idx != len(inorder) - 1:
            index += 1
            root.right = TreeNode(preorder[index])
            index = self.dfs(root.right, N, index, preorder, inorder[idx + 1:])
            
        return root
    
    def dfs(self, parent: TreeNode, N: int, index: int, preorder: List[int], inorder: List[int]) -> int:
        if N <= index or len(inorder) == 1:
            return index

        val = preorder[index]
        node = TreeNode(val)
        idx = inorder.index(val)

        if idx != 0:
            index += 1
            parent.left = TreeNode(preorder[index])
            index = self.dfs(parent.left, N, index, preorder, inorder[:idx])

        if N <= index:
            return index

        if idx != len(inorder) - 1:
            index += 1
            parent.right = TreeNode(preorder[index])
            index = self.dfs(parent.right, N, index, preorder, inorder[idx + 1:])
        
        return index
    