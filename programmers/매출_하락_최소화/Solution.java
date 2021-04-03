import java.util.List;
import java.util.ArrayList;

class Solution {
    int N;
    int[][] dp;
    List<Integer>[] teams;
    public int solution(int[] sales, int[][] links) {
        int answer = 0;

        N = sales.length;
        dp = new int[N][2];
        teams = new List[N];
        for(int[] link : links) {
            int a = link[0] - 1, b = link[1] - 1;
            if(teams[a] == null) {
                teams[a] = new ArrayList<>();
            }
            teams[a].add(b);
        }

        dfs(sales, 0);
        answer = Math.min(dp[0][0], dp[0][1]);
        return answer;
    }

    public void dfs(int[] sales, int index) {
        if(teams[index] == null) {
            dp[index][1] = sales[index];
            return ;
        }

        for(int member : teams[index]) {
            dfs(sales, member);
        }

        int temp = 0;
        for(int member : teams[index]) {
            temp += Math.min(dp[member][0], dp[member][1]);
        }
        dp[index][1] = temp + sales[index];

        int minVal = Integer.MAX_VALUE;
        for(int member : teams[index]) {
            minVal = Math.min(minVal, temp - Math.min(dp[member][0], dp[member][1]) + dp[member][1]);
        }
        dp[index][0] = minVal;
    }
}