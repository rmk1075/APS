import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] result = new int[N - k + 1];

        Deque<Integer> queue = new LinkedList<>();
        int start = 0;
        int end = 0;
        for(; end < N; end++) {
            int num = nums[end];
            while(!queue.isEmpty() && nums[queue.peekFirst()] < num) queue.pollFirst();
            queue.offerFirst(end);

            if(k - 1 <= start + end) {
                result[start] = nums[queue.peekLast()];
                start++;
                if(queue.peekLast() < start) queue.pollLast();
            }
        }

        return result;
    }
}