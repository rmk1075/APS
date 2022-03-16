import java.util.Arrays;

class Solution {
    public int solution(int sticker[]) {
        int N = sticker.length;
        if(N == 1) return sticker[0];

        int[] dp0 = new int[N];
        dp0[0] = sticker[0];
        dp0[1] = sticker[0];
        for(int i = 2; i < N - 1; i++) dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + sticker[i]);

        int[] dp1 = new int[N];
        dp1[1] = sticker[1];
        for(int i = 2; i < N; i++) dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);

        int answer = Math.max(dp0[N - 2], dp1[N - 1]);
        return answer;
    }
}