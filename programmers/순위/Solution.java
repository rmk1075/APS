import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited;
    boolean[][] arr;
    public int solution(int n, int[][] results) {
        int answer = 0;

        arr = new boolean[n][n];
        for(int[] result : results) arr[result[0] - 1][result[1] - 1] = true;

        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            visited[i] = true;
            if(valid(n, i, results)) answer++;
        }
        
        return answer;
    }
    
    public boolean valid(int n, int index, int[][] results) {
        // row
        int row = 0;
        queue.offer(index);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next = 0; next < n; next++) {
                if(visited[next] || !arr[cur][next]) continue;
                visited[next] = true;
                row++;
                queue.offer(next);
            }
        }

        // column
        int col = 0;
        queue.offer(index);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next = 0; next < n; next++) {
                if(visited[next] || !arr[next][cur]) continue;
                visited[next] = true;
                col++;
                queue.offer(next);
            }
        }

        return row + col == n - 1;
    }
}