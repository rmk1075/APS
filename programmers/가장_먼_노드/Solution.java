import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        List<Integer>[] graph = new List[n + 1];
        for(int i = 1; i < n + 1; i++) graph[i] = new ArrayList<>();
        for(int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] distances = new int[n + 1];
        for(int i = 2; i < n + 1; i++) distances[i] = Integer.MAX_VALUE;

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(visited[cur]) continue;
            visited[cur] = true;

            for(int next : graph[cur]) {
                if(visited[next] || distances[next] < distances[cur] + 1) continue;
                distances[next] = distances[cur] + 1;
                queue.offer(next);
            }
        }

        Arrays.sort(distances);
        int maxNum = distances[n];
        int idx = n;
        while(1 < idx && distances[idx--] == maxNum) answer++;
        return answer;
    }
}

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.PriorityQueue;

// class Edge implements Comparable<Edge> {
//     int node;
//     int distance;

//     Edge(int node, int distance) {
//         this.node = node;
//         this.distance = distance;
//     }

//     @Override
//     public int compareTo(Edge o) {
//         return Integer.compare(this.distance, o.distance);
//     }
// }

// class Solution {
//     public int solution(int n, int[][] edge) {
//         int answer = 0;

//         List<Integer>[] graph = new List[n + 1];
//         for(int i = 1; i < n + 1; i++) graph[i] = new ArrayList<>();
//         for(int[] e : edge) {
//             graph[e[0]].add(e[1]);
//             graph[e[1]].add(e[0]);
//         }

//         PriorityQueue<Edge> pq = new PriorityQueue<>();
//         boolean[] visited = new boolean[n + 1];
//         int[] distances = new int[n + 1];
//         for(int i = 2; i < n + 1; i++) {
//             distances[i] = Integer.MAX_VALUE;
//             pq.offer(new Edge(i, Integer.MAX_VALUE));
//         }
//         pq.offer(new Edge(1, 0));

//         while(!pq.isEmpty()) {
//             Edge e = pq.poll();
//             if(visited[e.node]) continue;
//             visited[e.node] = true;

//             for(int next : graph[e.node]) {
//                 if(visited[next] || distances[next] < distances[e.node] + 1) continue;
//                 distances[next] = distances[e.node] + 1;
//                 pq.offer(new Edge(next, distances[next]));
//             }
//         }

//         Arrays.sort(distances);
//         int maxNum = distances[n];
//         int idx = n;
//         while(1 < idx && distances[idx--] == maxNum) answer++;
//         return answer;
//     }
// }