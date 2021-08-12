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
    public int countNodes(TreeNode root) {
        int level = getLevel(root);
        if(level < 0) return 0;
        return level - 1 == getLevel(root.right) ? (1 << level) + countNodes(root.right) : (1 << (level-1)) + countNodes(root.left);
    }

    public int getLevel(TreeNode root) {
        return root == null ? -1 : getLevel(root.left) + 1;
    }
}

// class Solution {
//     public int countNodes(TreeNode root) {
//         if(root == null) return 0;
//         return countNodes(root.left) + countNodes(root.right) + 1;
//     }
// }