import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int N = nums.length, last = Integer.MIN_VALUE;
        for (int i = 0; i < N - 2; i++) {
            if (nums[i] == last)
                continue;
            last = nums[i];

            int val = 0 - nums[i];
            int left = i + 1, right = N - 1;
            while (left < right) {
                if (nums[left] + nums[right] == val) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (nums[left] == nums[++left] && left < right)
                        ;
                    while (nums[right] == nums[--right] && left < right)
                        ;
                } else if (nums[left] + nums[right] < val)
                    left++;
                else
                    right--;
            }
        }

        return result;
    }
}

// import java.util.ArrayList;
// import java.util.Arrays;

// class Solution {
// public List<List<Integer>> threeSum(int[] nums) {
// List<List<Integer>> result = new ArrayList<>();

// Arrays.sort(nums);

// int N = nums.length, val = 0;
// int last1, last2, last3;
// last1 = last2 = last3 = Integer.MIN_VALUE;
// for (int i = 0; i < N - 2; i++) {
// if (nums[i] == last1)
// continue;
// last1 = nums[i];
// for (int j = i + 1; j < N - 1; j++) {
// if (nums[j] == last2)
// continue;
// last2 = nums[j];

// val = nums[i] + nums[j];
// if (0 < val + nums[j + 1] || val + nums[N - 1] < 0)
// continue;

// for (int k = j + 1; k < N; k++) {
// if (val + nums[k] == 0) {
// result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
// break;
// }
// }
// }
// }

// return result;
// }
// }