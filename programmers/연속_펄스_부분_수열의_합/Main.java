class Solution {
    public long solution(int[] sequence) {
        int length = sequence.length;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                sequence[i] *= -1;
            }
        }

        long[][] dp = new long[length][2];
        dp[0][0] = dp[0][1] = sequence[0];
        for (int i = 1; i < length; i++) {
            int seq = sequence[i];
            dp[i][0] = Math.max(dp[i - 1][0] + seq, seq);
            dp[i][1] = Math.min(dp[i - 1][1] + seq, seq);
        }

        long answer = 0;
        for (long[] value : dp) {
            answer = Math.max(answer, Math.abs(value[0]));
            answer = Math.max(answer, Math.abs(value[1]));
        }
        return answer;
    }
}