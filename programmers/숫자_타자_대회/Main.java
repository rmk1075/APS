package programmers.숫자_타자_대회;

import java.util.*;

public class Main {
    
}

class Solution {
    
    private static final int[][] MOVE_COST = new int[][] {
        new int[]{1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        new int[]{7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        new int[]{6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        new int[]{7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        new int[]{5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        new int[]{4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        new int[]{5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        new int[]{3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        new int[]{2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        new int[]{3, 6, 5, 4, 5, 3, 2, 4, 2, 1},
    };
    
    public int solution(String numbers) {
        char[] chs = numbers.toCharArray();
        int length = chs.length;
        int[][][] dp = initDp(length);

        for (int i = 0; i < length; i++) {
            int number = chs[i] - '0';
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    int current = dp[i][j][k];
                    if (current == Integer.MAX_VALUE) {
                        continue;
                    }
                    
                    if (j == number || k == number) {
                        dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], current + 1);
                    } else {
                        dp[i + 1][number][k] = Math.min(dp[i + 1][number][k], current + move(j, number));
                        dp[i + 1][j][number] = Math.min(dp[i + 1][j][number], current + move(k, number));
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                answer = Math.min(answer, dp[length][i][j]);
            }
        }
        return answer;
    }

    private int move(int from, int to) {
        return MOVE_COST[from][to];
    }

    private int[][][] initDp(int length) {
        int[][][] dp = new int[length + 1][10][10];
        for (int i = 0; i < length + 1; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        dp[0][4][6] = 0;
        return dp;
    }
}
