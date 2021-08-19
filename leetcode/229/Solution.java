import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length / 3;

        int past = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == past) count++;
            else {
                if(N < count) result.add(past);
                past = nums[i];
                count = 1;
            }
        }
        if(N < count) result.add(past);
        return result;
    }
}

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// class Solution {
//     public List<Integer> majorityElement(int[] nums) {
//         List<Integer> result = new ArrayList<>();
//         Map<Integer, Integer> map = new HashMap<>();
//         int N = nums.length;
//         int ths = N / 3 + 1;
//         for(int num : nums) {
//             if(map.containsKey(num)) {
//                 int temp = map.get(num) + 1;
//                 map.put(num, temp);
//                 if(temp == ths) result.add(num);
//             } else {
//                 map.put(num, 1);
//                 if(1 == ths) result.add(num);
//             }
//         }

//         return result;
//     }
// }