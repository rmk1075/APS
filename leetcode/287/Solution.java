class Solution {
    public int findDuplicate(int[] nums) {
        int result = -1;
        int[] counts = new int[nums.length + 1];
        for(int num : nums) {
            counts[num]++;
            if(counts[num] == 2) {
                result = num;
                break;
            }
        }
        return result;
    }
}