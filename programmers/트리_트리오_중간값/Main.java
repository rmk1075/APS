package programmers.트리_트리오_중간값;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 4;
        int[][] edges = {{1,2},{2,3},{3,4}};
        int result = sol.solution(n, edges);
        System.out.println(result);

        n = 5;
        edges = new int[][]{{1,5},{2,5},{3,5},{4,5}};
        result = sol.solution(n, edges);
        System.out.println(result);
    }
}

class Solution {
    List<Integer>[] tree;
    public int solution(int n, int[][] edges) {
        init(n, edges);
        int[] distances = findDistancesFrom(n, 0);
        List<Integer> indices = findMaxIndicesFromDistances(distances);
        distances = findDistancesFrom(n, indices.get(0));
        indices = findMaxIndicesFromDistances(distances);
        if (1 < indices.size()) {
            return distances[indices.get(0)];
        }

        distances = findDistancesFrom(n, indices.get(0));
        indices = findMaxIndicesFromDistances(distances);
        if (1 < indices.size()) {
            return distances[indices.get(0)];
        }
        return distances[indices.get(0)] - 1;
    }

    private void init(int n, int[][] edges) {
        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            tree[a].add(b);
            tree[b].add(a);
        }
    }

    private int[] findDistancesFrom(int n, int start) {
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        distances[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int current = -1;
        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (0 < size--) {
                current = queue.poll();
                for (int next : tree[current]) {
                    if (distances[next] != -1) {
                        continue;
                    }
    
                    queue.offer(next);
                    distances[next] = distance;
                }
            }
            distance++;
        }
        return distances;
    }

    private List<Integer> findMaxIndicesFromDistances(int[] distances) {
        List<Integer> indices = new ArrayList<>();
        int distance = -1;
        for (int i = 0; i < distances.length; i++) {
            if (distance < distances[i]) {
                indices.clear();
                indices.add(i);
                distance = distances[i];
            } else if (distance == distances[i]) {
                indices.add(i);
            }
        }
        return indices;
    }
}
