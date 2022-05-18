import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int dist;

    public Edge(int u, int v, int dist) {
        this.u = u;
        this.v = v;
        this.dist = dist;
    }

    public int compareTo(Edge o) {
        return Integer.compare(this.dist, o.dist);
    }
}

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    int idx = 0;

    public int solution(int[][] land, int height) {
        int answer = 0;

        int N = land.length;
        PriorityQueue<Edge> edges = getEdges(land, height, N);

        int[] visited = new int[idx];
        for(int i = 0; i < idx; i++) visited[i] = i;
        while(!edges.isEmpty()) {
            Edge edge = edges.poll();
            int u = edge.u, v = edge.v;
            int pu = find(u, visited), pv = find(v, visited);
            if(pu == pv) continue;
            visited[pu] = pv;
            answer += edge.dist;
        }
        return answer;
    }

    public int find(int v, int[] visited) {
        if(visited[v] == v) return v;
        return find(visited[v], visited);
    }

    public PriorityQueue<Edge> getEdges(int[][] land, int height, int N) {
        List<Map<Integer, Integer>> list = new LinkedList<>();
        int[][] group = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(group[i], -1);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(group[i][j] != -1) continue;
                list.add(bfs(land, group, i, j, idx++, height, N));
            }
        }

        PriorityQueue<Edge> edges = new PriorityQueue();
        int i = 0;
        for(Map<Integer, Integer> map : list) {
            for(int key : map.keySet()) {
                edges.add(new Edge(i, key, map.get(key)));
            }
            i++;
        }
        return edges;
    }

    public Map<Integer, Integer> bfs(int[][] land, int[][] group, int i, int j, int idx, int height, int N) {
        Map<Integer, Integer> distances = new HashMap<>();
        group[i][j] = idx;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int d = 0; d < 4; d++) {
                int x = current[0] + dx[d];
                int y = current[1] + dy[d];
                if(x < 0 || y < 0 || N <= x || N <= y || group[x][y] == idx) continue;
                int key = group[x][y];
                if(key != -1) {
                    distances.put(key, distances.containsKey(key) ?
                     Math.min(distances.get(key), Math.abs(land[current[0]][current[1]] - land[x][y]))
                      : Math.abs(land[current[0]][current[1]] - land[x][y]));
                } else if(Math.abs(land[current[0]][current[1]] - land[x][y]) <= height) {
                    group[x][y] = idx;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return distances;
    }
}