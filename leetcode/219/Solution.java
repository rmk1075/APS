import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(k == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        int N = nums.length;
        int left = 1, right = k + 1;
        for(int i = left; i < right && i < N; i++) {
            int num = nums[i];
            if(map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }

        int cur = 0;
        while(cur < N) {
            int num = nums[cur];
            if(map.containsKey(num) && 0 < map.get(num)) return true;
            
            if(left < N) {
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }

            if(right < N) {
                if(map.containsKey(nums[right])) {
                    int temp = map.get(nums[right]);
                    if(0 < temp) return true;
                    map.put(nums[right], temp + 1);
                } else map.put(nums[right], 1);
                right++;
            }

            cur++;
        }
        
        return false;
    }
}