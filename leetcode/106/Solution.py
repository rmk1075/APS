# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        N = len(postorder)
        root, index = TreeNode(postorder[N - 1]), N - 1
        val = postorder[index]
        node = TreeNode(val)
        idx = inorder.index(val)

        if -1 < index and idx != len(inorder) - 1:
            index -= 1
            root.right = TreeNode(postorder[index])
            index = self.dfs(root.right, N, index, postorder, inorder[idx + 1:])

        if idx != 0:
            index -= 1
            root.left = TreeNode(postorder[index])
            index = self.dfs(root.left, N, index, postorder, inorder[:idx])
            
        return root
    
    def dfs(self, parent: TreeNode, N: int, index: int, postorder: List[int], inorder: List[int]) -> int:
        if index < 0 or len(inorder) == 1:
            return index

        val = postorder[index]
        node = TreeNode(val)
        idx = inorder.index(val)

        if idx != len(inorder) - 1:
            index -= 1
            parent.right = TreeNode(postorder[index])
            index = self.dfs(parent.right, N, index, postorder, inorder[idx + 1:])

        if index < 0:
            return index

        if idx != 0:
            index -= 1
            parent.left = TreeNode(postorder[index])
            index = self.dfs(parent.left, N, index, postorder, inorder[:idx])
        
        return index
    