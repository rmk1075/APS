import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int sum = 0;
        for(int work : works) {
            sum += work;
            pq.offer(work);
        }
        if(sum <= n) return 0;
        while(0 < n--) pq.offer(pq.poll() - 1);

        long answer = 0;
        for(int work : pq) answer += Math.pow(work, 2);
        return answer;
    }
}