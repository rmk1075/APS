import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long)nums[i] + t);
            Long ceil = set.ceiling((long)nums[i] - t);
            if(((floor != null) && (nums[i] <= floor)) || ((ceil != null) && (ceil <= nums[i]))) return true;

            set.add((long)nums[i]);
            if(k <= i) set.remove((long)nums[i - k]);
        }

        return false;
    }
}