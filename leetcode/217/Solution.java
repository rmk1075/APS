// import java.util.HashSet;
// import java.util.Set;

// class Solution {
//     public boolean containsDuplicate(int[] nums) {
//         Set<Integer> set = new HashSet<>();
//         for(int num : nums) {
//             if(set.contains(num)) return true;
//             set.add(num);
//         }
//         return false;
//     }
// }

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return nums.length != set.size();
    }
}