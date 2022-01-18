class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] result = new int[k];
        for(int i = Math.max(0, k - m); i <= k && i <= n; i++) {
            int[] merged = merge(createMax(nums1, i), createMax(nums2, k - i), k);
            if(compare(merged, 0, result, 0)) result = merged;
        }
        return result;
    }

    public int[] merge(int[] a, int[] b, int k) {
        int[] result = new int[k];
        int i = 0, j = 0;
        for(int idx = 0; idx < k; idx++) result[idx] = compare(a, i, b, j) ? a[i++] : b[j++];
        return result;
    }

    public boolean compare(int[] a, int i, int[] b, int j) {
        while(i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        return j == b.length || (i < a.length && b[j] < a[i]);
    }

    public int[] createMax(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[k];
        for(int i = 0, j = 0; i < n; i++) {
            while(k < n - i + j && 0 < j && result[j - 1] < nums[i]) j--;
            if(j < k) result[j++] = nums[i];
        }
        return result;
    }
}