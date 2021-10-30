import java.util.ArrayList;
import java.util.List;

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        int N = nums.length;
        for(int i = 1; i < N; i++) {
            if(list.get(list.size() - 1) < nums[i]) list.add(nums[i]);
            else push(list, nums[i]);
        }

        return list.size();
    }

    private void push(List<Integer> list, int value) {
        int left = 0;
        int right = list.size() - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(list.get(mid) < value) left = mid + 1;
            else right = mid - 1;
        }

        list.add(left, value);
        list.remove(left + 1);
    }
}