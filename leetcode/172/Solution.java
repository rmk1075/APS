class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        while(5 <= n) {
            result += n / 5;
            n /= 5;
        }
        return result;
    }
}