/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int index = 0;
    public int kthSmallest(TreeNode root, int k) {
        int result = inOrder(root, k);
        return result;
    }

    public int inOrder(TreeNode root, int k) {
        int result = -1;
        if(root.left != null) {
            result = inOrder(root.left, k);
            if(result != -1) return result;
        }

        index++;
        if(index == k) return root.val;
        
        if(root.right != null) {
            result = inOrder(root.right, k);
            if(result != -1) return result;
        }
        return -1;
    }
}