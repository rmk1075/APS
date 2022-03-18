/**
 * overview
 * 
 * 효진이는 1칸 또는 2칸으로 이동할 수 있다.
 * 이때 효진이가 n칸까지 갈 수 있는 경우의 수를 구한다.
 * 전체 경우의 수를 탐색하면 시간이 너무 오래 걸리기 때문에 dp 를 사용해 문제를 해결한다.
 * 
 * description
 * 
 * 효진이는 1칸 또는 2칸을 이동할 수 있기 때문에 i 번째 칸에 도달할 수 있는 방법은 i - 1 번째 칸과 i - 2 번째 칸에 도달할 수 있는 방법을 더한 값과 같다.
 * 이를 식으로 표현하면 dp[i] = dp[i - 1] + dp[i - 2] 가 된다.
 * 이 값을 dp 배열에 저장하여 연산을 수행한다. 이때 1234567 로 값은 나눠줘야함을 유의한다.
 * 결과로 n 값에 도달하는 경우의 수인 dp[n] 을 반환해준다.
 */
class Solution {
    static int MOD = 1234567;
    public long solution(int n) {
        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= n; i++) dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        return dp[n];
    }
}