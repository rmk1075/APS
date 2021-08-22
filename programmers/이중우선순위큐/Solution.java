import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};

        int cnt = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {return o2.compareTo(o1);});
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> {return o1.compareTo(o2);});
        for(String operation : operations) {
            if(operation.charAt(0) == 'I') {
                int num = Integer.valueOf(operation.split(" ")[1]);
                maxHeap.offer(num);
                minHeap.offer(num);
                cnt++;
            }
            else {
                if(cnt == 0) continue;

                int num = -1;
                if(operation.split(" ")[1].equals("1")) {
                    num = maxHeap.poll();
                    minHeap.remove(num);
                } else {
                    num = minHeap.poll();
                    maxHeap.remove(num);
                }
                cnt--;
            }
        }

        answer = cnt == 0 ? new int[]{0, 0} : new int[]{maxHeap.poll(), minHeap.poll()};
        return answer;
    }
}