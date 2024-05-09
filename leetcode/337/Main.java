public class Main {

}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
  public int rob(TreeNode root) {
    int[] result = recursive(root);
    return Math.max(result[0], result[1]);
  }

  private int[] recursive(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }

    int[] leftResult = recursive(root.left);
    int[] rightResult = recursive(root.right);
    int includingRoot = root.val + leftResult[1] + rightResult[1];
    int excludingRoot = Math.max(leftResult[0], leftResult[1]) + Math.max(rightResult[0], rightResult[1]);
    return new int[]{includingRoot, excludingRoot};
  }
}