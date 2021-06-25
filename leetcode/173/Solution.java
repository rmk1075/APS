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
class BSTIterator {
    private TreeNode pointer = new TreeNode();

    public BSTIterator(TreeNode root) {
        inOrder(pointer, root);
    }
    
    private TreeNode inOrder(TreeNode head, TreeNode node) {
        TreeNode temp = head;
        if(node.left != null) temp = inOrder(head, node.left);
        temp.right = node;
        temp = node;
        if(node.right != null) {
            temp = inOrder(node, node.right);
        }
        return temp;
    }

    public int next() {
        this.pointer = this.pointer.right;
        return this.pointer.val;
    }
    
    public boolean hasNext() {
        return this.pointer.right != null;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */