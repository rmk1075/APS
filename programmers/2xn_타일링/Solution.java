class Solution {
    static int MOD = 1_000_000_007;
    public int solution(int n) {
        int a = 1;
        int b = 1;
        for(int i = 1; i < n; i++) {
            int value = (a + b) % MOD;
            a = b;
            b = value;
        }
        return b;
    }
}