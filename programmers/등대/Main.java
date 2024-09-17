import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        List<Integer>[] links = initLinks(n, lighthouse);
        int[][] dp = new int[n][2]; // {{when on, when off}, ...}
        boolean[] visited = new boolean[n];
        visited[0] = true;
        dfs(links, dp, visited, 0);
        return Math.min(dp[0][0], dp[0][1]);
    }

    private List<Integer>[] initLinks(int n, int[][] lighthouse) {
        List<Integer>[] links = new List[n];
        for (int i = 0; i < n; i++) {
            links[i] = new ArrayList<>();
        }

        for (int[] link : lighthouse) {
            int a = link[0] - 1;
            int b = link[1] - 1;
            links[a].add(b);
            links[b].add(a);
        }
        return links;
    }

    private void dfs(List<Integer>[] links, int[][] dp, boolean[] visited, int index) {
        List<Integer> link = links[index];
        for (int node : link) {
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            dfs(links, dp, visited, node);
        }

        for (int node : link) {
            dp[index][0] += Math.min(dp[node][0], dp[node][1]);
            dp[index][1] += dp[node][0];
        }
        dp[index][0]++;
    }
}