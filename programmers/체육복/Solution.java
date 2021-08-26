import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] cnt = new int[n + 2];
        cnt[0] = cnt[n + 1] = -1;

        for(int l : lost) cnt[l]--;
        for(int r : reserve) cnt[r]++;

        for(int i = 1; i < n + 1; i++) {
            if(-1 < cnt[i]) continue;
            if(cnt[i - 1] == 1) cnt[i - 1]--;
            else if(cnt[i + 1] == 1) cnt[i + 1]--;
            else answer++;
        }

        return n - answer;
    }
}