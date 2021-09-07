import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        int[][] graph = new int[n + 1][n + 1];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(i == j) continue;
                graph[i][j] = -1;
            }
        }
        for(int[] fare : fares) graph[fare[0]][fare[1]] = graph[fare[1]][fare[0]] = fare[2];

        for(int k = 1; k < n + 1; k ++) {
            for(int i = 1; i < n + 1; i++) {
                for(int j = i + 1; j < n + 1; j++) {
                    if(graph[i][k] == -1 || graph[k][j] == -1 || (graph[i][j] != -1 && graph[i][j] < graph[i][k] + graph[k][j])) continue;
                    graph[i][j] = graph[j][i] = graph[i][k] + graph[k][j];
                }
            }
        }

        answer = graph[s][a] + graph[s][b];
        for(int i = 1; i < n + 1; i++) {
            if(graph[s][i] == -1 || graph[i][a] == -1 || graph[i][b] == -1) continue;
            answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }
        return answer;
    }
}