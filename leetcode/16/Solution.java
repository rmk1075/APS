import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int answer = nums[0] + nums[1] + nums[2];
        int val, last, left, right, N = nums.length;
        for (int i = 0; i < N; i++) {
            val = target - nums[i];
            left = i + 1;
            right = N - 1;
            while (left < right) {
                last = nums[i] + nums[left] + nums[right];
                if (nums[left] + nums[right] == val)
                    return target;
                else if (nums[left] + nums[right] < val)
                    left++;
                else
                    right--;

                answer = Math.abs(target - answer) < Math.abs(target - last) ? answer : last;
            }
        }

        return answer;
    }
}