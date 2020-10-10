import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int dest, w;

    Node(int dest, int w) {
        this.dest = dest;
        this.w = w;
    }
}

public class Main {
    static int N;
    static boolean[] visited;
    static ArrayList<Node>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        edges = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            edges[u].add(new Node(v, w));
            edges[v].add(new Node(u, w));
        }

        int maxIdx = 0, maxVal = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 0));
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if(maxVal < current.w) {
                maxIdx = current.dest;
                maxVal = current.w;
            }

            for(Node dest : edges[current.dest]) {
                if(visited[dest.dest]) continue;
                visited[dest.dest] = true;
                queue.offer(new Node(dest.dest, current.w + dest.w));
            }
        }

        Arrays.fill(visited, false);
        queue.offer(new Node(maxIdx, 0));
        visited[maxIdx] = true;
        maxVal = 0;
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            maxVal = Math.max(maxVal, current.w);

            for(Node dest : edges[current.dest]) {
                if(visited[dest.dest]) continue;
                visited[dest.dest] = true;
                queue.offer(new Node(dest.dest, current.w + dest.w));
            }
        }

        System.out.println(maxVal);
    }
}