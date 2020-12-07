import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = false;

        boolean[][] tree = new boolean[n][n];
        for (int[] p : path) {
            int a = p[0], b = p[1];
            tree[a][b] = tree[b][a] = true;
        }

        int[] ord = new int[n];
        for (int[] o : order) {
            ord[o[1]] = o[0];
        }

        answer = find(n, tree, ord);
        return answer;
    }

    public boolean find(int n, boolean[][] tree, int[] ord) {
        boolean[] visited = new boolean[n];
        visited[0] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int count = 1, past = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (0 < size--) {
                int current = queue.poll();
                if (!visited[ord[current]]) {
                    queue.offer(current);
                    continue;
                }

                if (!visited[current]) {
                    visited[current] = true;
                    count++;
                }

                for (int i = 1; i < n; i++) {
                    if (visited[i])
                        continue;
                    if (tree[current][i]) {
                        if (visited[ord[i]]) {
                            visited[i] = true;
                            count++;
                        }

                        queue.offer(i);
                    }
                }
            }

            if (count == past)
                break;

            past = count;

        }

        return count == n;
    }
}