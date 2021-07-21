class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int result = left;
        long i = left;
        for(i = i + 1; i <= right; i++) {
            result &= i;
            if(result == 0) break;
        }
        return result;
    }
}