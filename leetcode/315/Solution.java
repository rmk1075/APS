import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sortedList = new ArrayList<>();
        List<Integer> result = new LinkedList<>();
        int N = nums.length;
        for(int i = N - 1; -1 < i; i--) result.add(0, insert(sortedList, nums[i]));
        return result;
    }

    public static int insert(List<Integer> sortedList, int num) {
        int left = 0;
        int right = sortedList.size() - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            int temp = sortedList.get(mid);
            if(num <= temp) right = mid - 1;
            else left = mid + 1;
        }

        sortedList.add(left, num);
        return left;
    }
}