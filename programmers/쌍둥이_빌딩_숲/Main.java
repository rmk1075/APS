package programmers.쌍둥이_빌딩_숲;

public class Main {
    
}

class Solution {
    private static final int MOD = 1_000_000_007;
    public int solution(int n, int count) {
        long[][] dp = new long[n + 1][count + 1]; // dp[n][count]: n 쌍의 빌딩이 있을 때 count 개로 보이는 조건을 충적하는 경우의 수
        dp[1][1] = 1L;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < count + 1; j++) {
                // dp[i][j] 를 계산할 때 가장 낮은 높이의 빌딩 쌍을 추가하는 방식으로 계산한다. => 가장 낮은 높이의 빌딩은 그 사이에 다른 빌딩이 들어갈 수 없기 때문에 항상 쌍이 붙어서 위치된다.
                // 1. 가장 낮은 높이의 빌딩쌍을 맨 앞에 세우는 경우 => 맨 앞에 추가하면 count 가 + 1 된다. => dp[i - 1][j - 1] 의 경우의 수
                // 2. 가장 낮은 높이의 빌딩쌍을 맨 앞이 아닌 곳에 세우는 경우 => 맨 앞이 아닌 곳에 세우면 count 는 유지된다.
                //      => 제일 낮은 빌딩이기에 모든 빌딩 사이에 세울 수 있다. => 세울 수 있는 곳의 수는 이전 빌딩의 개수 (2 * (n - 1)) 만큼 존재한다. => dp[i - 1][j] * 2 * (i - 1)
                dp[i][j] = (dp[i - 1][j - 1] + (dp[i - 1][j] * 2 * (i - 1))) % MOD;
            }
        }
        return (int)(dp[n][count] % MOD);
    }
}
