import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] a) {
        int N = a.length;
        if(N == 1) return 0;
        int[][] dp = new int[N][2]; // {last index, count}
        for(int i = 0; i < N; i++) dp[i][0] = -2;
        for(int i = 0; i < N - 1; i++) {
            int x = a[i];
            int y = a[i + 1];
            if(x == y) continue;
            if(1 < i - dp[x][0]) {
                dp[x][0] = i;
                dp[x][1]++;
            }
            if(1 < i - dp[y][0]) {
                dp[y][0] = i;
                dp[y][1]++;
            }
        }
        int answer = 0;
        for(int[] arr : dp) {
            answer = Math.max(answer, arr[1]);
        }
        return answer * 2;
    }
}