import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * overview
 * 
 * 트리의 모든 노드의 값이 0 이 되려면, 모든 노드의 값의 합이 0 이 되어야 한다.
 * 만약 값의 합이 0 이 아닌 경우, -1 을 반환한다.
 * 
 * 트리의 값들을 0 으로 만드는 연산은 트리 구조에서 각 노드의 가중치를 부모 노드로 넘기는 방식으로 작업을 수행한다.
 * 트리의 가장 바깥 노드, leaf node 에서부터 작업을 수행하도록 한다.
 * 
 * description
 * 
 * 우선은 매개 변수를 읽어서 트리 구조를 만든다.
 * 트리 구조를 만든 후 연산은 bfs 방식으로 수행한다.
 * 이때 큐에 노드를 넣는 노드는 leaf node 가 되어야 하는데, 이는 노드에 연결된 간선이 하나인 경우에 해당한다.
 * 최초의 leaf node 들을 큐에 넣고 bfs 연산을 수행한다.
 * 매 연산에서 수행할 때에 current node 의 가중치를 parent node 에게 넘기고 parent node 의 차수를 -1 한다.
 * 이때 parent node 의 차수가 1이 되는 경우 모든 child node 를 방문하고, leaf node 가 된 것이기 때문에 parent node 를 큐에 넣는다.
 * 이를 반복하여 모든 노드를 방문하여 연산을 수행한다.
 */
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
