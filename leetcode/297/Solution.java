import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "X";

        StringBuffer sb = new StringBuffer();
        traversal(sb, root);
        return sb.toString();
    }

    private void traversal(StringBuffer sb, TreeNode node) {
        sb.append(node.val + ":");
        if(node.left == null) sb.append("X:");
        else traversal(sb, node.left);
        if(node.right == null) sb.append("X:");
        else traversal(sb, node.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(":")));
        TreeNode root = buildTree(queue);
        return root;
    }

    private TreeNode buildTree(Queue<String> queue) {
        String value = queue.poll();
        if(value.equals("X")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));