import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int value = target - nums[i] - nums[j], left = j + 1, right = N - 1;
                while (left < right) {
                    if (value == nums[left] + nums[right]) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        int temp = nums[left];
                        while (temp == nums[left] && left < right)
                            left++;
                    } else if (value < nums[left] + nums[right])
                        right--;
                    else
                        left++;
                }

                int temp = nums[j];
                while (j < N && temp == nums[j])
                    j++;
                j--;
            }

            int temp = nums[i];
            while (i < N && temp == nums[i])
                i++;
            i--;
        }

        return result;
    }
}