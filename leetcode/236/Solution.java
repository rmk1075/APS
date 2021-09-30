import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = root;

        Deque<TreeNode> pq = new LinkedList<>();
        findNode(pq, root, p);

        Deque<TreeNode> qq = new LinkedList<>();
        findNode(qq, root, q);

        while(!pq.isEmpty() && !qq.isEmpty()) {
            TreeNode pNode = pq.poll();
            TreeNode qNode = qq.poll();
            if(pNode.val != qNode.val) break;
            result = pNode;
        }
        return result;
    }

    public boolean findNode(Deque<TreeNode> queue, TreeNode current, TreeNode target) {
        queue.addLast(current);
        if(current.val == target.val) return true;
        if(current.left != null) {
            if(findNode(queue, current.left, target)) return true;
        }
        if(current.right != null) {
            if(findNode(queue, current.right, target)) return true;
        }
        queue.removeLast();
        return false;
    }
}