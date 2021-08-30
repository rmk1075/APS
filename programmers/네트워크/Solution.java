import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            queue.offer(i);
            while(!queue.isEmpty()) {
                int current = queue.poll();
                for(int j = 0; j < n; j++) {
                    if(computers[current][j] == 0 || visited[j]) continue;
                    visited[j] = true;
                    queue.offer(j);
                }
            }
            answer++;
        }
        return answer;
    }
}