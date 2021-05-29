import java.util.ArrayList;
import java.util.List;

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root != null) result = dfs(root, result);
        return result;
    }

    public List<Integer> dfs(TreeNode root, List<Integer> result) {
        if(root.left != null) result = dfs(root.left, result);
        if(root.right != null) result = dfs(root.right, result);
        result.add(root.val);
        return result;
    }
}