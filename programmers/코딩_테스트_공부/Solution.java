import java.util.Arrays;

class Solution {
  private static final int INF = Integer.MAX_VALUE;
  private int maxAlp, maxCop;
  private int[][] dp;
  public int solution(int alp, int cop, int[][] problems) {
    init(problems);
    solve(alp, cop, problems);
    return dp[maxAlp][maxCop];
  }

  private void init(int[][] problems) {
    for (int[] problem : problems) {
      maxAlp = Math.max(problem[0], maxAlp);
      maxCop = Math.max(problem[1], maxCop);
    }
    dp = new int[maxAlp + 1][maxCop + 1];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], INF);
    }
  }

  private void solve(int alp, int cop, int[][] problems) {
    alp = Math.min(alp, maxAlp);
    cop = Math.min(cop, maxCop);
    dp[alp][cop] = 0;
    for (int i = alp; i <= maxAlp; i++) {
      for (int j = cop; j <= maxCop; j++) {
        if (dp[i][j] == INF) {
          continue;
        }
        calculate(i, j, problems);
      }
    }
  }

  private void calculate(int alp, int cop, int[][] problems) {
    int cost = dp[alp][cop];
    for (int[] problem : problems) {
      if (!isSolvable(alp, cop, problem[0], problem[1])) {
        continue;
      }
      updateDp(alp, cop, problem[2], problem[3], cost + problem[4]);
    }
    updateDp(alp, cop, 1, 0, cost + 1);
    updateDp(alp, cop, 0, 1, cost + 1);
  }

  private boolean isSolvable(int alp, int cop, int alpReq, int copReq) {
    return alpReq <= alp && copReq <= cop;
  }

  private void updateDp(int alp, int cop, int alpRwd, int copRwd, int cost) {
    int nextAlp = Math.min(alp + alpRwd, maxAlp);
    int nextCop = Math.min(cop + copRwd, maxCop);
    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], cost);
  }
}