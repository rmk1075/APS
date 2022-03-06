/**
 * description
 * 
 * dp 로 구현한다.
 * 피보나치와 같이 f(n) = f(n - 1) + f(n - 2) 로 계산된다.
 */
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