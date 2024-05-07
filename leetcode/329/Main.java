public class Main {
  public static void main(String[] args) {
    Solution solution = new Solution();

    String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
    boolean result = solution.isValidSerialization(preorder);
    System.out.println(result);
  }
}

class Solution {
  public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    return findBinaryTree(nodes, 0) == nodes.length;
  }

  private int findBinaryTree(String[] nodes, int index) {
    if (nodes[index].equals("#")) {
      return index + 1;
    }

    int next = index + 1;
    if (nodes.length <= next) {
      return -1;
    }
    next = findBinaryTree(nodes, next);

    if (next == -1 || nodes.length <= next) {
      return -1;
    }
    next = findBinaryTree(nodes, next);
    return next;
  }
}