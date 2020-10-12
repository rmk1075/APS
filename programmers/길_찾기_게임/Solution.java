import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Node {
    int x, y, idx;
    Node left, right;

    Node(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}

class Solution {
    List<Integer> list = new ArrayList<>();
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};

        int N = nodeinfo.length;
        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i++) nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);

        Arrays.sort(nodes, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.y == o2.y) return o1.x - o2.x;
                return o2.y - o1.y;
            }
        });

        Node root = nodes[0];
        for(int i = 1; i < N; i++) createTree(root, nodes[i]);
        
        answer = new int[2][N];

        int idx = 0;
        preorder(root);
        for(Integer num : list) answer[0][idx++] = num;
        
        idx = 0;
        list.clear();
        postorder(root);
        for(Integer num : list) answer[1][idx++] = num;

        return answer;
    }

    public void preorder(Node node) {
        list.add(node.idx);
        if(node.left != null) preorder(node.left);
        if(node.right != null) preorder(node.right);
    }

    public void postorder(Node node) {
        if(node.left != null) postorder(node.left);
        if(node.right != null) postorder(node.right);
        list.add(node.idx);
    }

    public void createTree(Node root, Node node) {
        if(node.x < root.x) {
            if(root.left == null) root.left = node;
            else createTree(root.left, node);
        } else {
            if(root.right == null) root.right = node;
            else createTree(root.right, node);
        }
    }
}