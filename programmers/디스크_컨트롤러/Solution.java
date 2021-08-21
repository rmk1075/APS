import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int i = 0;
        int time = 0;
        int N = jobs.length;
        while(i < N || !pq.isEmpty()) {
            while(i < N && jobs[i][0] <= time) pq.offer(jobs[i++]);
            if(pq.isEmpty()) time = jobs[i][0];
            else {
                int[] task = pq.poll();
                time += task[1];
                answer += time - task[0];
            }
        }

        return answer / N;
    }
}