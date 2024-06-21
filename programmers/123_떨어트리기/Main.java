import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// package programmers.123_떨어트리기;

// public class Main {
    
// }

class Node {
    private int index;
    private boolean isLeaf;
    private Queue<Node> children;

    public Node(int index) {
        this.index = index;
        this.isLeaf = false;
    }

    public int getIndex() {
        return this.index;
    }

    public void setChildren(Queue<Node> children) {
        this.children = children;
        if (children.isEmpty()) {
            this.isLeaf = true;
        }
    }

    public Node getChild() {
        return children.peek();
    }

    public void changeChild() {
        children.offer(children.poll());
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }
}

class Solution {
    private Node root;
    public int[] solution(int[][] edges, int[] target) {
        init(edges);
        return find(target);
    }

    private void init(int[][] edges) {
        boolean[][] tree = new boolean[101][101];
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            tree[parent][child] = true;
        }
        root = makeTree(tree, 1);
    }

    private Node makeTree(boolean[][] tree, int index) {
        Node node = new Node(index);
        LinkedList<Node> children = new LinkedList<>();
        for (int i = 1; i < tree[index].length; i++) {
            if (tree[index][i]) {
                Node child = makeTree(tree, i);
                children.add(child);
            }
        }
        node.setChildren(children);
        return node;
    }

    private int[] find(int[] target) {
        int len = target.length;
        int[] counts = new int[len];
        LinkedList<Integer> sequence = new LinkedList<>();
        boolean[] nodesStatus = initNodesStatus(counts, target);
        while (true) {
            int index = drop() - 1;
            counts[index]++;
            sequence.add(index);
            boolean isValid = isValidCount(counts, target, index);
            if (!isValid) {
                if (nodesStatus[index]) {
                    return new int[]{-1};
                } else {
                    nodesStatus[index] = false;
                }
            } else {
                nodesStatus[index] = true;
                if (isValidStatus(nodesStatus)) {
                    break;
                }
            }
        }
        return makeSequenceArray(target, counts, sequence);
    }

    private boolean[] initNodesStatus(int[] counts, int[] target) {
        boolean[] result = new boolean[target.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = isValidCount(counts, target, i);
        }
        return result;
    }

    private boolean isValidCount(int[] counts, int[] target, int index) {
        int c = counts[index];
        int t = target[index];
        return (c <= t) && (t <= 3 * c);
    }

    private boolean isValidStatus(boolean[] status) {
        for (boolean s : status) {
            if (!s) {
                return false;
            }
        }
        return true;
    }

    private int drop() {
        Node current = root;
        while (!current.isLeaf()) {
            Node next = current.getChild();
            current.changeChild();
            current = next;
        }
        return current.getIndex();
    }

    private int[] makeSequenceArray(int[] target, int[] counts, List<Integer> sequence) {
        List<Integer> result = new LinkedList<>();
        for (int index : sequence) {
            counts[index]--;
            if (counts[index] == 0) {
                result.add(target[index]);
            } else {
                int t = target[index];
                int c = counts[index];
                if (t <= 3 * c + 1) {
                    target[index]--;
                    result.add(1);
                } else {
                    int num = t - (3 * c);
                    target[index] -= num;
                    result.add(num);
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
