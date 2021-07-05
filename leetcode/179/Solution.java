import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        int N = nums.length;
        String[] strs = new String[N];
        for(int i = 0; i < N; i++) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        if(strs[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(String str : strs) sb.append(str);
        return sb.toString();
    }
}