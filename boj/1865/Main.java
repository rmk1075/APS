import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (0 < T--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<int[]> edges = new LinkedList<>();
            while (0 < M--) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                edges.add(new int[] { u, v, w });
                edges.add(new int[] { v, u, w });
            }

            while (0 < W--) {
                st = new StringTokenizer(br.readLine());
                edges.add(new int[] {
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken()) - 1,
                        -Integer.parseInt(st.nextToken()) });
            }

            sb.append(check(N, edges) ? YES : NO).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean check(int N, List<int[]> edges) {
        int[] distances = new int[N];
        for (int i = 1; i < N; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (distances[v] <= distances[u] + w) {
                    continue;
                }

                distances[v] = distances[u] + w;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (distances[v] <= distances[u] + w) {
                continue;
            }
            return true;
        }
        return false;
    }
}
