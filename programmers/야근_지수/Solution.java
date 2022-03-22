import java.util.Collections;
import java.util.PriorityQueue;

/**
 * overview
 * 
 * 야근 지수가 가장 작아지기 위해서는 숫자들 중에서 가장 큰 값을 최소화 시켜야한다.
 * 이를 위해서 매번 배열에서 가장 큰 수의 값을 1씩 감소시킨다.
 * 
 * description
 * 
 * 매번 가장 큰 수를 선택하기 위해서는 정렬이 필요하다. 매번 정렬을 할 수도 있지만 이번에는 PriorityQueue 를 사용했다.
 * PriorityQueue 를 사용하여 내림차순으로 정렬하고, 매번 가장 큰 수를 poll 하여 1을 감소시킨 후 다시 큐에 넣는다.
 * 이를 n 번동안 반복한 후, 큐에 남아있는 숫자들의 제곱을 모두 더하여 결과를 게산한다.
 */
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