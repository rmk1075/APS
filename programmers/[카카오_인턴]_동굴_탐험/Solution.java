import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = false;

        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        for (int[] p : path) {
            int a = p[0], b = p[1];
            tree[a].add(b);
            tree[b].add(a);
        }

        int[] ord = new int[n];
        for (int[] o : order) {
            ord[o[1]] = o[0];
        }

        if (ord[0] != 0)
            return false;

        answer = find(n, tree, ord);
        return answer;
    }

    public boolean find(int n, List<Integer>[] tree, int[] ord) {
        int[] next = new int[n];

        boolean[] visited = new boolean[n];
        visited[0] = true;

        Stack<Integer> stack = new Stack<>();
        for (int t : tree[0])
            stack.push(t);

        int count = 1;
        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited[current])
                continue;

            if (!visited[ord[current]]) {
                next[ord[current]] = current;
                continue;
            }

            visited[current] = true;
            count++;

            for (int t : tree[current]) {
                if (visited[t])
                    continue;
                stack.push(t);
            }

            stack.push(next[current]);
        }

        return count == n;
    }
}

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Queue;

// class Solution {
// public boolean solution(int n, int[][] path, int[][] order) {
// boolean answer = false;

// List<Integer>[] tree = new List[n];
// for (int i = 0; i < n; i++)
// tree[i] = new ArrayList<>();

// for (int[] p : path) {
// int a = p[0], b = p[1];
// tree[a].add(b);
// tree[b].add(a);
// }

// int[] ord = new int[n];
// for (int[] o : order) {
// ord[o[1]] = o[0];
// }

// answer = find(n, tree, ord);
// return answer;
// }

// public boolean find(int n, List<Integer>[] tree, int[] ord) {
// boolean[] visited = new boolean[n];
// visited[0] = true;

// Queue<Integer> queue = new LinkedList<>();
// queue.offer(0);

// int count = 1, past = 1;
// while (!queue.isEmpty()) {
// int size = queue.size();
// while (0 < size--) {
// int current = queue.poll();
// if (!visited[ord[current]]) {
// queue.offer(current);
// continue;
// }

// if (!visited[current]) {
// visited[current] = true;
// count++;
// }

// for (int i : tree[current]) {
// if (visited[i])
// continue;

// if (visited[ord[i]]) {
// visited[i] = true;
// count++;
// }

// queue.offer(i);
// }
// }

// if (count == past)
// break;

// past = count;

// }

// return count == n;
// }
// }