class Solution {
    public int solution(int[] money) {
        int answer = 0;

        int N = money.length;
        int[] dp = new int[N];

        if(N == 3) return Math.max(Math.max(money[0], money[1]), money[2]);

        // pick 0
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for(int i = 2; i < N - 1; i++) dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
        answer = dp[N - 2];

        // pick N - 1
        dp[0] = 0;
        dp[1] = money[1];
        for(int i = 2; i < N; i++) dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
        answer = Math.max(answer, dp[N - 1]);

        return answer;
    }
}