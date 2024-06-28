package programmers.부대복귀;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    
}

class Solution {
    private List<Integer>[] nodes;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        init(n, roads);
        int[] distances = find(n, destination);
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distances[sources[i]];
        }
        return answer;
    }

    private void init(int n, int[][] roads) {
        nodes = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            nodes[a].add(b);
            nodes[b].add(a);
        }
    }

    private int[] find(int n, int src) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        distances[src] = 0;

        boolean[] visited = new boolean[n + 1];
        visited[src] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (0 < size--) {
                int current = queue.poll();
                for (int next : nodes[current]) {
                    if (visited[next]) {
                        continue;
                    }
                    distances[next] = distance;
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            distance++;
        }
        return distances;
    }
}