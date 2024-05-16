package programmers.경사로의_개수;

public class Main {
  public static void main(String[] args) {
    Solution s = new Solution();
    int[][] grid = {{3, 6, 11, 12}, {4, 8, 15, 10}, {2, 7, 0, 16}};
    int[] d = {1, -2, 5};
    int k = 3;
    int result = s.solution(grid, d, k);
    System.out.println(result);
  }
}

class Solution {
  private static final int MOD = 1_000_000_007;
  private int N, M, L, D;
  private int[] dx = {-1, 0, 1, 0};
  private int[] dy = {0, -1, 0, 1};
  public int solution(int[][] grid, int[] d, int k) {
    init(grid, d);
    long[][] routes = findRoutes(grid, d);
    long[][] result = powMatrix(routes, k);
    long answer = 0;
    for (long[] row : result) {
      for (long col : row) {
        answer = (answer + col) % MOD;
      }
    }
    return (int) answer;
  }

  private void init(int[][] grid, int[] d) {
    N = grid.length;
    M = grid[0].length;
    L = N * M;
    D = d.length;
  }

  private long[][] findRoutes(int[][] grid, int[] d) {
    long[][][] dp = new long[D + 1][L][L];
    for (int i = 0; i < L; i++) {
      dp[0][i][i] = 1;
    }

    for (int di = 0; di < D; di++) {
      for (int i = 0; i < L; i++) {
        findRoute(dp, grid, di, d[di], i);
      }
    }
    return dp[D];
  }

  private void findRoute(long[][][] dp, int[][] grid, int di, int diff, int src) {
    int x = src / M;
    int y = src % M;
    for (int dir = 0; dir < 4; dir++) {
      int nx = x + dx[dir];
      int ny = y + dy[dir];
      if (nx < 0 || N <= nx || ny < 0 || M <= ny || grid[nx][ny] - grid[x][y] != diff) {
        continue;
      }

      int next = nx * M + ny;
      for (int i = 0; i < L; i++) {
        dp[di + 1][i][next] = (dp[di + 1][i][next] + dp[di][i][src]) % MOD;
      }
    }
  }

  private long[][] powMatrix(long[][] matrix, int count) {
    if (count == 1) {
      return matrix;
    }

    long[][] result = powMatrix(matrix, count / 2);
    result = mulMatrix(result, result);
    if (count % 2 == 0) {
      return result;
    } else {
      return mulMatrix(matrix, result);
    }
  }

  private long[][] mulMatrix(long[][] a, long[][] b) {
    long[][] result = new long[L][L];
    for (int i = 0; i < L; i++) {
      for (int j = 0; j < L; j++) {
        for (int k = 0; k < L; k++) {
          result[i][j] += (a[i][k] * b[k][j]) % MOD;
          result[i][j] %= MOD;
        }
      }
    }
    return result;
  }
}