import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    private static final int P = 1003;
    private static final int MOD = 1_000_000_007;

    public int findLength(int[] nums1, int[] nums2) {
        int left = 1;
        int right = Math.min(nums1.length, nums2.length) + 1;
        return binarySearch(left, right, nums1, nums2);
    }

    private int binarySearch(int left, int right, int[] nums1, int[] nums2) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (validate(mid, nums1, nums2)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private boolean validate(int len, int[] nums1, int[] nums2) {
        int[] arr1 = makeRolling(nums1, len);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            int value = arr1[i];
            map.computeIfAbsent(value, v -> new ArrayList<>()).add(i);
        }

        int[] arr2 = makeRolling(nums2, len);
        for (int i = 0; i < arr2.length; i++) {
            int value = arr2[i];
            if (map.containsKey(value)) {
                for (int index : map.get(value)) {
                    if (compareStream(len, nums1, index, nums2, i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[] makeRolling(int[] nums, int len) {
        int[] result = new int[nums.length - len + 1];
        long value = 0L;
        long base = 1;
        for (int i = 0; i < len; i++) {
            value = (value + nums[i]) * P % MOD;
            base = (base * P) % MOD;
        }
        result[0] = (int) value;

        for (int i = len; i < nums.length; i++) {
            value = (value - (nums[i - len] * base));
            while (value < 0)
                value += MOD;
            value = (value + nums[i]) * P % MOD;
            result[i - len + 1] = (int) value;
        }
        return result;
    }

    private boolean compareStream(int len, int[] nums1, int a, int[] nums2, int b) {
        for (int i = 0; i < len; i++) {
            if (nums1[a + i] != nums2[b + i]) {
                return false;
            }
        }
        return true;
    }
}
