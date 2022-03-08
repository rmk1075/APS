import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    long answer = 0L;
    boolean[] visited;
    public long solution(int[] a, int[][] edges) {
        int N = a.length;
        long sum = 0L;
        long[] weights = new long[N];
        int[] degrees = new int[N];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
            sum += (long)a[i];
            weights[i] = (long)a[i];
        }
        if(sum != 0L) return -1L;

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            degrees[u]++;
            degrees[v]++;
        }

        visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if(degrees[i] == 1) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;

            ArrayList<Integer> nodes = graph.get(current);
            for(int i = 0; i < nodes.size(); i++) {
                int next = nodes.get(i);
                if(visited[next]) continue;

                degrees[next]--;
                weights[next] += weights[current];
                answer += Math.abs(weights[current]);
                weights[current] = 0;
                if(degrees[next] == 1) queue.offer(next);
            }
        }
        return answer;
    }
}
