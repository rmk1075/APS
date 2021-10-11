class Solution {
    public int[] singleNumber(int[] nums) {
        int bit = 0;
        for(int num : nums) bit ^= num;

        int diff = bit & (-bit);
        int result = 0;
        for(int num : nums) {
            if((num & diff) == 0) result ^= num;
        }

        return new int[]{result, bit^result};
    }
}

// import java.util.HashMap;
// import java.util.Map;

// class Solution {
//     public int[] singleNumber(int[] nums) {
//         int count = 0;
//         int[] result = new int[2];

//         Map<Integer, Integer> map = new HashMap<>();
//         for(int num : nums) map.put(num, map.containsKey(num) ? 2 : 1);
//         for(int num : map.keySet()) {
//             if(map.get(num) == 1) {
//                 result[count++] = num;
//                 if(count == 2) break;
//             }
//         }

//         return result;
//     }
// }