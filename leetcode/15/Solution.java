import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int N = nums.length, val = 0;
        int last1, last2, last3;
        last1 = last2 = last3 = Integer.MIN_VALUE;
        for (int i = 0; i < N - 2; i++) {
            if (nums[i] == last1)
                continue;
            last1 = nums[i];
            for (int j = i + 1; j < N - 1; j++) {
                if (nums[j] == last2)
                    continue;
                last2 = nums[j];

                val = nums[i] + nums[j];
                if (0 < val + nums[j + 1] || val + nums[N - 1] < 0)
                    continue;

                for (int k = j + 1; k < N; k++) {
                    if (val + nums[k] == 0) {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                        break;
                    }
                }
            }
        }

        return result;
    }
}

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Collections;
// import java.util.List;

// class Solution {
// int N;
// Integer[] arr = new Integer[3];
// List<List<Integer>> list = new ArrayList<>();

// public List<List<Integer>> threeSum(int[] nums) {
// Arrays.sort(nums);

// N = nums.length;
// for (int i = 0; i < N; i++) {
// arr[0] = nums[i];
// find(nums, i + 1, nums[i], 1);
// }

// return list;
// }

// public boolean find(int[] nums, int index, int val, int count) {
// if (count == 3) {
// if (val == 0) {

// if (!list.contains(Arrays.asList(arr))) {
// list.add(Arrays.asList(arr.clone()));
// return true;
// }
// }

// return false;
// }

// for (int i = index; i < N; i++) {
// arr[count] = nums[i];
// if (find(nums, i + 1, val + nums[i], count + 1))
// break;
// }

// return false;
// }
// }