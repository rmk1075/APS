import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sco : scoville) pq.add(sco);

        while(1 < pq.size() && pq.peek() < K) {
            pq.offer(pq.poll() + (pq.poll() * 2));
            answer++;
        }

        if(pq.peek() < K) return -1;
        return answer;
    }
}