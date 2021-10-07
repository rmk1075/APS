import java.util.LinkedList;
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
    StringBuffer sb = new StringBuffer();
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        sb.append(root.val);
        dfs(root, result);
        return result;
    }

    public void dfs(TreeNode node, List<String> result) {
        if(node.left == null && node.right == null) {
            result.add(sb.toString());
            return ;
        }

        if(node.left != null) {
            String temp = "->" + node.left.val;
            sb.append(temp);
            dfs(node.left, result);
            sb.delete(sb.length() - temp.length(), sb.length());
        }

        if(node.right != null) {
            String temp = "->" + node.right.val;
            sb.append(temp);
            dfs(node.right, result);
            sb.delete(sb.length() - temp.length(), sb.length());
        }
    }
}