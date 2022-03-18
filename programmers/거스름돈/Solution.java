import java.util.Arrays;

/**
 * overview
 * 
 * 돈의 종류가 여러개 있을 때, 이 돈의 종류를 가지고 금액 n을 만들 수 있는 모든 경우의 수를 구해야한다.
 * n 이 100,000 까지이기 때문에 모든 경우의 수를 탐색하기보다는 dp 를 사용하여 문제를 계산한다.
 * 
 * description
 * 
 * 0부터 n 만큼의 금액에 대해서 각 금액을 만들 수 있는 경우의 수를 저장할 dp 배열을 생성한다.
 * 금액 m 으로 i 만큼의 금액을 만드는 방법은 i - m 의 금액을 만들 수 있는 방법과 같다.
 * 이를 식으로 표현하면 dp[i] = dp[i] + dp[i - m] 이 된다.
 * 이를 계산하기 위해 momey 를 정렬하여 작은 수 부터 계산하도록 한다.
 * 작은 수부터 계산하는 이유는 현재 금액보다 더 작은 금액 (i - m) 의 경우의 수를 가져와서 연산을 하기 때문에 더 작은 금액의 경우의 수가 먼저 게산되어 있어야 한다.
 * 이제 정렬된 money 를 반복문으로 돌면서 해당 금액 m 으로 금액 m 부터 n 까지를 만들 수 있는 경우의 수를 계산한다.
 * 이때 결과 값으로 1,000,000,007 로 나눈 나머지를 반환해야 하기 때문에 이를 유의해서 계산한다.
 * 연산이 끝난 후에 dp[n] 을 결과로 반환한다.
 */
class Solution {
    static int MOD = 1_000_000_007;
    int[] dp;
    public int solution(int n, int[] money) {
        dp = new int[n + 1];
        dp[0] = 1;
        Arrays.sort(money);
        for(int m : money) {
            for(int i = m; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - m]) % MOD;
            }
        }
        return dp[n];
    }
}